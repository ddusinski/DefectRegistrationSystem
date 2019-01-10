package pl.edu.pw.dusinski.defect.registration.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.dusinski.defect.registration.system.model.Defect;

import java.util.List;

@Repository
public interface DefectRepository extends JpaRepository<Defect, Long> {
    List<Defect> findByDefectOwner(String defectOwner);
}
