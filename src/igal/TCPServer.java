//מנגנון הסרבר: הסבר כללי
//ה- סרבר מופעל ראשון , ומחכה שאפליצקציית הקליינט יתחברו אליו
//כאשר ה- סרבר עולה יש לו כתובת שדרכה הוא מקבל מידע
//הקליינט שרוצה להתחבר ל סרבר צריך לשלוח בקשה לכתובת זו
//כתובת כזו מורכבת מ 2 חלקים:
// כתובת אייפי , שהיא מספר המייצג מחשב ברשת
//מספר שהוא פורט עליו מאזין ה סרבר
package igal;
import java.io.*; 
import java.net.*; 
class TcpServer { 
//@SuppressWarnings("resource")
@SuppressWarnings("resource")
public static void main(String argv[]) throws Exception 
//ה- MAIN 
//נמצא בתוך מחלקה כי בגאווה כל דבר הוא אובייקט ובפרט האובייקט הראשוני המכיל את ה מיין
    { 
	  ServerSocket s = null;
		try {
		    s = new ServerSocket(10000);//הפורט אליו מאזין
		   
		} catch(IOException e) {
		    System.out.println(e);
		    System.exit(1);
		}
		
		// שהסרבר תמיד יעבוד ויחכה לפניות מהמשתמש
		while (true) {
		    Socket incoming = null;
		 // יצירת משתנה שיזכור את הכתובת שצריך לחזור אלייה
		 			// יוצר מזכירה שתדבר עם הלקוח במקום השרת
		    try {
			incoming = s.accept(); //BLOCKING
			//מחכה לבקשת התחברות מהקליינט.
			//פעולה זו היא BLOCKING
			
		    } catch(IOException e) {
		    	//אם המשתמש הפסיק באמצע את השיחה בצורה לא תקינה
		    	
			System.out.println(e);
			continue;
		    }

		    new SocketHandler(incoming).start();
		    //כאן תתבצע ההרצה באמצעות המתודה START מריצה את הטרד
		    //ועוברת לפונקציית RUN  שבתוך הסוקטהדלרר- באופן אסינכרוני
		    System.out.println("Server is on");

		}
    } 
} 