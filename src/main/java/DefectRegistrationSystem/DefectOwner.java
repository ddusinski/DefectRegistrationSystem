package DefectRegistrationSystem;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Email;

@Entity
@Table(name= "TBL_USERS")
public class DefectOwner {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    @NotEmpty(message = "Please Enter your name")
    private String name;

    @Column(name = "USER_PASSWORD")
    private String password;

    protected DefectOwner(){}

    public DefectOwner(String name, String password){
        this.name=name;
        this.password=password;
    }


    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String email) {
        this.password = email;
    }
}
