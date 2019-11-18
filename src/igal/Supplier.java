package igal;
import java.io.Serializable;


//@SuppressWarnings("serial")
@SuppressWarnings("serial")
public class Supplier extends People implements Serializable {
 private String special;
 private String phone;
//--------------------------------------------------------------------------//    
 public Supplier(String special1, String phone1) {
	 super(-1, "un","un");
     special = special1;
     phone = phone1;
 }
//--------------------------------------------------------------------------//    
 public Supplier(int id1,String firstname1, String lastname1,String special1, String phone1) {
 	super(1, firstname1,lastname1);
 	id=id1;
    special= special1;
    phone = phone1;
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
 public String GetPhone() {
     return phone;
 }
//--------------------------------------------------------------------------//    
 public void SetPhone(String phone1) {
     phone = phone1;
 }
}
