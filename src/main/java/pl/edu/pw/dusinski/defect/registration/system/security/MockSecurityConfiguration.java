package pl.edu.pw.dusinski.defect.registration.system.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static pl.edu.pw.dusinski.defect.registration.system.Profiles.MOCK;
import static pl.edu.pw.dusinski.defect.registration.system.Profiles.NOT_MOCK;

@Configuration
@Profile(MOCK)
public class MockSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/*").permitAll();
        http.authorizeRequests().anyRequest().permitAll();
        http.csrf().disable();
    }

    

}
