package igal;
import java.sql.*;
import java.util.Scanner;
public class Driver
	{
    private static Connection connect;
    public Driver() {}
    //������ ����� ������� ��� �- DB
    //executeQuery � ������ ResultSet ���� ������ SELECT
//executeUpdate � ����� ������� �������� �� ���� �������. ������ �� ���� ������ ������� �������
//execute � ����� �� ��� �� ������ TRUE �� ���� ������� ��� ResultSet �- FALSE ����
//���� ����� ����� ����� �� ��� ������� ����� ������� ���� �����

 //--------------------------------------------------------------------------//
    public static String SelectTenant(String username) { // ��� ����� �� ������ ������� �� �����
    	String pass="";
        try {
        	//�������  �� PreparedStatement ������ �� �� ����������� ������� ���� �-
        	// PreCompile ���� ���� ����� ������ ��� ������ �� ���� ������ ��� �����
            //PreparedStatement ����� �- Statement ���� �� ���� ����� �������� ������� ����, ����� ���
        	PreparedStatement statement = connect.prepareStatement("select password from tenant where username = "+"'"+username+"'");
            ResultSet result = statement.executeQuery();
            //Statement ��� ����� ������� ������ ������� �� �����
            //����� ����� �� �- DB ���� �� ������� �� ��� ���� ���� �����

            	while(result.next()) {
                pass=result.getString(1); }
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
          return pass;
    }
    
 //--------------------------------------------------------------------------//
    public static String SelectHouseCommite(String username) {//��� ����� �� ������  ������� �� ���� ����
    	String pass="";
        try {
            PreparedStatement statement = connect.prepareStatement("select password from housecommite where username = "+"'"+username+"'");
            ResultSet result = statement.executeQuery();
            	while(result.next()) {
               pass=result.getString(1); }
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
        return pass;
    }
//--------------------------------------------------------------------------//     
    public static String SelectSupplier(String specializes) {//���� ������
    	String answer1="",answer2="";
    	int i=1;
        try {
            PreparedStatement statement = connect.prepareStatement("select * from supplier where specializes = "+"'"+specializes+"'");
            ResultSet result = statement.executeQuery();
            	while(result.next())
            	{
            		 while (i<5)
                	 {
                        answer1=result.getString(i++);
                    	   answer2+= answer1 + " , " ;
                	 }       
            }
        } catch (SQLException var2) {
            var2.printStackTrace();
        } 
        return answer2;
    }
//--------------------------------------------------------------------------//    
  public static void UpdateTenantPassword(String username,String password) { //����� ����� ���� ���� �����
        String sqlupdate = "UPDATE tenant SET password=?  WHERE username =? ";
        try {
            PreparedStatement pst = connect.prepareStatement(sqlupdate);
            pst.setString(1,password);
            pst.setString(2,username);
            pst.executeUpdate();
          //executeUpdate � ����� ������� �������� �� ���� �������. ������ �� ���� ������ ������� �������
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
//--------------------------------------------------------------------------//    
    public static void UpdateHouseCommitePassword(String username,String password) { //����� ����� ���� ���� ���� ����
        String sqlupdate = "UPDATE housecommite SET password=?  WHERE username =? ";
        try {
            PreparedStatement pst = connect.prepareStatement(sqlupdate);
            pst.setString(1,password);
            pst.setString(2,username);
            pst.executeUpdate();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
 //--------------------------------------------------------------------------//    
    //�������� ������ ���� ������
    public static void InsertTenant(int id,String firstname,String lastname,String username,String password,String email,int monthlypayment) {
        String sqlInsert = "insert into tenant (id,firstname,lastname,username,password,email,appartmentnum,monthlypayment) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = connect.prepareStatement(sqlInsert);
            pst.setInt(1, id);
            pst.setString(2, firstname);
            pst.setString(3, lastname);
            pst.setString(4, username);
            pst.setString(5, password);
            pst.setString(6,email);
            pst.setInt(7, id);
            pst.setInt(8, monthlypayment);
            pst.execute();
           
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
//--------------------------------------------------------------------------//
    public static void DropTable() { //�������� ������ ����
        String sqlDrop = "drop table tenant";
        try {
            PreparedStatement pst = connect.prepareStatement(sqlDrop);
            pst.execute();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
//--------------------------------------------------------------------------//      
    public static void CreateTable() {//�������� ������� ������ �� ���� ������� ���� ���� �������� ������
        String sqlcreate = "\r\n" + 
        		"CREATE TABLE tenant (\r\n" + 
        		"    ID int,\r\n" + 
        		"    FirstName varchar(255),\r\n" + 
        		"    LastName varchar(255),\r\n" + 
        		"    userName varchar(255),\r\n" + 
        		"    password varchar(255),\r\n" + 
        		"    email varchar(255),\r\n" + 
        		"	appartmentnum int,\r\n" + 
        		"	monthlypayment int,\r\n" + 
        		"    January int,\r\n" + 
        		"    February int,\r\n" + 
        		"    March int,\r\n" + 
        		"    April int,\r\n" + 
        		"    May int,\r\n" + 
        		"    June int,\r\n" + 
        		"    July int,\r\n" + 
        		"    August int,\r\n" + 
        		"    September int,\r\n" + 
        		"    October int,\r\n" + 
        		"    November int,\r\n" + 
        		"    December int\r\n" + 
        		");";
        try {
            PreparedStatement pst = connect.prepareStatement(sqlcreate);
            pst.execute();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
 //--------------------------------------------------------------------------//   
    public static void UpdatePay(String month,int apartment_num,int value) {//�������� ������� ��� ���� ���� ����
        String sqlupdate = "UPDATE tenant SET " + month + " =? WHERE appartmentnum =?";
        try {
          PreparedStatement pst = connect.prepareStatement(sqlupdate);
            pst.setInt(1,value);
            pst.setInt(2,apartment_num);
            pst.executeUpdate();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
//--------------------------------------------------------------------------//        
    public static void UpdatePayD(String month,int apartment_num) {//  ����� ������� ����� ��� ���� �� ��������� ����� �� ����� ����� �����
        String sqlupdate = "UPDATE tenant SET " + month + " =? WHERE appartmentnum =?";
        try {
            PreparedStatement pst = connect.prepareStatement(sqlupdate);
            pst.setInt(1,0);
            pst.setInt(2,apartment_num);
            pst.executeUpdate();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
//--------------------------------------------------------------------------// 
    public static String SelectMonthPay(String username) {    //������� �� ����� �� ������ ���� �����
    	int i=1;
    	String answer1="",answer2="";
    	 try {
             PreparedStatement statement = connect.prepareStatement("select January,February,March,April,May,June,July,August,September,October,November,December from tenant where username= "+"'"+username+"'");
             ResultSet result = statement.executeQuery();
         while(result.next()) 
         {
        	 while (i<13)
        	 {
                answer1=result.getString(i++);
               if(!(answer1.matches("0")))
            	   answer2+= answer1 + " , " ;
        	 }                       
          }
         } catch (SQLException var2) {
             var2.printStackTrace();
         }
    	 return answer2;
    }
//--------------------------------------------------------------------------//    
    public static String SelectAllPay() {//���� �� ������� ����� ��� ���� ����
    	int i=1;
    	String answer1="",answer2="";
    	 try {
             PreparedStatement statement = connect.prepareStatement("select appartmentnum,January,February,March,April,May,June,July,August,September,October,November,December from tenant");
             ResultSet result = statement.executeQuery();
         while(result.next()) 
         {
        	 while (i<14)
        	 {
               answer1=result.getString(i++);
               if(!(answer1.matches("0")))
               {
            	   if (i==2) {answer2+= "appartment "+answer1 + " = ";}
            	   else answer2+= answer1 + " , " ;
        	   }
        	 }
        	i=1;
        	answer2+=" ";
         }
         } catch (SQLException var2) {
             var2.printStackTrace();
         }
    	 return answer2;  	
    }
//--------------------------------------------------------------------------//  
    //�������� ����� ����� ��� ����
    public static String SelectAllSum(String month) {    //�������� ����� ����� ��� ����
    	String answer="";
    	 try {
    		 
             PreparedStatement statement = connect.prepareStatement("select sum(monthlypayment)from tenant where "+month+"!=0");
             ResultSet result = statement.executeQuery();
         while(result.next()) 
         {
        	    answer="the sum of -"+month+"- = " + result.getString(1);
               
         }
         } catch (SQLException var2) {
             var2.printStackTrace();
         }	 
    	 return answer;
    }
//--------------------------------------------------------------------------//       
  public static String SelectTenantPay(String appartmentnum) {//���� ����� ���� ����
    	int i=1;
    	String answer1="",answer2="appartment -"+appartmentnum+"- payments:";
    	 try {
             PreparedStatement statement = connect.prepareStatement("select January,February,March,April,May,June,July,August,September,October,November,December from tenant where appartmentnum= "+"'"+appartmentnum+"'");
             ResultSet result = statement.executeQuery();
      //RESULT �� ������� ������ ������ �� �� ������� ���� ������� ���  ���� ����� ��� ������ ����� � NEXT
             //���� ������� ��� ����, ������ ��� ���������� ������ ��� ������� ����� ������ ����� ��� ������ (����� ������ ������)
             //��� �� ������� ��� � nextline �� ����� ������ �� ������� �������� ��� ���� �������
         while(result.next()) //������ ����� �� ��� res �� ����� � NULL
         {
        	 while (i<13) //���� ������� �������� ������� I=1
        	 {
                answer1=result.getString(i++);
               if(!(answer1.matches("0")))
            	   answer2+= answer1 + " , " ; 
        	 }           
          }
         } catch (SQLException var2) {
             var2.printStackTrace();
         }
    	 return answer2;	
    }
//--------------------------------------------------------------------------//    
    public static String CheckIfExist(String phone) {//����� �� ���� ��� ��� ����
    	String ans="";
        try {
            PreparedStatement statement = connect.prepareStatement("select firstname from supplier  where phonenumber="+phone);
            ResultSet result = statement.executeQuery();
            	while(result.next()) {
                ans+=result.getString(1);}
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
        return ans;
    }
//--------------------------------------------------------------------------//      
    public static void InsertSupplier(int id,String firstname,String lastname,String phonenumber,String special) {
        String sqlInsert = "insert into supplier (id,firstname,lastname,phonenumber,specializes) values (?,?,?,?,?)";
        try {
            PreparedStatement pst = connect.prepareStatement(sqlInsert);
            pst.setInt(1, id);
            pst.setString(2, firstname);
            pst.setString(3, lastname);
            pst.setString(4, phonenumber);
            pst.setString(5, special);
            pst.execute();
        } 
        catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
//--------------------------------------------------------------------------//    
    public static int SelectMax() {
    	//����� ���� ������� �� ��� �� ����� ���� ��� ���� (���� ���� ����� ��� ������)
    	int answer=-1;
        try {
            PreparedStatement statement = connect.prepareStatement("select max(id) from supplier");
            ResultSet result = statement.executeQuery();
            	while(result.next()) {
                  answer=result.getInt(1);}
        } catch (SQLException var2) {
            var2.printStackTrace();
        } 
        return answer;
    }
//--------------------------------------------------------------------------//        
    public static void connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //����� ������� mysql � JDBC
        } catch (ClassNotFoundException var1) {
            var1.printStackTrace();
        }
    }
//--------------------------------------------------------------------------//    
    public static void ConectingToSQL() {
        connection();
        //������� ����� ����� ������ �������� ������ ����� ��
        String host = "jdbc:mysql://localhost:3306/project";
        String username = "root";
        String password = "";
        try {
            connect = (Connection)DriverManager.getConnection(host+"?serverTimezone=UTC", username, password);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }
    }
  //--------------------------------------------------------------------------//    
	@SuppressWarnings("resource")
	public static void main(String[] argv) {
    	new Scanner(System.in);
    	ConectingToSQL();   
    }
}
