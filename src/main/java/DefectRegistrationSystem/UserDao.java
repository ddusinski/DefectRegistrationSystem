package DefectRegistrationSystem;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/*
public interface UserDaoInterface{
    void save(User user);
    List<User> list();
}*/

@Repository
public class UserDao  {

    @Autowired
    private SessionFactory sessionFactory;

    //@Override
    public void save(User user){
        sessionFactory.getCurrentSession().save(user);
    }

    //@Override
    public List<User> list(){
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
}
