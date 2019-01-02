package DefectRegistrationSystem;

enum DefectType{
    Electric,
    Structure,
    Installation
}


public class Defect {
    private String defectOwner;
    private DefectType defectType;
    private String description;

    public Defect(String defectOwner, DefectType defectType, String description)
    {
        this.defectOwner = defectOwner;
        this.defectType=defectType;
        this.description = description;
    }

    public Defect()
    {
        this.defectOwner = null;
        this.defectType=null;
        this.description = null;
    }
    public DefectType getDefectType() {
        return defectType;
    }

    public void setDefectOwner(String defectOwner) {
        this.defectOwner = defectOwner;
    }

    public String getDefectOwner() {
        return defectOwner;
    }

    public void setDefectType(DefectType defectType) {
        this.defectType = defectType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
