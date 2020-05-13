package cours.jee.m1gl.api.dao;


import cours.jee.m1gl.api.model.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialiteDao extends JpaRepository<Specialite, Integer> {
}
