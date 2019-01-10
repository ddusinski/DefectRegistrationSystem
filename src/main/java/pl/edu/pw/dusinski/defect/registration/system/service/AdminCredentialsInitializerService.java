package pl.edu.pw.dusinski.defect.registration.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import pl.edu.pw.dusinski.defect.registration.system.config.AdminConfiguration;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class AdminCredentialsInitializerService {

    @Autowired
    private AdminConfiguration adminConfiguration;
    @Autowired
    private UserDetailsManager userDetailsManager;

    @PostConstruct
    public void addAdminIfNotPresent() {
        userDetailsManager.createUser(new User(adminConfiguration.getUsername(),
                "{noop}" + adminConfiguration.getPassword(),
                Collections.singletonList((GrantedAuthority) () -> "ROLE_ADMIN")));

    }

}
