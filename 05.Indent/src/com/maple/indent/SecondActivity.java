package com.maple.indent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends Activity {
	
	private Button btn = null;
	
	private final static int REQUEST_CODE = 1;

	protected static final int RESULT_CODE = 1;
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
//			intent.setClass(SecondActivity.this, MainActivity.class);
//			startActivity(intent);
			intent.putExtra("back", "byebye");
			setResult(RESULT_CODE, intent);
			finish();			
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(listener );
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str = (String) bundle.get("str");
        Toast.makeText(SecondActivity.this, "Hello " + str, Toast.LENGTH_LONG).show();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_second, menu);
        return true;
    }


    
    
}
