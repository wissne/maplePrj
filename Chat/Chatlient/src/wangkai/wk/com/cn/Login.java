package wangkai.wk.com.cn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	/** Called when the activity is first created. */

	EditText ip;
	Button send;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		ip = (EditText) findViewById(R.id.ip);
		send = (Button) findViewById(R.id.send);
		
		ip.setOnClickListener(this);
		send.setOnClickListener(this);
		
	
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.send:
			
			if (!ip.getText().toString().equals("")) {
				
				String ipnum = ip.getText().toString();
				Intent intent = new Intent();
				intent.putExtra("123", ipnum);
				intent.setClass(Login.this, ChatlientActivity.class);
				Login.this.startActivity(intent);
				finish();
			}else{
				Toast.makeText(Login.this, " ‰»Î”–ŒÛ£°", Toast.LENGTH_SHORT).show();
			}
			
			break;

		
		}
	}
	
}
