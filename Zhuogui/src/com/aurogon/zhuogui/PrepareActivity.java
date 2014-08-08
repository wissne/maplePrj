package com.aurogon.zhuogui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.aurogon.zhuogui.util.Constant;
import com.aurogon.zhuogui.util.SoundUtil;

public class PrepareActivity extends Activity {

	private Button btnPreOK;
	private Button btnPreCancel;
	private EditText edtTotalNum;
	private EditText edtGhostNum;
	private EditText edtIdiotNum;
	private EditText edtIdiotWord;
	private EditText edtPeopleWord;
	private EditText edtRoom;
	private CheckBox chkCustomWord;
	

	private String name;
	private String IP;
	private String romm;
	SoundUtil soundUtil = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_prepare);

		edtTotalNum = (EditText) findViewById(R.id.edtTotalNum);
		edtGhostNum = (EditText) findViewById(R.id.edtGhostNum);
		edtIdiotNum = (EditText) findViewById(R.id.edtIdiotNum);
		edtIdiotWord = (EditText) findViewById(R.id.edtIdiotWord);
		edtPeopleWord = (EditText) findViewById(R.id.edtPeopleWord);
		edtRoom = (EditText) findViewById(R.id.edtRoom);
		chkCustomWord = (CheckBox) findViewById(R.id.chkCustomWord);
		soundUtil = new SoundUtil(this);

		btnPreOK = (Button) findViewById(R.id.btnPreOK);
		btnPreCancel = (Button) findViewById(R.id.btnPreCancel);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		name = bundle.getString(Constant.HOST_DATA.NAME);
		IP = bundle.getString(Constant.HOST_DATA.IP);

		edtTotalNum.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (null == s || "".equals(s.toString()))
						return;
				int all = Integer.parseInt(s.toString());
				int ghost = (int) Math.floor((all + 2) / 4.0);
				edtGhostNum.setText(Integer.toString(ghost));
				edtIdiotNum.setText(Integer.toString(1));
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void afterTextChanged(Editable s) {

			}
		});
		
		chkCustomWord.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					edtPeopleWord.setEnabled(true);
					edtIdiotWord.setEnabled(true);
				} else {
					edtPeopleWord.setEnabled(false);
					edtIdiotWord.setEnabled(false);
					edtPeopleWord.setText("");
					edtIdiotWord.setText("");
				}
			}
		});
		
		btnPreOK.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				Intent intent = new Intent(); 
				intent.setClass(PrepareActivity.this, HostActivity.class);
				intent.putExtra(Constant.HOST_DATA.NAME, name);
				intent.putExtra(Constant.HOST_DATA.IP, IP);
				intent.putExtra(Constant.HOST_DATA.ROOM, edtRoom.getText().toString());
				intent.putExtra(Constant.HOST_DATA.TOTAL, edtTotalNum.getText().toString());
				intent.putExtra(Constant.PEOPLE_TYPE.GHOST, edtGhostNum.getText().toString());
				intent.putExtra(Constant.PEOPLE_TYPE.IDIOT, edtIdiotNum.getText().toString());
				intent.putExtra(Constant.WORD_TYPE.PEOPLE_WORD, edtPeopleWord.getText().toString());
				intent.putExtra(Constant.WORD_TYPE.IDIOT_WORD, edtIdiotWord.getText().toString());
				startActivity(intent);				
			}
		});

		btnPreCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				soundUtil.playMusic();
				finish();
			}
		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_prepare, menu);
		return false;
	}
}
