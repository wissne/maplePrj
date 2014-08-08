package wangkai.wk.com.cn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {

	public static ArrayList<Socket> socketList = new ArrayList<Socket>();// ����һ��Socket��ArrayList 
	
		public static void main(String[] args) {
			
			try {
				ServerSocket ss = new ServerSocket(34000); // ����һ��ServerSocket ���� �˿ں�Ϊ 34000
				while (true) {
					Socket s = ss.accept();
					socketList.add(s); // ������е�Socket 
					new Thread(new ServerThread(s)).start();// ÿһ��ServerThread ������� Socket ��������ʱ   ����һ���߳�
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
}
