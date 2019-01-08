package DefectRegistrationSystem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectRepository extends CrudRepository<Defect, Long> {
    List<Defect> findByDefectOwner(String defectOwner);

}
