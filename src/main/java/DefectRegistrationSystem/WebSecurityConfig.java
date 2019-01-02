package DefectRegistrationSystem;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication().withUser("jduser").password(encoder.encode("123")).authorities("ROLE_USER");
        auth.inMemoryAuthentication().withUser("jduser").password(encoder.encode("123")).authorities("ROLE_USER");
        auth.inMemoryAuthentication().withUser("jdadmin").password(encoder.encode("111")).authorities("ROLE_ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/").authenticated();
        //http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/index").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/addDefect").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/defectTable").access("hasRole('ROLE_USER')");
        http.formLogin().loginPage("/login");
        http.formLogin().permitAll();
        http.logout().permitAll();


    }




}
