package pl.edu.pw.dusinski.defect.registration.system.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static pl.edu.pw.dusinski.defect.registration.system.Profiles.NOT_MOCK;

@Configuration
@Profile(NOT_MOCK)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.formLogin().loginPage("/login");
        http.formLogin().permitAll();
        http.logout().permitAll();
    }

}
