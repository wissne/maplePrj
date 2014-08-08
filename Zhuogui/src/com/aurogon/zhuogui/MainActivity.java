package com.aurogon.zhuogui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.aurogon.zhuogui.util.Constant;
import com.aurogon.zhuogui.util.MyViberation;
import com.aurogon.zhuogui.util.SoundUtil;
import com.aurogon.zhuogui.util.ZGUtil;

public class MainActivity extends Activity {
	
	static final int REQUEST_CODE = 1;
	
	private Button btnStart;
	private Button btnJoin;
	private Button btnSetting;
	private Button btnHelp;
	private Button btnExit;
	private TextView tvIP;
	private TextView tvName;
	
    SoundUtil soundUtil = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        
        Context context = getApplicationContext();
        
        soundUtil = new SoundUtil(this);
        
//        LinearLayout layout =  (LinearLayout) findViewById(R.id.layMain);
//        FontManager.changeFonts(layout, MainActivity.this);
        
        btnStart = (Button) findViewById(R.id.btnStart);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnHelp = (Button) findViewById(R.id.btnHelp);
        btnExit = (Button) findViewById(R.id.btnExit);
        tvIP = (TextView) findViewById(R.id.tvIP);
        tvName = (TextView) findViewById(R.id.tvName);
        
        String name = ZGUtil.getStringData(MainActivity.this, Constant.HOST_DATA.NAME);
        tvName.setText(name);
        
        btnStart.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, PrepareActivity.class);
				intent.putExtra(Constant.HOST_DATA.NAME, tvName.getText());
				intent.putExtra(Constant.HOST_DATA.IP, tvIP.getText());
				startActivity(intent);
			}
		});
        
        btnJoin.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		soundUtil.playMusic();
        		Intent intent = new Intent(); 
        		intent.setClass(MainActivity.this, PreJoinActivity.class);
        		intent.putExtra(Constant.HOST_DATA.NAME, tvName.getText());
        		intent.putExtra(Constant.HOST_DATA.IP, tvIP.getText());
        		startActivity(intent);
        	}
        });
        
        btnSetting.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				Intent intent = new Intent(); 
				intent.setClass(MainActivity.this, SettingActivity.class);
				intent.putExtra(Constant.HOST_DATA.NAME, tvName.getText());
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
        
        btnExit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				finish();
			}
		});
        
        
		ZGUtil.enableMulticastLock(context);
        String wifiIP = ZGUtil.getWIFIIP(context);
        tvIP.setText(wifiIP);
    }



    
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE) {
			if (resultCode == SettingActivity.RESULT_CODE) {
				Bundle bundle = data.getExtras();
				String str = bundle.getString(Constant.HOST_DATA.NAME);
				ZGUtil.saveStringData(this, Constant.HOST_DATA.NAME, str);
				tvName.setText(str);
			}
		}
	}    

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return false;
    }
}
