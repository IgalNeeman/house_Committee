package igal;
import java.io.Serializable;
//@SuppressWarnings("serial")
@SuppressWarnings("serial")
public class Tenant extends People implements Serializable   {
    private String username;
    private String password;
    private int pay; 
    private String mail;
//--------------------------------------------------------------------------//   
    public Tenant(String username1, int pay1, String password1, String mail1) {
    	super(-1, "un","un");
        username = username1;
        password=password1;
        pay = pay1;
        mail = mail1;
    }
//--------------------------------------------------------------------------//    
    public Tenant(int id1,String firstname1, String lastname1, String username1, String password1, int pay1,String mail1) {
    	super(1, firstname1,lastname1);
    	id=id1;
        username = username1;
        password=password1;
        pay = pay1;
        mail = mail1;
    }
//--------------------------------------------------------------------------//    
    public String GetUsername() {
        return username;
    }
//--------------------------------------------------------------------------//    
    public void SetName(String username1) {
        username = username1;
    }
//--------------------------------------------------------------------------//    
    public String GetPassword() {
        return password;
    }
//--------------------------------------------------------------------------//    
    public void SetPassword(String password1) {
        password = password1;
    }
//--------------------------------------------------------------------------//    
    public String GetMail() {
        return mail;
    }
//--------------------------------------------------------------------------//    
    public void SetMail(String mail1) {
        mail = mail1;
    }
//--------------------------------------------------------------------------//    
    public int GetPay() {
        return pay;
    }

}
