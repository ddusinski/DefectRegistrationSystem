package pl.dusinski.defectregistrationsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.dusinski.defectregistrationsystem.model.Defect;
import pl.dusinski.defectregistrationsystem.model.DefectOwner;
import pl.dusinski.defectregistrationsystem.repository.DefectOwnerRepository;
import pl.dusinski.defectregistrationsystem.repository.DefectRepository;


import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;


@Controller
public class WebController {


    @Autowired
    UserDetailsManager userDetailsManager;
    @Autowired
    private DefectOwnerRepository defectOwnerRepository;

    @Autowired
    private DefectRepository defectRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);


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
            defect.setImage(file.getBytes());
        } catch (Exception ie) {
            LOGGER.info("Image can not be loaded");
        }
        defectRepository.save(defect);
        LOGGER.info("New Defect is added ID: {}, Defect Date: {}", defect.getId(), defect.getDefectDate());
        return "index";
    }

    @GetMapping("/defectTable")
    public String showDefectListForm(Model model, Principal principal) {

        if (principal != null && principal.getName().equals("admin")) {
            model.addAttribute("defectList", defectRepository.findAll());
        } else
            if(principal != null && !principal.getName().isEmpty())
            {
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


        userDetailsManager.createUser(new User(defectOwner.getName(), "{noop}" + defectOwner.getPassword(), Collections.emptyList()));
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

        if (defectIsRepaired){
            LOGGER.info("Defect ID: {} is repaired", defect.getId());
        }

        return "seeDefect";
    }
}
