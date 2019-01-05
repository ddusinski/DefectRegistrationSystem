package DefectRegistrationSystem;

import DefectRegistrationSystem.DefectOwner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectOwnerRepository extends CrudRepository<DefectOwner, Long>
{
    List<DefectOwner>findByName(String name);
}

