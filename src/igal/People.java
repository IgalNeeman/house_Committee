package igal;
import java.io.Serializable;


@SuppressWarnings("serial")
public class People implements Serializable {
 protected static int id;
 protected String firstName;
 protected String lastName;
//--------------------------------------------------------------------------//    
 public People(int id1, String firstName1, String lastName1) {
     id = id1;
     firstName = firstName1;
     lastName = lastName1;
 }
//--------------------------------------------------------------------------//    
 public String GetFirstName() {
     return this.firstName;
 	//this ל 2 שימושים עיקריים
		//1 לאפשר להעביר פרמטרים לשיטות עם שמות משמעותיים
		//2 קריאה מבני אחד לאחר

 }
//--------------------------------------------------------------------------//    
 public void SetFirstName(String firstName1) {
     firstName = firstName1;
 }
//--------------------------------------------------------------------------//    
 public String GetLastName() {
     return this.lastName;
 }
//--------------------------------------------------------------------------//    
 public void SetLastName(String lastName1) {
     lastName = lastName1;
 }
//--------------------------------------------------------------------------//    
 public int GetId() {
     return People.id;
 }
//--------------------------------------------------------------------------//    
 public void SetId(int id1) {
     id = id1;
 }
}
