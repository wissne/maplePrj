package com.maple.indent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	protected static final int REQUEST_CODE = 1;

	private Button btn = null;
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			
			/*
			 * Call
			 */
//			intent.setAction(Intent.ACTION_CALL);
//			intent.setData(Uri.parse("tel:110"));
			
			/*
			 * Send message
			 */
//			intent.setAction(Intent.ACTION_SENDTO);
//			intent.setData(Uri.parse("smsto:5554"));
//			intent.putExtra("sms_body", "How are you");
//			startActivity(intent);
			
			intent.setClass(MainActivity.this, SecondActivity.class);
			intent.putExtra("str", "Maple");
//			startActivity(intent);
			startActivityForResult(intent, REQUEST_CODE);
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE) {
			if (resultCode == SecondActivity.RESULT_CODE) {
				Bundle bundle = data.getExtras();
				String str = bundle.getString("back");
				Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
			}
		}
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn = (Button) findViewById(R.id.btnMain);
        btn.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
