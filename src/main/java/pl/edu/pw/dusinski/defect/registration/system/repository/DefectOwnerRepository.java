package pl.edu.pw.dusinski.defect.registration.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.dusinski.defect.registration.system.model.DefectOwner;

import java.util.List;

@Repository
public interface DefectOwnerRepository extends JpaRepository<DefectOwner, Long> {
    //    todo unused? remove then
    List<DefectOwner> findByName(String name);
}

