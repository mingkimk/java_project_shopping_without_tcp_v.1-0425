package Client;

import java.net.Socket;

import member.Login;

public class CMain {

	public static void main(String[] args) throws Exception {
		Socket withServer = null;
		withServer = new Socket("1.247.118.30", 7777);
		System.out.println("아이피랑 포트넘버 생성");
		new Login();
	}

}
