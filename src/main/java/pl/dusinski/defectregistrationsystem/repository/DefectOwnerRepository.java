package pl.dusinski.defectregistrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dusinski.defectregistrationsystem.model.DefectOwner;

import java.util.List;

@Repository
public interface DefectOwnerRepository extends JpaRepository<DefectOwner, Long>
{

}

