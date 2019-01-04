package DefectRegistrationSystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
//todo nie wiem czy tutaj ale encoder wypadaloby ustawic jaki konkretny np. sha256 czy inny, ale to jak bedziesz dodawac baze to zrobisz
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.userDetailsService(inMemoryUserDetailsManager());
        //auth.inMemoryAuthentication().withUser("jduser").password(encoder.encode("123")).authorities("ROLE_USER");
       // auth.inMemoryAuthentication().withUser("jduser").password(encoder.encode("123")).authorities("ROLE_USER");
        //auth.inMemoryAuthentication().withUser("jdadmin").password(encoder.encode("111")).authorities("ROLE_ADMIN");


    }
//todo nie wiem jak dziala ponizsza implemetacja, ale i tak pewnie tu bedzie cos co pobiera z bazy danych
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        final Properties users = new Properties();
        users.put("admin","{noop}123,ROLE_ADMIN");
        return new InMemoryUserDetailsManager(users);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/").authenticated();
        //http.authorizeRequests().anyRequest().authenticated();
//        todo admin jest tez userem, wiec powinno wystarczyc cos jak ponizej, i tak dla kazgeo, oprocz tego zeby sie nie powtarzac antMatchers bierze tablce elementow
//        http.authorizeRequests().antMatchers("/index", "/defectTable").hasRole("ROLE_USER");
        http.authorizeRequests().antMatchers("/index").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/addDefect").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/defectTable").access("hasRole('ROLE_USER')");
//todo ogolnie mozna ten zapis uprosic wszedzie zamiast konczyc linijke dajesz .and(), i np. jak masz formLogin().loginPage() daj od razu po tym .permitAll()
//po co pisac 2x to samo
        http.formLogin().loginPage("/login");
        http.formLogin().permitAll();
        http.logout().permitAll();

    }




}
