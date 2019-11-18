//הגדרות: מנגנון שרת-לקוח
//עד היום האפליקציות שנכתבו היו STAND-ALONE 
//כלומר , לא תקשרו עם אף-אחד, בתוכנית זו ארצה לכתוב תוכנית שיודעת "לדבר" עם תוכניות אחרות כדוגמא:
//מערכת וועד-בית:
//ייש ישות מרכזית המחזיקה את הנתונים של כל הדיירים, ויש ישויות המבקשות מידע מישות מרכזית זו
//הישות המרכזית המספקת מידע נקראת "שרת" )SERVER(
//יישות הקצה המבקשת מידע נקראת "לקוח" (CLIENT)
//שרת יכול לספק מידע לכמה לקוחות

package igal;
import java.io.*;
import java.net.*;
import java.time.Month;
import java.util.Scanner; // הספרייה שמכילה את האובייקט שקורא מן הקלט
@SuppressWarnings("unused")
//בעקבות שורה זו הקומפיילר לא יציג אזהרות שקשורות ל unused
class TCPClient_with_serialized {	
		public static void main(String argv[]) throws Exception {
			//EXCEPTION נהוג ששיטה שעלולה לייצר חריגה תצהיר על כך בקוד,
			//כדי שמתכנת אחר שישתמש בה ידע להכין קוד שיטפל בחריגה
		String Result = null;
		Object Supplier=null;
		Supplier supplier=null;
		Object HouseCommite = null;
		Object Tenant=null;
		Tenant tenant=null;
		Housecommite House_Commite=null;
		Object detail = null;
		Info Detail=null;
		String Select="non",app_num="non",month="non",speciality="non",choice="non";
		int Tenant_num=-1;
		int id=0;
		String Category = null, username=null, password=null; //category עבור הסוייץקייס יציין את הבחירה בין הדיירים
		@SuppressWarnings("resource")
		//התחברות לסרבר הנמצא בכתובת המצויינת ומאזין לפורט זה
		Socket clientSocket = new Socket("localhost", 10000);
		@SuppressWarnings("resource")
		// inFromUser המזכירה שומעת את מה שהלקוח מדבר
		
		Scanner inFromUser = new Scanner(System.in); //יצירת אובייקט לקליטת נתונים מהמקלדת
		//system.in מקור קובץ BYTE מהמקלדת
		
		// פותחים 2 "צינורות": אחד מקבל מידע מהשרת ,אחד שולח מידע לשרת
					// אחד שולח מידע לשרת
		ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());//
		//bufferedReader אובייקט שיודע לקרוא נתון מרצף תווים
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//InputStreamReader אובייקט המקבל 'בייתי'ם' וממיר אותם לתווים
		
