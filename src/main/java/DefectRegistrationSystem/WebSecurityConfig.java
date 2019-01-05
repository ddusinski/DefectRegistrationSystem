package DefectRegistrationSystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import java.util.Properties;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.userDetailsService(inMemoryUserDetailsManager());
        //auth.inMemoryAuthentication().withUser("jduser").password(encoder.encode("123")).authorities("ROLE_USER");
       // auth.inMemoryAuthentication().withUser("jduser").password(encoder.encode("123")).authorities("ROLE_USER");
        //auth.inMemoryAuthentication().withUser("jdadmin").password(encoder.encode("111")).authorities("ROLE_ADMIN");


    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        final Properties users = new Properties();
        users.put("admin","{noop}123,ROLE_ADMIN");
        return new InMemoryUserDetailsManager(users);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.authorizeRequests().antMatchers("/").authenticated();
        http.authorizeRequests().anyRequest().authenticated();
        //http.authorizeRequests().antMatchers("/defectTable").access("hasRole('ROLE_ADMIN')");
        //http.authorizeRequests().antMatchers("/addDefect","/addDefectOwner","/defectTable").access("hasRole('ROLE_ADMIN')");
        //http.authorizeRequests().antMatchers("/defectTable").access("hasRole('ROLE_USER')");
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.formLogin().loginPage("/login");
        http.formLogin().permitAll();
        http.logout().permitAll();
    }




}
