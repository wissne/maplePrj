package com.example.customdialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showCustomDialog(MainActivity.this, "我来自广西", null, null, null);
			}
		});
		
		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showCustomDialog(MainActivity.this, null, null, "请输入你的话", null);
			}
		});
		
		Button btn3 = (Button) findViewById(R.id.button3);
		btn3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i < 14 ;i ++ )
					list.add("00" + i);
				showCustomDialog(MainActivity.this, null, null, null, list);
			}
		});
		

	}
	
	public void  showCustomDialog(Activity activity, String msg, String text, String hint, List<String> list) {
		CustomDialog.Builder builder = new CustomDialog.Builder(activity);
		builder.setMessage(msg);
		builder.setText(text);
		builder.setHint(hint);
		builder.setTitle("提示");
		builder.setList(list, new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ListView listView = (ListView) arg0;
				listView.getItemAtPosition(arg2);
		        Toast.makeText(MainActivity.this, "The list value is " +listView.getItemAtPosition(arg2),
						Toast.LENGTH_LONG).show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.dismiss();
		        //设置你的操作事项
		        CustomDialog dlg = (CustomDialog) dialog;
		        // For EditText only
		        EditText text =  (EditText) dlg.findViewById(R.id.text);
		        Toast.makeText(MainActivity.this, "The text value is " +text.getText(),
						Toast.LENGTH_LONG).show();
		    }
		});
		 
//		builder.setNegativeButton("取消",
//		        new android.content.DialogInterface.OnClickListener() {
//		            public void onClick(DialogInterface dialog, int which) {
//		                dialog.dismiss();
//		            }
//		        });
		 
		builder.create().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
