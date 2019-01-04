package DefectRegistrationSystem;

import java.util.ArrayList;

public class DefectList {
    //todo ta klasa jest bez sensu kompletnie, poki ma tylko liste defectow to lepiej jawnie wszedzie dawac liste defectow
//    todo ponizsza zmienne moze byc w web controller po prostu i staraj sie pisac private nazwaInterfejsuKolekcji<> nazwaZmiennej = new nazwaImplementacji
//    todo czyli w tym przypadku private List<Defect> defectList = new ArrayList<>();
    private ArrayList<Defect> defectList = new ArrayList<Defect>();

    public DefectList(){

    }

    public void addDefect(Defect defect)
    {
        this.defectList.add(defect);
        //this.defectList.add(new Defect("Warbud", DefectType.Structure,"Wrong concrete"));
    }

    public void addDefect()
    {
        //this.defectList.add(new Defect("Warbud", DefectType.Structure,"Wrong concrete"));
    }

    public ArrayList<Defect> getDefectList() {
        return defectList;
    }
}
