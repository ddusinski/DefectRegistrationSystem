package DefectRegistrationSystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;


@Controller
public class WebController {

    private DefectList defectList = new DefectList();
    private ArrayList<DefectOwner> defectOwnerList = new ArrayList<DefectOwner>();

    private void createDefectOwners()
    {
        defectOwnerList.add(new DefectOwner("Warbud","1111"));
        defectOwnerList.add(new DefectOwner("KARMAR","2222"));
    }

    @GetMapping("/")
    public String showMenu()
    {
        createDefectOwners();
        return "index";
    }

    @GetMapping("/addDefect")
    public String showForm(Model model){
        //Defect defect = new Defect();
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
        //System.out.println(defectOwnerList.get(0).getOwnerName());
       // System.out.println(defectOwnerList.get(0).getOwnerPassword());
    }

}
