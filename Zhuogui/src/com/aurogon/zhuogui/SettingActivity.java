package com.aurogon.zhuogui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.aurogon.zhuogui.util.Constant;
import com.aurogon.zhuogui.util.SoundUtil;

public class SettingActivity extends Activity {
	
	static final int RESULT_CODE = 1;
	private Button btnOK;
	private Button btnCancel;
	
	private EditText edtName;
	SoundUtil soundUtil = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);
        
        btnOK = (Button) findViewById(R.id.btnSettingOK);
        btnCancel = (Button) findViewById(R.id.btnSettingCancel);
        
        edtName = (EditText) findViewById(R.id.edtSettingName);
        
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str = bundle.getString(Constant.HOST_DATA.NAME);
        edtName.setText(str);
        
        soundUtil = new SoundUtil(this);
        
        btnOK.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this, MainActivity.class);
		        String str = edtName.getText().toString();
		        intent.putExtra(Constant.HOST_DATA.NAME, str);
		        setResult(RESULT_CODE, intent);
				finish();	
			}
		});
        
        btnCancel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				finish();
			}
		});
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_setting, menu);
        return false;
    }
}
