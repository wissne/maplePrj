package com.aurogon.zhuogui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aurogon.zhuogui.bean.HostBean;
import com.aurogon.zhuogui.bean.PlayerBean;
import com.aurogon.zhuogui.util.Constant;
import com.aurogon.zhuogui.util.CustomDialog;
import com.aurogon.zhuogui.util.GameReceiver;
import com.aurogon.zhuogui.util.GameSender;
import com.aurogon.zhuogui.util.SoundUtil;

public class JoinActivity extends Activity {
	
	private Button btnJoinOpenConn;
	private Button btnJoinCloseConn;
	private Button btnSendMsg;
	private EditText edtJoinOut;
	private TextView tvTitle;
	private String name;
	private String room;
	private String IP;
	private PlayerBean player;
	private HostBean hostServer;
	private GameReceiver receiver;
	private GameSender sender;
	private MyHandler handler;
	private String ticketPlayerName;
	SoundUtil soundUtil = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_join);
		
		btnJoinOpenConn = (Button) findViewById(R.id.btnJoinOpenConn);
		btnJoinCloseConn = (Button) findViewById(R.id.btnJoinCloseConn);
		btnSendMsg = (Button) findViewById(R.id.btnJoinSendMsg);
		tvTitle = (TextView) findViewById(R.id.tvJoinTitle);
		edtJoinOut = (EditText) findViewById(R.id.edtJoinOut);
        
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		name = bundle.getString(Constant.HOST_DATA.NAME);
		IP = bundle.getString(Constant.HOST_DATA.IP);
		room = bundle.getString(Constant.HOST_DATA.ROOM);
		
		soundUtil = new SoundUtil(this);
		
		player = new PlayerBean();
		player.setIp(IP);
		player.setName(name);
		player.setRoom(room);
		player.setHoster(false);
		player.setMessageType(Constant.MESSAGE_TYPE.PLAYER_IS_READY);
		player.setGameOver(false);
		player.setAllPlayerList(new ArrayList<PlayerBean>());
		
		handler = new MyHandler();
		
		btnJoinOpenConn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {
					soundUtil.playMusic();
					hostServer = new HostBean(0, 0, 0, null, room, IP, false);
					hostServer.init();
					receiver = new GameReceiver(hostServer, handler);
					receiver.receiveMsg();
					
					player.setMessageType(Constant.MESSAGE_TYPE.PLAYER_IS_READY);
					player.setGameOver(false);
					player.setAllPlayerList(new ArrayList<PlayerBean>());
					
					sender = new GameSender(hostServer);
					sender.sendMsg(player);
					btnJoinOpenConn.setEnabled(!btnJoinOpenConn.isEnabled());					
					btnJoinCloseConn.setEnabled(!btnJoinCloseConn.isEnabled());
					btnSendMsg.setVisibility(View.VISIBLE);
				} catch (IOException e) {
					e.printStackTrace();
					edtJoinOut.setText(e.getMessage());
				}
			}
		});
		btnJoinCloseConn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				hostServer.setGameOver(true);
				try {
					player.setMessageType(Constant.MESSAGE_TYPE.PLAYER_HAS_LEFT);
					sender = new GameSender(hostServer);
					hostServer.setGameOver(true);
					sender.sendMsg(player);
					btnJoinOpenConn.setEnabled(!btnJoinOpenConn.isEnabled());					
					btnJoinCloseConn.setEnabled(!btnJoinCloseConn.isEnabled());
					btnSendMsg.setVisibility(View.GONE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnSendMsg.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						soundUtil.playMusic();
						String title = "我要发言";
						String s = "请输入你想说的话" ;
						sendYourMsg(JoinActivity.this, title,  null, null, s, null);
						
					}
				});

	}
	
	   public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				try {
					soundUtil.playMusic();
					if (null != hostServer) {
						if (null == sender)
							return super.onKeyDown(keyCode, event);
						hostServer.setGameOver(true);
						player.setMessageType(Constant.MESSAGE_TYPE.PLAYER_HAS_LEFT);
						hostServer.setGameOver(true);
						sender.sendMsg(player);
						btnJoinOpenConn.setEnabled(!btnJoinOpenConn.isEnabled());					
						btnJoinCloseConn.setEnabled(!btnJoinCloseConn.isEnabled());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    	return super.onKeyDown(keyCode, event);
	    }
	   
	   public void  sendYourMsg(Activity activity, String title, String msg, String text, String hint, List<String> list) {
			CustomDialog.Builder builder = new CustomDialog.Builder(activity);
			
			builder.setMessage(msg);
			builder.setText(text);
			builder.setHint(hint);
			builder.setTitle(title);
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int which) {
				    	soundUtil.playMusic();
				        dialog.dismiss();
				        //设置你的操作事项
				        CustomDialog dlg = (CustomDialog) dialog;
				        EditText text =  (EditText) dlg.findViewById(R.id.text);
				        String s = text.getText().toString();
				        if (s == null || s.equals(""))
				        	s = "我什么都没说";
						player.setMsg(player.getName() + " ： " + s);
						player.setMsgToAll(true);
				        player.setMessageType(Constant.MESSAGE_TYPE.SHOW_MESSAGE);
				        sendMsg(player);
			        }
			});
			 
			builder.create().show();
		}

	
    public void alertMsg(String title, String msg) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		AlertDialog alertDialog = builder.setMessage(msg).setNegativeButton("确定", null).setTitle(title).create();
    	Window window = alertDialog.getWindow();
    	WindowManager.LayoutParams lp = window.getAttributes(); 
    	lp.alpha = 0.9f;
    	window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,   
    			 WindowManager.LayoutParams.FLAG_BLUR_BEHIND);  
    	window.setAttributes(lp);
    	alertDialog.show();
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.join, menu);
		return false;
	}
	
	public void  showYourWord(Activity activity, String title, String msg, String text, String hint, List<String> list) {
		CustomDialog.Builder builder = new CustomDialog.Builder(activity);
		builder.setMessage(msg);
		builder.setText(text);
		builder.setHint(hint);
		builder.setTitle(title);
		
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.dismiss();
		        soundUtil.playMusic();
		    }
		});    	
		 
		builder.create().show();
	}
	
	private void showMsg(String msg) {
		edtJoinOut.append(msg + "\n");
		edtJoinOut.setSelection(edtJoinOut.length());
	}
	
    public void sendMsg(PlayerBean player) {
    	sender.sendMsg(player);
    }
    
    public void  sendYourTicket(Activity activity, String title, String msg, String text, String hint, List<String> list) {
		CustomDialog.Builder builder = new CustomDialog.Builder(activity);
		
		builder.setMessage(msg);
		builder.setText(text);
		builder.setHint(hint);
		builder.setTitle(title);
		builder.setList(list, new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ListView listView = (ListView) arg0;
				listView.getItemAtPosition(arg2);
		        Toast.makeText(JoinActivity.this, "The list value is " +listView.getItemAtPosition(arg2),
						Toast.LENGTH_LONG).show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				soundUtil.playMusic();
		        dialog.dismiss();
		        //设置你的操作事项
		        CustomDialog dlg = (CustomDialog) dialog;
		        EditText text =  (EditText) dlg.findViewById(R.id.text);
		        ticketPlayerName = text.getText().toString();
		        player.setTicketName(ticketPlayerName);
		        player.setMessageType(Constant.MESSAGE_TYPE.SEND_MY_TICKET);
		        sendMsg(player);
		    }
		});
		 
		builder.create().show();
	}
	
    class MyHandler extends Handler {
    	
		public void handleMessage(Message msg) {
    		super.handleMessage(msg);
    		Bundle bundle= msg.getData();
    		PlayerBean bean = (PlayerBean) bundle.getSerializable(Constant.HOST_DATA.DATA);
    		String str = bean.getMsg();
    		if (Constant.MESSAGE_TYPE.GET_YOUR_WORD == bean.getMessageType()){
    			// 分配单词
    			String s = bean.getMyWord(player);
    			String role = bean.getRole();
    			String title = Constant.PEOPLE_TYPE.GHOST.equals(role)? "你的提示是":"你的词语是";
//    			alertMsg(title, s);
    			showYourWord(JoinActivity.this, title,  s, null, null, null);
    		} else if (bean.getMessageType() == Constant.MESSAGE_TYPE.SEND_YOUR_TICKEDT) {
    			// 开始投票
    			showMsg("下一轮投票开始，请投票。。。");
    			if (!player.isGameOver()) {
	    			String title = "请投票";
	//    			alertMsg(title, s);
	    			sendYourTicket(JoinActivity.this, title,  null, null, null, bean.getAvailablePlayerNameList(player.getName()));
    			}
    		}  else if (bean.getMessageType() == Constant.MESSAGE_TYPE.PLAYER_GAME_OVER) {
    			if (bean.getDeadName().equals(player.getName())){
    				player.setGameOver(true);
    				player.setGameInfo(player.getGameInfo() + " 出局");
    				tvTitle.setText(player.getGameInfo());
    			}
    			showMsg(str);
    		} else if (bean.getMessageType() == Constant.MESSAGE_TYPE.GAME_OVER) {
				player.setGameOver(true);
				player.setGameInfo(" 游戏结束");
				tvTitle.setText(player.getGameInfo());
    			showMsg(str);
    		} else if (bean.getMessageType() == Constant.MESSAGE_TYPE.SHOW_MESSAGE) {
    			showMsg(str);
    			if (!player.isGameOver()) {
	    			player.setGameInfo(bean.getGameInfo());    			
	    			tvTitle.setText(bean.getGameInfo());
    			}
    		}
    	}
		
    }

}
