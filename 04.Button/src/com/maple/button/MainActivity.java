package com.maple.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btn1 = null;
	private Button btn2 = null;

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button btn = (Button) v;
			switch (btn.getId()) {
			case R.id.btnDemo01:
				Toast.makeText(MainActivity.this, "You click on the button 1",
						Toast.LENGTH_LONG).show();
				break;
			case R.id.btnDemo02:
				Toast.makeText(MainActivity.this, "You click on the button 2",
						Toast.LENGTH_LONG).show();
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
		btn1 = (Button) findViewById(R.id.btnDemo01);

		btn1.setOnClickListener(listener);

		btn2 = (Button) findViewById(R.id.btnDemo02);
		btn2.setOnClickListener(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}

class ButtonClick implements OnClickListener {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("You click on the button");
	}

}