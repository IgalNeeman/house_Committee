package igal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
import java.time.Month;
@SuppressWarnings("unused")

//ו הסוקט-הדלר שייצרנו הוא // מטרותו הוא לקבל את הפרטים של התקשורת מול הלקוח


public class SocketHandler extends Thread {
	Socket incoming;
	Driver Sql;
	static int count=1;
	int counter=0;
	SocketHandler(Socket _in) {
		// הכנסת הערכים כאן חוסכת יצירת מקום מיותר בזיכרון , ויוצרת רק כאשר באמת נכנסים
				// ערכים
		this.incoming = _in;
		//this ל 2 שימושים עיקריים
		//1 לאפשר להעביר פרמטרים לשיטות עם שמות משמעותיים
		//2 קריאה מבני אחד לאחר

	}
	@SuppressWarnings("static-access")
	
	//  trd- מתחיל להפעיל את המזכירה
	public void run() {
	//	בשפת JAVA הרי ניתן לרשת ממחלקה אחת בלבד
	//	בצורה הנוכחית, אם נרצה שמחלקה מסויימת תפעל ב- thread נפרד אין לנו אפשרות, במקרה הצורך, לרשת ממחלקה נוספת
	//	לכן נממש את הממשק Run
		Object clientObject;
		ObjectInputStream inFromClient = null;
		DataOutputStream outToClient = null;
		try {
			// פותחים 2 "צינורות" מוצלבים: אחד מקבל מידע מהשרת ,אחד שולח מידע לשרת
						// אחד מקבל מידע מהשרת // קריאה של סטרינגים
			inFromClient = new ObjectInputStream(incoming.getInputStream());
			outToClient = new DataOutputStream(incoming.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String one;
		Sql.ConectingToSQL();
		double sum = 0;
		double result = 0;
		try {
			Info Detail = null;
			Housecommite House_Commite=null;
			Tenant tenant=null;
			Supplier supplier=null;
			while (true) {
				clientObject = inFromClient.readObject();
				if (clientObject instanceof Info)
					//instanceof כדי לברר אם אובייקט הוא מטיפוס מסויים, הישתמשתי ב
				//יוחזר TRUE אם CLIENTOBJECT הוא מטיפוס של INFO
					//או אחד מהיורשים שלו
				{
					Detail = (Info) clientObject;	
					  if ((Detail.GetCategory().matches("0")))
							  {
						       outToClient.writeBytes("bye bye!"+ "\n");
							  }
	               if ((Detail.GetCategory().matches("1") && Detail.GetSelect().matches("1")) && Detail.GetChoice().matches("non")) {
						if (Detail.GetPassword().matches(Sql.SelectHouseCommite(Detail.GetUsername()))) 
							outToClient.writeBytes("Wellcom!"+ "\n"); //אם שם המשתמש והסיסמה של וועד הבית נכונים
						else {
						outToClient.writeBytes("Wrong username/password! please try again--->" + "\n");
						}						
						}					
					if ((Detail.GetCategory().matches("2") && Detail.GetSelect().matches("1")) && Detail.GetChoice().matches("non")) {		
						if (Detail.GetPassword().matches(Sql.SelectTenant(Detail.GetUsername()))) 
							outToClient.writeBytes("Wellcom!" + "\n");//אם שם המשתמש והסיסמה של הדייר בבניין נכונים
						else {
						outToClient.writeBytes("Wrong username/password! please try again--->" + "\n");
						}
					}
					 if ((Detail.GetCategory().matches("1") && Detail.GetSelect().matches("2"))&& Detail.GetChoice().matches("non")) {
							Sql.UpdateHouseCommitePassword(Detail.GetUsername(), Detail.GetPassword());
							outToClient.writeBytes("Password changed sucssefuly :)" + "\n");//הכנסת סיסמה חדשה של וועד הבית
					 }
					 if ((Detail.GetCategory().matches("2") && Detail.GetSelect().matches("2"))&& Detail.GetChoice().matches("non")) {
						Sql.UpdateTenantPassword(Detail.GetUsername(), Detail.GetPassword());
						outToClient.writeBytes("Password has changed sucssefuly :)" + "\n");//הכנסה של סיסמה חדשה של דייר
					}
				if (Detail.GetCategory().matches("2") && Detail.GetSelect().matches("1") && Detail.GetChoice().matches("1")) {
					outToClient.writeBytes(Sql.SelectMonthPay(Detail.GetUsername()) + "\n");////הצגת התשלום של הדייר
					}
				}
				if (clientObject instanceof Housecommite) {
					House_Commite = (Housecommite) clientObject;			
				   switch(House_Commite.GetChoice())
				   {
				   //מציאת הבחירה של היוזר
				case "1":{ 
					//הצגה ספציפית של תשלום דייר
					String ans=Sql.SelectTenantPay(House_Commite.GetApp());
			     	outToClient.writeBytes(ans+ "\n");
					break;
					     }
				case "2":{ 
					//הצגה של כל התשלומים בבניין
					String ans=Sql.SelectAllPay();
					outToClient.writeBytes(ans+ "\n");
					break;
					     }
				case "4":{ 
					//עדכון ספציפי של תשלום דייר
					int month = Integer.parseInt(House_Commite.GetMonth());
					String month2=(Month.of(month).name());	
					int app = Integer.parseInt(House_Commite.GetApp());
					Sql.UpdatePay(month2,app,month);
					outToClient.writeBytes("The payment updated sucssefuly :)"+ "\n");
					break;
					     }
				case "5":{ //מחיקת תשלום דייר
					int month = Integer.parseInt(House_Commite.GetMonth());
					String month2=(Month.of(month).name());	
					int app = Integer.parseInt(House_Commite.GetApp());
					Sql.UpdatePayD(month2,app);
					outToClient.writeBytes("The payment deleted sucssefuly :)"+ "\n");
					break;
					     }
				case "6":{ // ההכנסה החודשית של הבניין
					int month = Integer.parseInt(House_Commite.GetMonth());
					String month2=(Month.of(month).name());
					String ans=Sql.SelectAllSum(month2);
					outToClient.writeBytes(ans+ "\n");
					break;
					     }
				case "7":{ //הצגת ספקים
					String answer=(Sql.SelectSupplier(House_Commite.GetSpecial()));
					outToClient.writeBytes(answer+ "\n");
					break;
					     }
				default:{break;}
				}
				}	
				if (clientObject instanceof Tenant) {
					tenant = (Tenant) clientObject;
					{
					if(count==1)
					{
					Sql.DropTable();
					Sql.CreateTable();
					}
					Sql.InsertTenant(count++, tenant.GetFirstName(),tenant.GetLastName(),tenant.GetUsername(),tenant.GetPassword(),tenant.GetMail(),tenant.GetPay());
					}
					}
				if (clientObject instanceof Supplier) { //הכנסת ספק חדש
					supplier = (Supplier) clientObject;
					if(supplier.GetFirstName().matches((Sql.CheckIfExist(supplier.GetPhone()))))
					{
					outToClient.writeBytes("The supplier you entered already exist!"+ "\n");
					}
					else
					{
					counter=Sql.SelectMax();
					counter++;
					Sql.InsertSupplier(counter,supplier.GetFirstName(),supplier.GetLastName(),supplier.GetPhone(),supplier.GetSpecial());
					outToClient.writeBytes("The supplier entered sucssesfuly :)"+ "\n");
					}	
				}
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
