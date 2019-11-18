//������ �����: ���� ����
//�- ���� ����� ����� , ����� ����������� ������� ������ ����
//���� �- ���� ���� �� �� ����� ����� ��� ���� ����
//������� ����� ������ � ���� ���� ����� ���� ������ ��
//����� ��� ������ � 2 �����:
// ����� ����� , ���� ���� ������ ���� ����
//���� ���� ���� ���� ����� � ����
package igal;
import java.io.*; 
import java.net.*; 
class TcpServer { 
//@SuppressWarnings("resource")
@SuppressWarnings("resource")
public static void main(String argv[]) throws Exception 
//�- MAIN 
//���� ���� ����� �� ������ �� ��� ��� ������� ����� �������� ������� ����� �� � ����
    { 
	  ServerSocket s = null;
		try {
		    s = new ServerSocket(10000);//����� ���� �����
		   
		} catch(IOException e) {
		    System.out.println(e);
		    System.exit(1);
		}
		
		// ������ ���� ����� ����� ������ �������
		while (true) {
		    Socket incoming = null;
		 // ����� ����� ������ �� ������ ����� ����� �����
		 			// ���� ������ ����� �� ����� ����� ����
		    try {
			incoming = s.accept(); //BLOCKING
			//���� ����� ������� ��������.
			//����� �� ��� BLOCKING
			
		    } catch(IOException e) {
		    	//�� ������ ����� ����� �� ����� ����� �� �����
		    	
			System.out.println(e);
			continue;
		    }

		    new SocketHandler(incoming).start();
		    //��� ����� ����� ������� ������ START ����� �� ����
		    //������ ��������� RUN  ����� ����������- ����� ���������
		    System.out.println("Server is on");

		}
    } 
} 