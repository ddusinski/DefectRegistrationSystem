package pl.dusinski.defectregistrationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import pl.dusinski.defectregistrationsystem.model.Defect;
import pl.dusinski.defectregistrationsystem.model.DefectOwner;
import pl.dusinski.defectregistrationsystem.model.DefectType;
import pl.dusinski.defectregistrationsystem.repository.DefectOwnerRepository;
import pl.dusinski.defectregistrationsystem.repository.DefectRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Collections;

@Service
public class LoadSampleData {
    @Autowired
    UserDetailsManager userDetailsManager;
    @Autowired
    private DefectOwnerRepository defectOwnerRepository;

    @Autowired
    private DefectRepository defectRepository;


    @PostConstruct
    private void initMethod() {

        defectOwnerRepository.save(new DefectOwner("Warbud", "111"));
        defectOwnerRepository.save(new DefectOwner("KARMAR", "222"));

        userDetailsManager.createUser(new User("Warbud", "{noop}" + "111", Collections.emptyList()));
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
