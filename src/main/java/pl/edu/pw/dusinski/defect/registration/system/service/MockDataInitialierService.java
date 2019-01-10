package pl.edu.pw.dusinski.defect.registration.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import pl.edu.pw.dusinski.defect.registration.system.model.Defect;
import pl.edu.pw.dusinski.defect.registration.system.model.DefectOwner;
import pl.edu.pw.dusinski.defect.registration.system.model.DefectType;
import pl.edu.pw.dusinski.defect.registration.system.repository.DefectOwnerRepository;
import pl.edu.pw.dusinski.defect.registration.system.repository.DefectRepository;

import javax.annotation.PostConstruct;

import java.time.LocalDate;
import java.util.Collections;

import static pl.edu.pw.dusinski.defect.registration.system.Profiles.MOCK;

@Service
@Profile(MOCK)
public class MockDataInitialierService {

    @Autowired
    private UserDetailsManager userDetailsManager;
    @Autowired
    private DefectOwnerRepository defectOwnerRepository;
    @Autowired
    private DefectRepository defectRepository;


    @PostConstruct
    private void initMethod() {
        defectOwnerRepository.save(new DefectOwner("Warbud", "111"));
        userDetailsManager.createUser(new User("Warbud", "{noop}" + "111", Collections.emptyList()));

        defectOwnerRepository.save(new DefectOwner("KARMAR", "222"));
        userDetailsManager.createUser(new User("KARMAR", "{noop}" + "222", Collections.emptyList()));

        LocalDate date = LocalDate.now();
        defectRepository.save(new Defect("KARMAR", DefectType.STRUCTURE, "Wrong concrete", date.toString()));
        defectRepository.deleteAll();
        defectRepository.save(new Defect("KARMAR", DefectType.INSTALLATION, "Wrong lenght of cable", date.toString()));
        defectRepository.save(new Defect("KARMAR", DefectType.STRUCTURE, "Wrong concrete", date.toString()));
        date = LocalDate.now().minusDays(5);
        defectRepository.save(new Defect("Warbud", DefectType.INSTALLATION, "Wrong pipe", date.toString()));
        defectRepository.save(new Defect("Warbud", DefectType.ELECTRIC, "Wrong cable", date.toString()));
    }
}
