package igal;
import java.io.Serializable;
//@SuppressWarnings("serial")
@SuppressWarnings("serial")
public class Info implements Serializable{
private String username;
private String password;
private String Category;
private String Select;
/**
 * 
 */
private String Choice;
//--------------------------------------------------------------------------//    
public Info(String username1, String password1, String Category1,String Select1,String Choice1) {
    username = username1;
    password = password1;
    Category = Category1;
    Select= Select1;
    Choice=Choice1;
}
//--------------------------------------------------------------------------//    
public String GetUsername() {
    return this.username;
	//this ל 2 שימושים עיקריים
	//1 לאפשר להעביר פרמטרים לשיטות עם שמות משמעותיים
	//2 קריאה מבני אחד לאחר

}
//--------------------------------------------------------------------------//    
public void SetUsername(String username1) {
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
public String GetCategory() {
    return Category;
}
//--------------------------------------------------------------------------//    
public void SetCategory(String Category1) {
  Category = Category1;
}
//--------------------------------------------------------------------------//    
public String GetSelect() {
    return Select;
}
//--------------------------------------------------------------------------//    
public void SetSelect(String Select1) {
    Select=Select1;
}
//--------------------------------------------------------------------------//    
public String GetChoice() {
    return Choice;
}
//--------------------------------------------------------------------------//    
public void SetChoice(String Choice1) {
    Choice = Choice1;
}
}