package cours.jee.m1gl.api.dao;

import cours.jee.m1gl.api.model.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinDao extends JpaRepository<Medecin, Integer> {
    public Medecin findByMatricule(String matricule);

}
