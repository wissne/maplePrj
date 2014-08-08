package wangkai.wk.com.cn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {

	public static ArrayList<Socket> socketList = new ArrayList<Socket>();// 定义一个Socket的ArrayList 
	
		public static void main(String[] args) {
			
			try {
				ServerSocket ss = new ServerSocket(34000); // 定义一个ServerSocket 对象 端口号为 34000
				while (true) {
					Socket s = ss.accept();
					socketList.add(s); // 存放所有的Socket 
					new Thread(new ServerThread(s)).start();// 每一个ServerThread 对象里的 Socket 对象启动时   启动一个线程
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
}
