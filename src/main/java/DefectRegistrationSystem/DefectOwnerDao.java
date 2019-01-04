package DefectRegistrationSystem;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/*
public interface UserDaoInterface{
    void save(DefectOwner user);
    List<DefectOwner> list();
}*/

@Repository
public class DefectOwnerDao {

    @Autowired
    private SessionFactory sessionFactory;

    //@Override
    public void save(DefectOwner defectOwner){
        sessionFactory.getCurrentSession().save(defectOwner);
    }

    //@Override
    public List<DefectOwner> list(){
        TypedQuery<DefectOwner> query = sessionFactory.getCurrentSession().createQuery("from DefectOwner");
        return query.getResultList();
    }
}
