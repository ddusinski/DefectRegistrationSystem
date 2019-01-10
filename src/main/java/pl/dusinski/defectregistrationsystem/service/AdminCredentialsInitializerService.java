package pl.dusinski.defectregistrationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import pl.dusinski.defectregistrationsystem.config.AdminConfiguration;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class AdminCredentialsInitializerService {

    @Autowired
    private AdminConfiguration adminConfiguration;

    @Autowired
    private UserDetailsManager userDetailsManager;


    @PostConstruct
    private void addAdminIfNotPresent(){

        System.out.println("UWAGA");
        System.out.println(adminConfiguration.getUsername());
        System.out.println(adminConfiguration.getPassword());
        System.out.println("UWAGA");

    userDetailsManager.createUser(new User(adminConfiguration.getUsername(),
                "{noop}"+adminConfiguration.getPassword(),
                Collections.singletonList((GrantedAuthority)() ->"ROLE_ADMIN")));




    }


}
