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

import com.aurogon.zhuogui.bean.PlayerBean;
import com.aurogon.zhuogui.util.Constant;
import com.aurogon.zhuogui.util.SoundUtil;

public class PreJoinActivity extends Activity {

	private Button btnJoinOK;
	private Button btnJoinCancel;
	private EditText edtJoinRoom;
	private String name;
	private String IP;
	
	private PlayerBean player;
	SoundUtil soundUtil = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pre_join);
        
		btnJoinOK = (Button) findViewById(R.id.btnJoinOK);
		btnJoinCancel = (Button) findViewById(R.id.btnJoinCancel);
		
		edtJoinRoom = (EditText) findViewById(R.id.edtJoinRoom);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		name = bundle.getString(Constant.HOST_DATA.NAME);
		IP = bundle.getString(Constant.HOST_DATA.IP);
		
		player = new PlayerBean();
		player.setName(name);
		player.setIp(IP);
		soundUtil = new SoundUtil(this);

		btnJoinOK.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				Intent intent = new Intent(); 
				intent.setClass(PreJoinActivity.this, JoinActivity.class);
				intent.putExtra(Constant.HOST_DATA.NAME, name);
				intent.putExtra(Constant.HOST_DATA.IP, IP);
				String room = edtJoinRoom.getText().toString();
				intent.putExtra(Constant.HOST_DATA.ROOM, room);
				startActivity(intent);		
			}
		});
		btnJoinCancel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				finish();
			}
		});
		
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pre_join, menu);
        return false;
    }
}
