package cours.jee.m1gl.api.controlleur;


import cours.jee.m1gl.api.dao.MedecinDao;
import cours.jee.m1gl.api.dao.ServiceDao;
import cours.jee.m1gl.api.dao.SpecialiteDao;
import cours.jee.m1gl.api.model.ErrorResponse;
import cours.jee.m1gl.api.model.Medecin;
import cours.jee.m1gl.api.model.Service;
import cours.jee.m1gl.api.model.Specialite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/medecin")
public class MedecinController {

    @Autowired
    private MedecinDao medecinDao;

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private SpecialiteDao specialiteDao;

    @PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @GetMapping("/liste")
    public ResponseEntity<?> listeMedecin() {
        return ResponseEntity.ok(medecinDao
                .findAll());
    }

    @PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @PostMapping("/add")
    public @ResponseBody Medecin add(@RequestBody Medecin medecin){
        Optional<Service> service=serviceDao.findById(medecin.getService().getId());
        medecin.setService(service.get());
        List<Specialite> specialites= medecin.getSpecialites();
        List<Specialite> specialiteList = new ArrayList<>();
        for(int i=0; i<specialites.size(); i++){
            Optional<Specialite> specialite = specialiteDao.findById(specialites.get(i).getId());
            specialiteList.add(specialite.get());
        }
        medecin.setSpecialites(specialiteList);
      return  medecinDao.save(medecin);
    }

    @PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @GetMapping("/search/{matricule}")
    public @ResponseBody Medecin search(@PathVariable("matricule") String matricule){
        return medecinDao.findByMatricule(matricule);
    }

}
