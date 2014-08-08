package wangkai.wk.com.cn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ServerThread implements Runnable {

	Socket s = null;
	BufferedReader br = null;

	public ServerThread(Socket s) {
		// TODO Auto-generated constructor stub

		this.s = s;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream(),
					"utf-8")); // ��ÿ�� Socket�����װ��Ϊ BufferedReader ����ڻ�����
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		String content = null;
		while ((content = readeFromClient()) != null) {

			for (Socket s : MyServer.socketList) {// ����ÿ�� Myserver �е� SocketList

				try {
					OutputStream os = s.getOutputStream();
					os.write((content + "\n").getBytes("utf-8"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	private String readeFromClient() {// ��ȡ�ͻ��˵�����

		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MyServer.socketList.remove(s);
		}
		return null;
	}

}
