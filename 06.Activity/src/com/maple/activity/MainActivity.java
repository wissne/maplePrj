package com.maple.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";



	private static final String CONTEXT = "CONTEXT";



	private Button btn1;
	private Button btn2;
	private EditText edit;
	private Intent intent = new Intent();
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button btn = (Button) v;
			switch (btn.getId()) {
			case R.id.button1:
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
				break;
			case R.id.button2:
				intent.setClass(MainActivity.this, ThirdActivity.class);
				startActivity(intent);
				break;

			default:
				break;
			}
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        edit = (EditText) findViewById(R.id.editText1);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        if (null != savedInstanceState && savedInstanceState.containsKey(CONTEXT)) {
	        String s = savedInstanceState.getString(CONTEXT);
	        edit.setText(s);
	        Log.i(TAG, "get instance state");
        }
        Log.i(TAG, "onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		String context = edit.getText().toString();
		outState.putString(CONTEXT, context);
		Log.i(TAG, "save instance state");
	}
    
    
}
