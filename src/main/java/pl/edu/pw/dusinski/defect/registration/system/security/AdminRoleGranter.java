package pl.edu.pw.dusinski.defect.registration.system.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import java.util.Collections;

import static pl.edu.pw.dusinski.defect.registration.system.Profiles.MOCK;

@Configuration
@Profile(MOCK)
public class AdminRoleGranter {

    public void addAdminRole() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                        SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );
    }


}
