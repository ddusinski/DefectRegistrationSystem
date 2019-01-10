package pl.dusinski.defectregistrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dusinski.defectregistrationsystem.model.Defect;

import java.util.List;

@Repository
public interface DefectRepository extends JpaRepository<Defect, Long> {
    List<Defect> findByDefectOwner(String defectOwner);
}
