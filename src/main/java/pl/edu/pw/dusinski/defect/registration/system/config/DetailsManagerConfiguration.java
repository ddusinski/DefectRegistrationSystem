package pl.edu.pw.dusinski.defect.registration.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class DetailsManagerConfiguration {

    @Bean
    public UserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager();
    }
}
