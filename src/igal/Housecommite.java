package igal;
import java.io.Serializable;
@SuppressWarnings("serial")
public class Housecommite implements Serializable{
    private String app;
    private int Tenant_num;
    private String month;
    private String special;
    private String choice;
//--------------------------------------------------------------------------//    
    public Housecommite(String app1, int Tenant_num1, String month1,String special1,String choice1) {
        app= app1;
        Tenant_num=Tenant_num1;
        month = month1;
        special=special1;
        choice=choice1;
    }
//--------------------------------------------------------------------------//    
    public String GetChoice() {
        return choice; 
 }
//--------------------------------------------------------------------------//    
    public void SetChoice(String choice1) {
        choice = choice1;
    }
//--------------------------------------------------------------------------//       
    public String GetSpecial() {
        return special;
    }
//--------------------------------------------------------------------------//    
    public void SetSpecial(String special1) {
        special = special1;
    }
//--------------------------------------------------------------------------//        
    public String GetApp() {
        return app;
    }
//--------------------------------------------------------------------------//    
    public void SetApp(String app1) {
        app = app1;
    }
//--------------------------------------------------------------------------//    
    public String GetMonth() {
        return month;
    }
//--------------------------------------------------------------------------//    
    public void SetMonth(String month1) {
        month = month1;
    }
//--------------------------------------------------------------------------//    
    public int getTenant_num() {
        return Tenant_num;
    }
//--------------------------------------------------------------------------//    
    public void setTenant_num(int Tenant_num1) {
        Tenant_num = Tenant_num1;
    }
}
