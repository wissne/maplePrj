package com.example.jgroupsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Button btn;
	private EditText text;
	private TextView view;
	private MyHandler handler;
	private SimpleChat chat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button) findViewById(R.id.button1);
		text = (EditText) findViewById(R.id.editText1);
		view = (TextView) findViewById(R.id.textView1);
		handler = new MyHandler();
		chat = new SimpleChat();
		chat.setHandler(handler);
		
		try {
			chat.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					chat.sendMessage(text.getText().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}
	class MyHandler extends Handler {
		
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle bundle= msg.getData();
			String s = bundle.getString("data");
			
			if (1==msg.what) {
				view.setText(view.getText() + "\n" + s);
			}
		}
	}
}


