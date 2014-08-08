package com.example.customerdialog;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	ArrayList<DialogItem> mItems = new ArrayList<DialogItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViewById(R.id.version_old).setOnClickListener(this);
		findViewById(R.id.version_new).setOnClickListener(this);
		//标题
		mItems.add(new DialogItem(R.string.title,R.layout.custom_dialog_title));
		//登陆
		mItems.add(new DialogItem(R.string.login, R.layout.custom_dialog_special) {
			
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, R.string.click_login, Toast.LENGTH_LONG).show();

			}
		});
		//退出
		mItems.add(new DialogItem(R.string.exit,R.layout.custom_dialog_normal){
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				super.onClick();
				Toast.makeText(MainActivity.this, R.string.click_exit, Toast.LENGTH_LONG).show();
			}
		});
		//取消
		mItems.add(new DialogItem(R.string.cancel, R.layout.custom_dialog_cancel));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.version_old:
			Tools.createCustomDialog(MainActivity.this, mItems,R.style.CustomDialogOld);
			break;
		case R.id.version_new:
			Tools.createCustomDialog(MainActivity.this, mItems,R.style.CustomDialogNew);
			break;

		default:
			break;
		}
	}
	
	
}
