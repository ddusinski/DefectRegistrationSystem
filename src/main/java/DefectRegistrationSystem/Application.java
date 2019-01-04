package DefectRegistrationSystem;
//todo package powinien być zbudowany podobnie jak group id + nazwa aplikacji czyli w twoim wypadu np. pl.edu.pw.dusinski.defect
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class Application {
    public static void main(String[] args) {
    SpringApplication.run(Application.class);
    }
}
