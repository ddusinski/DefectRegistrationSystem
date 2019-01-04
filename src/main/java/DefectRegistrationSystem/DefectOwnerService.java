package DefectRegistrationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
public interface UserService{
    void save(DefectOwner user);
    List<DefectOwner> list();
}*/
@Service
public class DefectOwnerService {

    @Autowired
    private DefectOwnerDao defectOwnerDao;

    @Transactional
    public void save(DefectOwner defectOwner){
        defectOwnerDao.save(defectOwner);
    }
    @Transactional(readOnly = true)
    public List<DefectOwner> list(){
        return defectOwnerDao.list();
    }
}
