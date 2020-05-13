package cours.jee.m1gl.api.controlleur;

import cours.jee.m1gl.api.dao.ServiceDao;
import cours.jee.m1gl.api.dao.SpecialiteDao;
import cours.jee.m1gl.api.model.ErrorResponse;
import cours.jee.m1gl.api.model.Service;
import cours.jee.m1gl.api.model.Specialite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/specialite")
public class SpecialiteController {

    @Autowired
    private SpecialiteDao specialiteDao;

    @Autowired
    private ServiceDao serviceDao;



    @PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @PostMapping("/add/{id}")
    public ResponseEntity<?> addSpecialite(@RequestBody Specialite specialite, @PathVariable("id") int id){
        Service service=serviceDao.findById(id).orElse(null);
        if (service != null) {
            specialite.setService(service);
            specialiteDao.save(specialite);
            return ResponseEntity.ok(specialite);
        } else {
            return ResponseEntity.ok(new ErrorResponse("Service non disponible"));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_MEDECIN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SECRETAIRE')  ")
    @GetMapping("/liste")
    public ResponseEntity<?> listeService() {
        return ResponseEntity.ok(serviceDao
                .findAll());
    }
}
