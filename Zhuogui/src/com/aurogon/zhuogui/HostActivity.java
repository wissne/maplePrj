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
import com.aurogon.zhuogui.bean.TicketBean;
import com.aurogon.zhuogui.bean.WordBean;
import com.aurogon.zhuogui.bean.WordList;
import com.aurogon.zhuogui.util.Constant;
import com.aurogon.zhuogui.util.CustomDialog;
import com.aurogon.zhuogui.util.GameReceiver;
import com.aurogon.zhuogui.util.GameSender;
import com.aurogon.zhuogui.util.SoundUtil;

public class HostActivity extends Activity {
	
	private Button btnOpenConn;
	private Button btnCloseConn;
	private Button btnSendTicket;
	private Button btnSendMsg;
	private Button btnStart;
	private EditText edtOut;
	private TextView tvTitle;
	private String name;
	private String room;
	private String IP;
	private int totalPlayer;
	private int totalGhost;
	private int totalIdiot;
	private PlayerBean player;
	private WordBean word;
	private HostBean hostServer;
	private GameReceiver receiver;
	private GameSender sender;
	private MyHandler handler;
	private String peopleWord;
	private String idiotWord;
	private String ticketPlayerName;
	private boolean isJoin = true;
	
	private boolean isDebug = false;
	
