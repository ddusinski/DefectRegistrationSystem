package DefectRegistrationSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectOwnerRepository extends JpaRepository<DefectOwner, Long>
{
    List<DefectOwner>findByName(String name);
}

