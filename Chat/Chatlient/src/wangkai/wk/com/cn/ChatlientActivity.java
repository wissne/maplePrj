package wangkai.wk.com.cn;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChatlientActivity extends Activity {
	

	Button surebtn;
	EditText input,show,name;
	OutputStream os;
	Handler handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		surebtn = (Button) findViewById(R.id.surebtn);
		show = (EditText) findViewById(R.id.show);
		input = (EditText) findViewById(R.id.input);
		name = (EditText) findViewById(R.id.name);
		Socket s;
		
		Intent intent = this.getIntent();
		String ip = intent.getStringExtra("123");
		System.out.println(ip);
		
		
		handler = new Handler() {

			public void handleMessage(Message msg) {

				if (msg.what == 0x123) {
					
					

					show.append("\n"+ msg.obj.toString());

				}

			}

		};
		
		try {
			s = new Socket(ip,34000);
			new Thread(new ClientThread(s,handler)).start();
			os = s.getOutputStream();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		surebtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			
					try {
						os.write((name.getText().toString()+":"+input.getText().toString()+"\r\n").getBytes("utf-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					input.setText("");
				}
				
			
		});
	}
}