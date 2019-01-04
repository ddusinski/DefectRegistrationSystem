package DefectRegistrationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
public interface UserService{
    void save(User user);
    List<User> list();
}*/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void save(User user){
        userDao.save(user);
    }
    @Transactional(readOnly = true)
    public List<User> list(){
        return userDao.list();
    }
}
