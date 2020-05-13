package cours.jee.m1gl.api.controlleur;

import cours.jee.m1gl.api.dao.ServiceDao;
import cours.jee.m1gl.api.model.ErrorResponse;
import cours.jee.m1gl.api.model.JwtRequest;
import cours.jee.m1gl.api.model.Medecin;
import cours.jee.m1gl.api.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceDao serviceDao;

//    @GetMapping("/add")
//    public String add (Model model){
//        model.addAttribute("service", serviceDao.findAll());
//        return "";
//    }

    @PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addService(@RequestBody Service service){
        serviceDao.save(service);
        return ResponseEntity.ok(service);
    }

    @PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") int id){
        try {
            serviceDao.deleteById(id);
            return ResponseEntity.ok("Service supprimé");
        }catch (Exception e){
            return  ResponseEntity.ok(new ErrorResponse("Suppression echouée"));
        }

    }

    @PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @GetMapping("/liste")
    public ResponseEntity<?> listeService() {
        return ResponseEntity.ok(serviceDao
                .findAll());
    }
}
