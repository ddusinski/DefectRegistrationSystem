package DefectRegistrationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;


@Controller
public class WebController {

    private DefectList defectList = new DefectList();
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    private UserService userService;

    public ArrayList<DefectOwner> defectOwnerList = new ArrayList<DefectOwner>();

    @Autowired
    public WebController(InMemoryUserDetailsManager inMemoryUserDetailsManager)
    {
        this.inMemoryUserDetailsManager=inMemoryUserDetailsManager;
    }

    private void createDefectOwners()
    {
        //defectOwnerList.add(new DefectOwner("Warbud","1111"));
        //
        // 3defectOwnerList.add(new DefectOwner("KARMAR","2222"));

    }

    @GetMapping("/")
    public String showMenu()
    {
        createDefectOwners();
        return "index";
    }
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @GetMapping("/addDefect")
    public String showForm(Model model){
        //Defect defect = new Defect();
        model.addAttribute("defektOwners",this.userService.list());
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
        //model.addAttribute("addDefectOwnerForm", new DefectOwner());
        //model.addAttribute("users", userService.list());

        return "addDefectOwner";
    }
    @ModelAttribute
    public DefectRegistrationSystem.User formBackingObject(){
        return new DefectRegistrationSystem.User();
    }

    @PostMapping("/addDefectOwner")
    public String addDefectOwner(@ModelAttribute("user") @Valid DefectRegistrationSystem.User user, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("users", userService.list());
            return "addDefectOwner";
        }
        userService.save(user);
        inMemoryUserDetailsManager.createUser(new User(user.getName(),"{noop}"+ user.getPassword(),new ArrayList<GrantedAuthority>()));
        return "index";
    }

    /*
    @PostMapping("/addDefectOwner")
    public void addDefectOwner(Model model, @ModelAttribute("addDefectOwnerForm") DefectOwner owner){
        defectOwnerList.add(owner);
        //System.out.println(owner.getOwnerName());
        //System.out.println(owner.getOwnerPassword());

        inMemoryUserDetailsManager.createUser(new User(owner.getOwnerName(),"{noop}"+ owner.getOwnerPassword(),new ArrayList<GrantedAuthority>()));
    }
    */

}
