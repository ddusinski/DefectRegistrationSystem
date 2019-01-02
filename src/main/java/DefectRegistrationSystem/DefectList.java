package DefectRegistrationSystem;

import java.util.ArrayList;

public class DefectList {
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
        this.defectList.add(new Defect("Warbud", DefectType.Structure,"Wrong concrete"));
    }

    public ArrayList<Defect> getDefectList() {
        return defectList;
    }
}
