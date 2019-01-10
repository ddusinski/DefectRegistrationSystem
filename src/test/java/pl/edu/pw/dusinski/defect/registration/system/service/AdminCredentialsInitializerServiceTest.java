package pl.edu.pw.dusinski.defect.registration.system.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import pl.edu.pw.dusinski.defect.registration.system.config.AdminConfiguration;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminCredentialsInitializerServiceTest {

    @InjectMocks
    private AdminCredentialsInitializerService service = new AdminCredentialsInitializerService();
    @Mock
    private AdminConfiguration adminConfiguration;
    @Mock
    private UserDetailsManager userDetailsManager;

    @Test
    public void shouldAddAdminUserDefinedInConfiguration() {
        String username = "admin";
        String password = "password";
        String hashingFunction = "{noop}";
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        when(adminConfiguration.getUsername()).thenReturn(username);
        when(adminConfiguration.getPassword()).thenReturn(password);
        service.addAdminIfNotPresent();

        verify(userDetailsManager, atLeastOnce()).createUser(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue().getUsername()).isEqualTo(username);
        assertThat(userArgumentCaptor.getValue().getPassword()).isEqualTo(hashingFunction + password);
        assertThat(userArgumentCaptor.getValue().getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN")))
                .isTrue();
    }
}