	SoundUtil soundUtil = null;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_host);
        
        btnOpenConn = (Button) findViewById(R.id.btnOpenConn);
        btnCloseConn = (Button) findViewById(R.id.btnCloseConn);
        btnSendTicket = (Button) findViewById(R.id.btnSendTicket);
        btnSendMsg = (Button) findViewById(R.id.btnSendMsg);
        btnStart = (Button) findViewById(R.id.btnStart);
        tvTitle =(TextView) findViewById(R.id.tvHostTitle); 
        edtOut = (EditText) findViewById(R.id.edtOut);
        
        soundUtil = new SoundUtil(this);
        
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		name = bundle.getString(Constant.HOST_DATA.NAME);
		IP = bundle.getString(Constant.HOST_DATA.IP);
		room = bundle.getString(Constant.HOST_DATA.ROOM);
		try {
			totalPlayer = Integer.parseInt(bundle.getString(Constant.HOST_DATA.TOTAL));
			totalGhost = Integer.parseInt(bundle.getString(Constant.PEOPLE_TYPE.GHOST));
			totalIdiot = Integer.parseInt(bundle.getString(Constant.PEOPLE_TYPE.IDIOT));
		} catch (Exception e) {
		}
		String peopleWord = bundle.getString(Constant.WORD_TYPE.PEOPLE_WORD);
		String idiotWord = bundle.getString(Constant.WORD_TYPE.IDIOT_WORD);
		
		InitWords();
		
		player = new PlayerBean();
		player.setGameOver(false);
		player.setIp(IP);
		player.setName(name);
		player.setRoom(room);
		player.setHoster(true);
		player.setMessageType(Constant.MESSAGE_TYPE.PLAYER_IS_READY);
		player.setAllPlayerList(new ArrayList<PlayerBean>());
		
		handler = new MyHandler();
		
		btnOpenConn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				try {
					hostServer = new HostBean( totalPlayer, totalGhost, totalIdiot, word, room, IP, true);
					hostServer.init();
					
					// Test data
					// TODO to be comment
					if (isDebug) {
						for (int i = 0; i < 8 ; i ++) {
							PlayerBean p = new PlayerBean();
							p.setName("test" + i);
							p.setIp("192.168.2." + i);
							p.setGameOver(false);
							p.setHoster(false);
							hostServer.addTotalReady(p);
						}
					}
					
					receiver = new GameReceiver(hostServer, handler);
					receiver.receiveMsg();
					
					edtOut.setText("等待其他玩家连接中。。。\n");
					player.setMessageType(Constant.MESSAGE_TYPE.PLAYER_IS_READY);
					player.setGameOver(false);
					player.setAllPlayerList(new ArrayList<PlayerBean>());
					
					sender = new GameSender(hostServer);
					sender.sendMsg(player);
					btnOpenConn.setEnabled(!btnOpenConn.isEnabled());
					btnCloseConn.setEnabled(!btnCloseConn.isEnabled());
					btnStart.setVisibility(View.VISIBLE);
					btnSendMsg.setVisibility(View.VISIBLE);
				} catch (IOException e) {
					e.printStackTrace();
					edtOut.setText(e.getMessage());
				}
			}
		});
		btnCloseConn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				try {
					player.setMessageType(Constant.MESSAGE_TYPE.HOST_HAS_LEFT);
					if (null == sender)
						sender = new GameSender(hostServer);
					hostServer.setGameOver(true);
					sender.sendMsg(player);
					btnSendTicket.setVisibility(View.GONE);
					btnStart.setVisibility(View.GONE);
					btnSendMsg.setVisibility(View.GONE);
					btnOpenConn.setEnabled(!btnOpenConn.isEnabled());
					btnCloseConn.setEnabled(!btnCloseConn.isEnabled());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnStart.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				
				InitWords();
				hostServer.setWord(word);
				hostServer.setAllPlayerReady(true);
				ArrayList<PlayerBean> allPlayerList = hostServer.getAllPlayerList();
				int totalPlayerLeft = hostServer.getTotalReady();
				int totalGhostLeft = hostServer.getTotalGhost();
				int totalIdiotLeft = hostServer.getTotalIdiot();
				for (PlayerBean bean : allPlayerList) {
					boolean isSet = false;
					bean.setWord(word);
					boolean con = Math.floor(Math.random() * totalPlayerLeft % totalPlayerLeft) < totalGhostLeft && totalGhostLeft > 0;
					if (con) {
						bean.setRole(Constant.PEOPLE_TYPE.GHOST);
						totalGhostLeft--;
						totalPlayerLeft--;
						isSet = true;
					}
					if (!isSet) {
						con = Math.floor(Math.random() * totalPlayerLeft % totalPlayerLeft) < totalIdiotLeft && totalIdiotLeft > 0;
						if (con) {
							bean.setRole(Constant.PEOPLE_TYPE.IDIOT);
							totalIdiotLeft--;
							totalPlayerLeft--;
							isSet = true;
						}
					}
					if (!isSet) {
						bean.setRole(Constant.PEOPLE_TYPE.PEOPLE);
						totalPlayerLeft--;
						isSet = true;
					}
					
					// TODO to be comment
					if (isDebug)
						showMsg(bean.aboutMe() + " 身份是 " + bean.getRole() + "词语是" + bean.getMyWord());

				}
				player.setMessageType(Constant.MESSAGE_TYPE.GET_YOUR_WORD);
				player.setAllPlayerList(allPlayerList);
				try {
					if (null == sender)
						sender = new GameSender(hostServer);
					hostServer.setGameOver(false);
					sender.sendMsg(player);
				} catch (IOException e) {
				}
				
				btnSendTicket.setVisibility(View.VISIBLE);
				btnSendMsg.setVisibility(View.VISIBLE);
			}
		});
		
		btnSendTicket.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				player.setMessageType(Constant.MESSAGE_TYPE.SEND_YOUR_TICKEDT);
				try {
					if (null == sender)
						sender = new GameSender(hostServer);
					hostServer.setGameOver(false);
					player.setTicketBeanList(new ArrayList<TicketBean>());
					sender.sendMsg(player);
				} catch (IOException e) {
				}
				
				btnStart.setVisibility(View.GONE);
			}
		});
		
		btnSendMsg.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				soundUtil.playMusic();
				String title = "我要发言";
				String s = "请输入你想说的话" ;
				sendYourMsg(HostActivity.this, title,  null, null, s, null);

			}
		});
		
		
    }

	private void InitWords() {
		word = new WordBean();
		
		if (peopleWord != null && idiotWord != null && !"".equals(peopleWord) && !"".equals(idiotWord)) {
			word = new WordBean(peopleWord, idiotWord, "");
			isJoin = false;
		}
		else {
			int ind = (int) (Math.floor(Math.random() * WordList.getCount()) % WordList.getCount());
			word = WordList.getWord(ind);
		}
	}
    
    public void showMsg(String msg) {
		edtOut.append(msg + "\n");
		edtOut.setSelection(edtOut.length());
	}
    
    public void alertMsg(String title, String msg) {
//    	LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
//    	final View view = layoutInflater.inflate(R.layout.alert_dailog, null);  
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		AlertDialog alertDialog = builder.setMessage(msg).setNegativeButton("确定", null).setTitle(title).create();
    	Window window = alertDialog.getWindow();
    	WindowManager.LayoutParams lp = window.getAttributes(); 
    	lp.alpha = 0.9f;
    	window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,   
    			 WindowManager.LayoutParams.FLAG_BLUR_BEHIND);  
    	window.setAttributes(lp);
    	alertDialog.show();
    	
