package DefectRegistrationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Collections;


@Controller
public class WebController {

    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    private DefectOwnerRepository defectOwnerRepository;

    @Autowired
    private DefectRepository defectRepository;

    @PostConstruct
    private void initMethod() {

        defectOwnerRepository.save(new DefectOwner("Warbud", "111"));
        inMemoryUserDetailsManager.createUser(new User("Warbud", "{noop}" + "111", Collections.emptyList()));

        defectOwnerRepository.save(new DefectOwner("KARMAR", "222"));
        inMemoryUserDetailsManager.createUser(new User("KARMAR", "{noop}" + "222", Collections.emptyList()));


        LocalDate date = LocalDate.now();
        defectRepository.save(new Defect("KARMAR", DefectType.Structure, "Wrong concrete", date.toString()));
        defectRepository.deleteAll();
        defectRepository.save(new Defect("KARMAR", DefectType.Installation, "Wrong lenght of cable", date.toString()));
        defectRepository.save(new Defect("KARMAR", DefectType.Structure, "Wrong concrete", date.toString()));
        date = LocalDate.now().minusDays(5);
        defectRepository.save(new Defect("Warbud", DefectType.Installation, "Wrong pipe", date.toString()));
        defectRepository.save(new Defect("Warbud", DefectType.Electric, "Wrong cable", date.toString()));
    }

    @GetMapping("/")
    public String showMenu() {
        return "index";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/addDefect")
    public String showForm(Model model) {
        model.addAttribute("defectOwner", this.defectOwnerRepository.findAll());
        model.addAttribute("defectForm", new Defect());

        return "addDefect";
    }

    @PostMapping("/addDefect")
    public String showDefectListForm(Model model, @ModelAttribute("defectForm") @Valid Defect defect, BindingResult result,
                                     @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            model.addAttribute("defectOwner", this.defectOwnerRepository.findAll());

            return "addDefect";
        }
        try {
            defect.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (Exception ie) {
        }
        defectRepository.save(defect);
        return "index";
    }

    @GetMapping("/defectTable")
    public String showDefectListForm(Model model, Principal principal) {

        if (principal.getName().equals("admin")) {
            model.addAttribute("defectList", defectRepository.findAll());
        } else {
            model.addAttribute("defectList", defectRepository.findByDefectOwner(principal.getName()));
        }
        return "defectTable";
    }

    @PostMapping("defectTable")
    public String showDefectListFormAfterDeletion(@ModelAttribute("deleteDefectId") Long deleteDefectId, Model model) {

        Defect defect = defectRepository.findById(deleteDefectId).get();
        defectRepository.delete(defect);
        model.addAttribute("defectList", defectRepository.findAll());
        model.addAttribute("information", "Defect number " + defect.getId() + " was successfully deleted");

        return "defectTable";
    }

    @GetMapping("/addDefectOwner")
    public String addDefectOwnerForm(Model model) {
        model.addAttribute("defectOwner", new DefectOwner());
        return "addDefectOwner";
    }

    @PostMapping("/addDefectOwner")
    public String addDefectOwner(@ModelAttribute("defectOwner") @Valid DefectOwner defectOwner, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addDefectOwner";
        }
        inMemoryUserDetailsManager.createUser(new User(defectOwner.getName(), "{noop}" + defectOwner.getPassword(), Collections.emptyList()));
        defectOwnerRepository.save(defectOwner);
        return "index";
    }

    @GetMapping("/seeDefect")
    public String editDefectForm(@RequestParam("editedDefectId") Long editedDefectId, Model model) {
        model.addAttribute("editedDefect", defectRepository.findById(editedDefectId).get());
        return "seeDefect";

    }

    @PostMapping("/seeDefect")
    public String confirmDefectReparation(@RequestParam("defectIsRepaired") boolean defectIsRepaired, @RequestParam("editedDefectId") Long editedDefectId, Model model) {

        Defect defect = defectRepository.findById(editedDefectId).get();
        defect.setRepaired(defectIsRepaired);
        defectRepository.save(defect);
        model.addAttribute("editedDefect", defect);

        return "seeDefect";
    }
}
