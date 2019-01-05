package DefectRegistrationSystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication//(exclude = HibernateJpaAutoConfiguration.class)
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages ="DefectRegistrationSystem")

public class Application {
    public static void main(String[] args) {
    SpringApplication.run(Application.class);
    }
}
