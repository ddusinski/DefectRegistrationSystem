package DefectRegistrationSystem;
//todo enum nie powinien być nested klasą, bo odwołujesz się do niego w innych miejscach, a jeśli nie to powinien być private, ale enumy i tak powinny być public i w innych klasach
//todo popraw formatowanie kodu, w intellij ctrl+shift+alt+l ustawia sie jak ma dzialac, ja mam whole file, i w optional wszystko zaznaczone
//todo potem tylko klikasz jak chcesz zformatowac ctrl+alt+l
enum DefectType{
    Electric,
    Structure,
    Installation
}


public class Defect {
    private String defectOwner;
    private DefectType defectType;
    private String description;
//todo jesli klasa nie ma pol final to nie potrzeba konstruktora z wartosciami, wystarczy defaultowy konstruktor, czyli oba ponizsze do wywalenia i dodanie setterow
//todo jesli klasa ma miec pola final bo ma byc immutable to drugi konstruktor jest bez sensu bo to wszystkie pola sa nullami
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
