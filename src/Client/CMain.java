package Client;

import java.net.Socket;

import member.Login;

public class CMain {

	public static void main(String[] args) throws Exception {
		Socket withServer = null;
		withServer = new Socket("1.247.118.30", 7777);
		System.out.println("�����Ƕ� ��Ʈ�ѹ� ����");
		new Login();
	}

}
