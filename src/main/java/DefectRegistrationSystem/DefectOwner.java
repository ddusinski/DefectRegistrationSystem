package DefectRegistrationSystem;

public class DefectOwner {
    private String ownerName;
    private String ownerPassword;

    public DefectOwner (String name, String password)
    {
        this.ownerName=name;
        this.ownerPassword=password;
    }
    public DefectOwner ()
    {};

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerPassword() {
        return ownerPassword;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }
}
