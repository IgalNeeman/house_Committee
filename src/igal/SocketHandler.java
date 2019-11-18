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

//� �����-���� ������� ��� // ������ ��� ���� �� ������ �� ������� ��� �����


public class SocketHandler extends Thread {
	Socket incoming;
	Driver Sql;
	static int count=1;
	int counter=0;
	SocketHandler(Socket _in) {
		// ����� ������ ��� ����� ����� ���� ����� ������� , ������ �� ���� ���� ������
				// �����
		this.incoming = _in;
		//this � 2 ������� �������
		//1 ����� ������ ������� ������ �� ���� ���������
		//2 ����� ���� ��� ����

	}
	@SuppressWarnings("static-access")
	
	//  trd- ����� ������ �� �������
	public void run() {
	//	���� JAVA ��� ���� ���� ������ ��� ����
	//	����� �������, �� ���� ������ ������� ���� �- thread ���� ��� ��� ������, ����� �����, ���� ������ �����
	//	��� ���� �� ����� Run
		Object clientObject;
		ObjectInputStream inFromClient = null;
		DataOutputStream outToClient = null;
		try {
			// ������ 2 "�������" �������: ��� ���� ���� ����� ,��� ���� ���� ����
						// ��� ���� ���� ����� // ����� �� ��������
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
					//instanceof ��� ���� �� ������� ��� ������ ������, �������� �
				//����� TRUE �� CLIENTOBJECT ��� ������ �� INFO
					//�� ��� �������� ���
				{
					Detail = (Info) clientObject;	
					  if ((Detail.GetCategory().matches("0")))
							  {
						       outToClient.writeBytes("bye bye!"+ "\n");
							  }
	               if ((Detail.GetCategory().matches("1") && Detail.GetSelect().matches("1")) && Detail.GetChoice().matches("non")) {
						if (Detail.GetPassword().matches(Sql.SelectHouseCommite(Detail.GetUsername()))) 
							outToClient.writeBytes("Wellcom!"+ "\n"); //�� �� ������ ������� �� ���� ���� ������
						else {
						outToClient.writeBytes("Wrong username/password! please try again--->" + "\n");
						}						
						}					
					if ((Detail.GetCategory().matches("2") && Detail.GetSelect().matches("1")) && Detail.GetChoice().matches("non")) {		
						if (Detail.GetPassword().matches(Sql.SelectTenant(Detail.GetUsername()))) 
							outToClient.writeBytes("Wellcom!" + "\n");//�� �� ������ ������� �� ����� ������ ������
						else {
						outToClient.writeBytes("Wrong username/password! please try again--->" + "\n");
						}
					}
					 if ((Detail.GetCategory().matches("1") && Detail.GetSelect().matches("2"))&& Detail.GetChoice().matches("non")) {
							Sql.UpdateHouseCommitePassword(Detail.GetUsername(), Detail.GetPassword());
							outToClient.writeBytes("Password changed sucssefuly :)" + "\n");//����� ����� ���� �� ���� ����
					 }
					 if ((Detail.GetCategory().matches("2") && Detail.GetSelect().matches("2"))&& Detail.GetChoice().matches("non")) {
						Sql.UpdateTenantPassword(Detail.GetUsername(), Detail.GetPassword());
						outToClient.writeBytes("Password has changed sucssefuly :)" + "\n");//����� �� ����� ���� �� ����
					}
				if (Detail.GetCategory().matches("2") && Detail.GetSelect().matches("1") && Detail.GetChoice().matches("1")) {
					outToClient.writeBytes(Sql.SelectMonthPay(Detail.GetUsername()) + "\n");////���� ������ �� �����
					}
				}
				if (clientObject instanceof Housecommite) {
					House_Commite = (Housecommite) clientObject;			
				   switch(House_Commite.GetChoice())
				   {
				   //����� ������ �� �����
				case "1":{ 
					//���� ������� �� ����� ����
					String ans=Sql.SelectTenantPay(House_Commite.GetApp());
			     	outToClient.writeBytes(ans+ "\n");
					break;
					     }
				case "2":{ 
					//���� �� �� �������� ������
					String ans=Sql.SelectAllPay();
					outToClient.writeBytes(ans+ "\n");
					break;
					     }
				case "4":{ 
					//����� ������ �� ����� ����
					int month = Integer.parseInt(House_Commite.GetMonth());
					String month2=(Month.of(month).name());	
					int app = Integer.parseInt(House_Commite.GetApp());
					Sql.UpdatePay(month2,app,month);
					outToClient.writeBytes("The payment updated sucssefuly :)"+ "\n");
					break;
					     }
				case "5":{ //����� ����� ����
					int month = Integer.parseInt(House_Commite.GetMonth());
					String month2=(Month.of(month).name());	
					int app = Integer.parseInt(House_Commite.GetApp());
					Sql.UpdatePayD(month2,app);
					outToClient.writeBytes("The payment deleted sucssefuly :)"+ "\n");
					break;
					     }
				case "6":{ // ������ ������� �� ������
					int month = Integer.parseInt(House_Commite.GetMonth());
					String month2=(Month.of(month).name());
					String ans=Sql.SelectAllSum(month2);
					outToClient.writeBytes(ans+ "\n");
					break;
					     }
				case "7":{ //���� �����
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
				if (clientObject instanceof Supplier) { //����� ��� ���
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
