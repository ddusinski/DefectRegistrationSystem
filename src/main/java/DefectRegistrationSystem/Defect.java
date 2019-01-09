package DefectRegistrationSystem;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Can not be null, Please add Defect owner")
    private String defectOwner;

    private DefectType defectType;

    @NotEmpty(message = "Please enter description")
    private String description;

    @NotEmpty(message = "Please enter date")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private String defectDate;

    private String imagePath;

    private Boolean isRepaired;

    @Lob
    String image;
    public Defect(){}

    public Defect(String defectOwner, DefectType defectType, String description, String defectDate){

        this.defectOwner=defectOwner;
        this.defectType=defectType;
        this.description=description;
        this.defectDate=defectDate;
        this.isRepaired=false;
    }

    public DefectType getDefectType() {
        return defectType;
    }

    public void setDefectType(DefectType defectType) {
        this.defectType = defectType;
    }

    public String getDefectOwner() {
        return defectOwner;
    }

    public void setDefectOwner(String defectOwner) {
        this.defectOwner = defectOwner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDefectDate(String defectDate) {
        this.defectDate = defectDate;
    }

    public Long getId() {
        return id;
    }

    public String getDefectDate() {
        return defectDate;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String image) {
        this.imagePath = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getRepaired() {
        return isRepaired;
    }

    public void setRepaired(Boolean repaired) {
        isRepaired = repaired;
    }
}