//    	AlertDialog.Builder dlg = new AlertDialog.Builder(this);
//		dlg.setTitle(title).setMessage(msg)
//				.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//					}
//				}).show();
    }

    
    public void sendMsg(PlayerBean player) {
    	sender.sendMsg(player);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			try {
				soundUtil.playMusic();
				if (null == sender)
					return super.onKeyDown(keyCode, event);
				player.setMessageType(Constant.MESSAGE_TYPE.HOST_HAS_LEFT);
				hostServer.setGameOver(true);
				sender.sendMsg(player);
				btnOpenConn.setVisibility(View.VISIBLE);					
				btnCloseConn.setVisibility(View.VISIBLE);
				btnSendTicket.setVisibility(View.GONE);
				btnStart.setEnabled(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	return super.onKeyDown(keyCode, event);
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_host, menu);
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
		    	soundUtil.playMusic();
		        dialog.dismiss();
		    }
		});    		
		 
		builder.create().show();
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
		        Toast.makeText(HostActivity.this, "The list value is " +listView.getItemAtPosition(arg2),
						Toast.LENGTH_LONG).show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.dismiss();
		        soundUtil.playMusic();
		        //设置你的操作事项
		        CustomDialog dlg = (CustomDialog) dialog;
		        EditText text =  (EditText) dlg.findViewById(R.id.text);
		        ticketPlayerName = text.getText().toString();
		        player.setTicketName(ticketPlayerName);
		        player.setMsg(player.getName() + " 投给了 " + ticketPlayerName);
		        player.setMessageType(Constant.MESSAGE_TYPE.SEND_MY_TICKET);
		        sendMsg(player);
		    }
		});
		 
		builder.create().show();
	}
	

	public void  sendYourMsg(Activity activity, String title, String msg, String text, String hint, List<String> list) {
		CustomDialog.Builder builder = new CustomDialog.Builder(activity);
		
		builder.setMessage(msg);
		builder.setText(text);
		builder.setHint(hint);
		builder.setTitle(title);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int which) {
			        dialog.dismiss();
			        soundUtil.playMusic();
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
	
    
    class MyHandler extends Handler {
    	
    	public void handleMessage(Message msg) {
    		super.handleMessage(msg);
    		Bundle bundle= msg.getData();
    		PlayerBean bean = (PlayerBean) bundle.getSerializable(Constant.HOST_DATA.DATA);
    		String str = null;
    		str = bean.getMsg();
    		if (Constant.MESSAGE_TYPE.GET_YOUR_WORD == bean.getMessageType()){
    			// 分配单词
    			String s = bean.getMyWord(player);
    			String role = bean.getRole();
    			String title = Constant.PEOPLE_TYPE.GHOST.equals(role)? "你的提示是":"你的词语是";
//    			alertMsg(title, s);
    			showYourWord(HostActivity.this, title,  s, null, null, null);
    		} else if (bean.getMessageType() == Constant.MESSAGE_TYPE.SEND_YOUR_TICKEDT) {
    			// 开始投票
    			showMsg("下一轮投票开始，请投票。。。");
    			if (!player.isGameOver()) {
	    			String title = "请投票";
	    			sendYourTicket(HostActivity.this, title,  null, null, null, bean.getAvailablePlayerNameList(player.getName()));
    			}
    		} else if (bean.getMessageType() == Constant.MESSAGE_TYPE.SEND_MY_TICKET) {
    			// 收集投票
    			player.addTicketBean(new TicketBean(bean.getName() , bean.getTicketName()));
    			
    			str = bean.getName() + " 已投票";
    			player.setMsgToAll(true);
				player.setMessageType(Constant.MESSAGE_TYPE.SHOW_MESSAGE);
    			player.setMsg(str);
    			sendMsg(player);
    			
    			if (player.getTicketBeanList().size() >= player.getAvailablePlayerNameList(player.getName()).size() 
    					|| (isDebug && player.getTicketBeanList().size() > 1)) {
    				str = "此轮投票结果如下：" + "\n";
    				str += player.getAllTicket();
    				str += "恭喜本轮票数最高玩家：" + player.getMaxTicketNameString() + "\n";
    				String [] maxTicketPlayer = player.getMaxTicketNames();
    				player.setMsg(str);
    				List<PlayerBean> list = player.getAllPlayerList();
    				for (PlayerBean playerBean : list) {
						if (playerBean!=null) {
							// 默认结束第一个票数最高的玩家
							if (playerBean.getName().equals(maxTicketPlayer[0])) {
								playerBean.setGameOver(true);
								playerBean.setMsg("我是冤死的。。。");
								player.setDeadName(playerBean.getName());
							}
						}
					}
    				player.setMsgToAll(true);
    				player.setMessageType(Constant.MESSAGE_TYPE.PLAYER_GAME_OVER);
    				sendMsg(player);
    				
    				// 检查游戏是否结束
    				int ghostNum = 0;
    				int noneGhostNum = 0;
    				for (PlayerBean playerBean : list) {
    					if (playerBean!=null) {
    						// 默认结束第一个票数最高的玩家
    						if (!playerBean.isGameOver()) {
	    						if (playerBean.getRole().equals(Constant.PEOPLE_TYPE.GHOST)) {
	    							ghostNum++;
	    						} else {
	    							noneGhostNum++;
	    						}
    						}
    					}
    				}
    				if (ghostNum >= noneGhostNum++) {
    					str = "平民数少于鬼数，鬼方胜利";
    					player.setMsg(str);
    					player.setMsgToAll(true);
        				player.setMessageType(Constant.MESSAGE_TYPE.GAME_OVER);
        				sendMsg(player);

    				}
    				if (ghostNum == 0) {
    					str = "所有鬼已经全部找出，平民方获胜";
    					player.setMsg(str);
    					player.setMsgToAll(true);
        				player.setMessageType(Constant.MESSAGE_TYPE.GAME_OVER);
        				sendMsg(player);
    				}
    				
    			} 
    			
//    			sendYourTicket(HostActivity.this, title,  null, null, null, player.getAvailablePlayerNameList());
    		} else if (bean.getMessageType() == Constant.MESSAGE_TYPE.PLAYER_GAME_OVER) {
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
    		} else if (bean.getMessageType() == Constant.MESSAGE_TYPE.PLAYER_IS_READY) {
    			bean.setMessageType(Constant.MESSAGE_TYPE.SHOW_MESSAGE);
    			sendMsg(bean);
    		} else if (bean.getMessageType() == Constant.MESSAGE_TYPE.PLAYER_HAS_LEFT) {
    			bean.setMessageType(Constant.MESSAGE_TYPE.SHOW_MESSAGE);
    			sendMsg(bean);
    		} else if (bean.getMessageType() == Constant.MESSAGE_TYPE.HOST_HAS_LEFT) {
    			bean.setMessageType(Constant.MESSAGE_TYPE.SHOW_MESSAGE);
    			sendMsg(bean);
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
