package DefectRegistrationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.validation.Valid;
import java.util.ArrayList;


@Controller

public class WebController {

    private DefectList defectList = new DefectList();
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    //@Autowired
    //private DefectOwnerService defectOwnerService;

    @Autowired
    private DefectOwnerRepository repository;

    @Autowired
    public WebController(InMemoryUserDetailsManager inMemoryUserDetailsManager)
    {
        this.inMemoryUserDetailsManager=inMemoryUserDetailsManager;
    }


    @GetMapping("/")
    public String showMenu()
    {
              return "index";
    }
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @GetMapping("/addDefect")
    public String showForm(Model model){
       // model.addAttribute("defectOwner",this.defectOwnerService.list());
        model.addAttribute("defectForm", new Defect());
        return "addDefect";
    }

    @PostMapping("/addDefect")
    public String showDefectListForm(Model model, @ModelAttribute("defectForm") Defect defect)
    {
        defectList.addDefect(defect);
        model.addAttribute("defectList", defectList.getDefectList());
        return "defectTable";
    }

    @GetMapping("/defectTable")
    public String showDefectListForm(Model model)
    {
        model.addAttribute("defectList", defectList.getDefectList());
        return "defectTable";
    }

    @GetMapping("/addDefectOwner")
    public String addDefectOwnerForm(Model model)
    {
        return "addDefectOwner";
    }
    @ModelAttribute
    public DefectOwner formBackingObject(){
        return new DefectOwner();
    }

    @PostMapping("/addDefectOwner")
    public String addDefectOwner(@ModelAttribute("user") @Valid DefectOwner defectOwner, BindingResult result, Model model){
        if (result.hasErrors()){
            //model.addAttribute("users", defectOwnerService.list());
            return "addDefectOwner";
        }
        //defectOwnerService.save(defectOwner);
        inMemoryUserDetailsManager.createUser(new User(defectOwner.getName(),"{noop}"+ defectOwner.getPassword(),new ArrayList<GrantedAuthority>()));
        repository.save(defectOwner);
        System.out.println("Result:");
        System.out.println(repository.findById(1L).get().getName());
        return "index";
        }

    @Bean
    public CommandLineRunner demo(DefectOwnerRepository repository){
        return (args) ->{
            repository.save(new DefectOwner("Dominik", "123"));
            System.out.println(repository.findById(1L));
        };



    }

}
