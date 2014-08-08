package wangkai.wk.com.cn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public class ClientThread implements Runnable {
	
	private Socket s;
	private Handler handler;
	BufferedReader br = null;
	
	
	public ClientThread(Socket s,Handler handler) {
		// TODO Auto-generated constructor stub
		
		this.s = s;
		this.handler = handler;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		String connet = null;
		try {
			while ((connet = br.readLine()) != null) {
				
				Message msg = new Message();
				msg.what = 0x123;
				msg.obj = connet;
				handler.sendMessage(msg);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
