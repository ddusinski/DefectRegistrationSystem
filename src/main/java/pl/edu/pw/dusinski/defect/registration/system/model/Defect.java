package pl.edu.pw.dusinski.defect.registration.system.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Base64;

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
    private byte[] image;

    public Defect() {
    }

    public Defect(String defectOwner, DefectType defectType, String description, String defectDate) {
        this.defectOwner = defectOwner;
        this.defectType = defectType;
        this.description = description;
        this.defectDate = defectDate;
        this.isRepaired = false;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDefectDate() {
        return defectDate;
    }

    public void setDefectDate(String defectDate) {
        this.defectDate = defectDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String image) {
        this.imagePath = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getEncodedImage() {
        return image == null ? "" : Base64.getEncoder().encodeToString(image);
    }

    public Boolean getRepaired() {
        return isRepaired;
    }

    public void setRepaired(Boolean repaired) {
        isRepaired = repaired;
    }
}
