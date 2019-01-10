package pl.dusinski.defectregistrationsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;



@Configuration
public class DetailsManagerConfiguration {

    @Bean
    public static UserDetailsManager inMemoryUserDetailsManager() {

        return new InMemoryUserDetailsManager();
    }
}
