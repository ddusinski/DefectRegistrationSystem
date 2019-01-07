package DefectRegistrationSystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Controller
public class WebController {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    //private List<DefectOwner> defectOwnerList = new ArrayList<DefectOwner>();
    //private List<Defect> defectList = new ArrayList<Defect>();

    @Autowired
    private DefectOwnerRepository defectOwnerRepository;

    @Autowired
    private DefectRepository defectRepository;

    @Autowired
    public WebController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
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
                                     @RequestParam("file")MultipartFile file) {
        if (result.hasErrors()){
            model.addAttribute("defectOwner", this.defectOwnerRepository.findAll());

            return "addDefect";
        }
        try {
                defect.setImage(Base64.getEncoder().encodeToString(file.getBytes())); }
        catch (Exception ie){}

        defectRepository.save(defect);
        System.out.println(file.getOriginalFilename());

        model.addAttribute("defectList", defectRepository.findAll());
        return "defectTable";
    }

    @GetMapping("/defectTable")
    public String showDefectListForm(Model model) {
        model.addAttribute("defectList", (List<Defect>)defectRepository.findAll());
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
        inMemoryUserDetailsManager.createUser(new User(defectOwner.getName(), "{noop}" + defectOwner.getPassword(), new ArrayList<GrantedAuthority>()));
        defectOwnerRepository.save(defectOwner);
        return "index";
    }

}