		// שתמיד יהיה קשר עם השרת
		while (true) {
			choice="non";
			Select="non";
			System.out.println("Please Select your Category:");//הדפסה למסך לפי בחירת הקטגורייה
			System.out.println("=>1 House Committee");
			System.out.println("=>2 Tenant");
			System.out.println("=>0 To quit");
			// inFromUser המזכירה שומעת את מה שהלקוח מדבר

			Category= inFromUser.nextLine(); //NEXTLINE קליטת מחרוזת עד האנטר כולל רווחים בדומה ל- GETS
			
			//ברגע שנבחר על ידי המשתמש אחד מהשניים - תוצג למשתמש האפשרות הבאה:
			//שבו על המשתמש יהיה לבחור 1 עבור מנהל וועד הבית מנהל של התוכנה
			//בחירה מס 2 של הדיירים
			// בחירה מספר 0 ליציאה
			switch (Category) {
			case "0"://עבור יציאה
			{
				String choice2="non";
				Detail = new Info(username,password,Category,choice2,Select);
				//WRITEOBJECT - // שולח את מה הבחירה  לשרת
				outToServer.writeObject(Detail);
				String modifiedSentence = inFromServer.readLine();//יציאה0 מהתוכנית
				System.out.println("FROM SERVER: " + modifiedSentence);
				//modifiedSentence   - זהו צינור שמציין את ההודעה שחוזרת מהסרבר
			//קורא את התשובה שהשרת  החזיר
				if(modifiedSentence.matches("bye bye!"))break;
			}
			case "1":
			{				//בחירה 1 עבור וועד הבית
	
				System.out.println(" Hello dear HouseCommite ! ");
				System.out.println("=>1 Login"); //עבור הכניסה
				System.out.println("=>2 Change password"); //עבור עדכון סיסמה
				String choice2 = inFromUser.nextLine();
				if (choice2.matches("1")) {//כניסה עם שם משתמש וסיסמה
					System.out.println("Please enter Username--->");
					username = inFromUser.nextLine(); //NEXTLINE קליטת מחרוזת עד האנטר כולל רווחים בדומה ל- GETS
					System.out.println("Please enter Password--->");
					password = inFromUser.nextLine();
					Detail = new Info(username,password,Category,choice2,Select);
					outToServer.writeObject(Detail);
					String modifiedSentence = inFromServer.readLine();
					System.out.println("FROM SERVER: " + modifiedSentence);
					if (modifiedSentence.matches("Wellcom!")) 
					{ // בדיקה אם שם המשתמש והסיסמה נכונים
						while(!choice2.matches("0"))
						{
								//לולאה שבודקת קלט עבור וועד הבית לפי בחירה
						app_num="non";
						month="non";
						speciality="non";
						Tenant_num=-1;
						System.out.println("Please select what would you like to do:");
						System.out.println("=>1 Show a specific tenant payments");
						System.out.println("=>2 Show all of the payments in your building");
						System.out.println("=>3 Set up new building");
						System.out.println("=>4 Update a specific tenant payment");
						System.out.println("=>5 Delete tenant payment");
						System.out.println("=>6 Monthly income of your building");
						System.out.println("=>7 Show Suppliers");
						System.out.println("=>8 Enter a new supplier");
						System.out.println("=>0  To exit");
						choice2= inFromUser.nextLine();
						switch (choice2) // סואיץקייס בשביל לבדוק את הקלט שנבחר
						{
						case"1":
						{// הצגת דייר ספציפי ששילם לפי מספר דירה
							System.out.println("Please enter the appartment number--->");
							app_num=inFromUser.nextLine();
							House_Commite = new Housecommite(app_num,Tenant_num,month,speciality,choice2);
							outToServer.writeObject(House_Commite);
							modifiedSentence = inFromServer.readLine();
							System.out.println("FROM SERVER: " + modifiedSentence);
							break;
						}
						
						case"2":
						{// הצגת כל התשלומים בבניין
							House_Commite = new Housecommite(app_num,Tenant_num,month,speciality,choice2);
							outToServer.writeObject(House_Commite);
							modifiedSentence = inFromServer.readLine();
							System.out.println("FROM SERVER: " + modifiedSentence);
							break;
						 }
						
						case"3":
						{// הקמה של בניין חדש
							System.out.println("How many people will live here?");
							Tenant_num=inFromUser.nextInt();
							for(int i=1;i<Tenant_num+1;i++)
								//הערה:
								//ב- JAVA
								//אין הפרדה בין הצהרה למימוש
								//פונקציות " גלובליות" נכתבות בתוך CLASS הראשי כ- STATIC
								//כי גם ה- MAIN סטטי
								//אין חשיבות לסדר כתיבת הפוקניות
							{
								System.out.println("enter tenant Firstname ?");	
								String firstname=inFromUser.next();
								System.out.println("enter tenant Lastname");
								String Lastname=inFromUser.next();
								System.out.println("enter tenant Username");
								String Username=inFromUser.next();
								System.out.println("enter tenant Password");
								String Password=inFromUser.next();
								System.out.println("enter tenant monthly payment");
								int payment=inFromUser.nextInt();
								System.out.println("enter tenant Email?");
								String Email=inFromUser.next();
								tenant = new Tenant(i,firstname,Lastname,Username,Password,payment,Email);
								//הערה\תזכורת: כאשר אובייקט מכיל אובייקט אחר, הוא למעשה מכיל הפניה לאובייקט המוכל
								//(אלא אם המוכל נוצר ע"י NEW)
								outToServer.writeObject(tenant);	
							}
							break;
						}
						
						case"4":
						{//  לפי חודש
							//עדכון ספציפי תשלום דייר
							System.out.println("Please enter the appartment number--->");
							app_num=inFromUser.nextLine();
							System.out.println("Please enter the number of the month you would like to update--->");
							month=inFromUser.nextLine();
							House_Commite = new Housecommite(app_num,Tenant_num,month,speciality,choice2);
							outToServer.writeObject(House_Commite);
							modifiedSentence = inFromServer.readLine();
							System.out.println("FROM SERVER: " + modifiedSentence);
							break;
						}
						
						case"5":
						{//  מחיקת \עדכון תשלום דייר
							System.out.println("Please enter the appartment number--->");
							app_num=inFromUser.nextLine();
							System.out.println("Please enter the number of the month you would like to update--->");
							month=inFromUser.nextLine();
							House_Commite = new Housecommite(app_num,Tenant_num,month,speciality,choice2);
							outToServer.writeObject(House_Commite);
							modifiedSentence = inFromServer.readLine();
							System.out.println("FROM SERVER: " + modifiedSentence);
							break;
					    }
						
						case"6":
						{// בדיקה של הכנסה חודשית של הבניין 
							//הצגה של הכנסות הבנייין לפי חודשים
							System.out.println("Please enter the number of the month--->");
							month=inFromUser.nextLine();
							House_Commite = new Housecommite(app_num,Tenant_num,month,speciality,choice2);
							outToServer.writeObject(House_Commite);
							modifiedSentence = inFromServer.readLine();
							System.out.println("FROM SERVER: " + modifiedSentence);
							break;
					   }
						
						case"7":
						{// הצגת הספקים
						    System.out.println("Please enter a speciality of supplier:electrician/plumber/renovation--->");
						    speciality=inFromUser.nextLine();
						    House_Commite = new Housecommite(app_num,Tenant_num,month,speciality,choice2);
						    outToServer.writeObject(House_Commite);
					    	modifiedSentence = inFromServer.readLine();
						    System.out.println("FROM SERVER: " + modifiedSentence);
						    break;
					    }
						case"8":
						{// הכנסה של ספק חדש
							System.out.println("Please enter the firstname--->");
							String SupName=inFromUser.next();
							System.out.println("Please enter the Lastname--->");
							String SupLast=inFromUser.next();
							System.out.println("Please enter the specializes:electrician/plumber/renovation--->");	
							String spec=inFromUser.next();
							System.out.println("Please enter the phone number--->");	
							String phone=inFromUser.next();
							supplier = new Supplier(id,SupName,SupLast,spec,phone);
							outToServer.writeObject(supplier);
							modifiedSentence = inFromServer.readLine();
							System.out.println("FROM SERVER: " + modifiedSentence);
							break;
						 }
						default:
						{
							break;
								}
							}
						}
					}
					if(choice2.matches("0")) 
					break;
						else if(choice2.matches("2")) 
						{//ייצירה של סיסמה חדשה
							System.out.println("Please enter Username--->");
						username = inFromUser.nextLine();
							System.out.println("Please enter your New Password--->");
						password = inFromUser.nextLine();
						Detail = new Info(username,password,Category,choice2,Select);
						outToServer.writeObject(Detail);
						String modifiedSentence1 = inFromServer.readLine();
						System.out.println("FROM SERVER: " + modifiedSentence1);
						break;
							}
						}
					}
			case "2": //בחירה עבור הדייר
			{
				String modifiedSentence;
				System.out.println("Hello dear Tenant ! ");
				System.out.println("->1 Login");
				System.out.println("->2 Change your password");
				String choice2 = inFromUser.nextLine();
				if (choice2.matches("1")) {//כניסה עם שם משתמש וסיסמה
					System.out.println("Please enter Username--->");
					username = inFromUser.nextLine();
					System.out.println("Please enter Password--->");
					password = inFromUser.nextLine();
					Detail = new Info(username,password,Category,choice2,Select);
					outToServer.writeObject(Detail);
					modifiedSentence = inFromServer.readLine();
					System.out.println("FROM SERVER: " + modifiedSentence);
					if (modifiedSentence.matches("Wellcom!")) {// אם שם המשתמש והסיסמה נכונים
						System.out.println("Please select what would you like to do");
						System.out.println("->1 Show your payments");
						 Select= inFromUser.nextLine();
						 if (Select.matches("1")) {
							Detail = new Info(username,password,Category,choice2,Select);
							outToServer.writeObject(Detail);
							//במידה והמשתמש "הדייר" בחר 1- הסרבר ישלוף מהדאטה בייס את הנתונים שמופיעים בתוך DETAIL
							//ווויציג לדייר את כל התשלןומים שהוא שילם על פי חודשים
							modifiedSentence = inFromServer.readLine();
							System.out.println("FROM SERVER: " + modifiedSentence);
						}
					}
				}
				else if (choice2.matches("2")) {//יצירת סיסמה חדשה
					System.out.println("Please enter Username--->");
					username = inFromUser.nextLine();
					System.out.println("Please enter your New Password--->");
					password = inFromUser.nextLine();
					Detail = new Info(username,password,Category,choice2,Select);
					outToServer.writeObject(Detail);
					modifiedSentence = inFromServer.readLine();
					System.out.println("FROM SERVER: " + modifiedSentence);
					//קבלת טקסט מהסרבר
					break;
				}
			}
			
			     default:{break;}
			}
				if(Category.matches("0"))
				break;
			}
		}
	}
