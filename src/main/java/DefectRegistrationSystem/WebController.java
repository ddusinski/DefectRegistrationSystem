package DefectRegistrationSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;


@Controller
public class WebController {
//todo ta lista chyba powinna być per usera a nie per aplikację, jeśli ja coś dodam, ty coś dodasz to mamy to samo, poczytaj jak działa
// scope session i zrób beana z takim scopem a potem trzymaj detale uzytkownika w jego sesji springowej
    private DefectList defectList = new DefectList();
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public ArrayList<DefectOwner> defectOwnerList = new ArrayList<DefectOwner>();

    @Autowired
    public WebController(InMemoryUserDetailsManager inMemoryUserDetailsManager)
    {
        this.inMemoryUserDetailsManager=inMemoryUserDetailsManager;
    }
//todo takie zeczy jak ponizsza metoda mozna zrobic raz, zrob publiczna methode np. init i daj jej adnotacje @PostConstruct -> metoda wywola sie raz po utworzeniu kontrolera
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
//        todo typo
        model.addAttribute("defektOwners",this.defectOwnerList);
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
        model.addAttribute("addDefectOwnerForm", new DefectOwner());


        return "addDefectOwner";
    }
    @PostMapping("/addDefectOwner")
    public void addDefectOwner(Model model, @ModelAttribute("addDefectOwnerForm") DefectOwner owner){
        defectOwnerList.add(owner);
        //System.out.println(owner.getOwnerName());
        //System.out.println(owner.getOwnerPassword());

        inMemoryUserDetailsManager.createUser(new User(owner.getOwnerName(),"{noop}"+ owner.getOwnerPassword(),new ArrayList<GrantedAuthority>()));


    }

}
