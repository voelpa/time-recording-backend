package dto;

public class EmployeeAuthenticationDTO {
    
    public boolean isController;
    public String pw;
    
    public EmployeeAuthenticationDTO (boolean isController, String pw) {
        super();
        this.isController = isController;
        this.pw = pw;
    }
   
    public boolean isController() {
        return isController;
    }
    
    public String getPw() {
        return pw;
    }

}
