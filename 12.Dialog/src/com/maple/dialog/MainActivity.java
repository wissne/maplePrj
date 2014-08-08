package com.maple.dialog;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity{
	
	protected static final int MAX_PROGRESS = 100;
	final String[] mItems = {"item0","item1","itme2","item3","itme4","item5","item6"};
	int mSingleChoiceID = -1;
	ProgressDialog mProgressDialog;
	ArrayList <Integer>MultiChoiceID = new ArrayList <Integer>();
	
	private final OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			
			Button btn = (Button) v;
			builder.setIcon(R.drawable.ic_launcher);
			switch (btn.getId()) {
			case R.id.Button01:
				builder.setTitle("你确定要离开吗？");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showDialog("你选择了确定");
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showDialog("你选择了取消");
					}
				});
				break;
			case R.id.Button02:
				builder.setTitle("投票");
				builder.setMessage("您认为什么样的内容能吸引您？");
				builder.setPositiveButton("有趣味的", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showDialog("你选择了有趣味的");
					}
				});
				builder.setNeutralButton("有思想的", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showDialog("你选择了有思想的");
					}
				});
				builder.setNegativeButton("主题强的", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showDialog("你选择了取消主题强的");
					}
				});
				break;
			case R.id.Button03:
				builder.setTitle("列表选择");
				builder.setItems(mItems, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						showDialog("你选择的ID为" + which + "," + mItems[which]);
					}
				});
				break;
			case R.id.Button04:
				mSingleChoiceID = -1;
				builder.setTitle("单项选择");
				builder.setSingleChoiceItems(mItems, 0, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						mSingleChoiceID = whichButton;
						showDialog("你选择的id为" + whichButton + " , " + mItems[whichButton]);
					}
					
				});
				
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int whichButton) {
	                     if(mSingleChoiceID > 0) {
	                     showDialog("你选择的是" + mSingleChoiceID);
	                     }
	                 }
	             });
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int whichButton) {

	                 }
	             });
				
				break;
			case R.id.Button05:
				mProgressDialog = new ProgressDialog(MainActivity.this);
                mProgressDialog.setTitle("进度条窗口");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setMax(MAX_PROGRESS);
                mProgressDialog.setButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //这里添加点击后的逻辑
                    }
                });
                mProgressDialog.setButton2("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //这里添加点击后的逻辑
                    }
                });
                mProgressDialog.show();
                new Thread(new Runnable() {
                	public void run() {
                        int Progress = 0;
                        while(Progress < MAX_PROGRESS) {
                        try {
                            Thread.sleep(100);
                            Progress++;  
                            mProgressDialog.incrementProgressBy(1);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                         
                        }
                    
                    }
                }).start();

				break;
			case R.id.Button06:
		        MultiChoiceID.clear();
				builder.setTitle("多项选择");
				builder.setMultiChoiceItems(mItems, new boolean[] { false,
						false, false, false, false, false, false },
						new DialogInterface.OnMultiChoiceClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton, boolean isChecked) {
								if (isChecked) {
									MultiChoiceID.add(whichButton);
									showDialog("你选择的id为" + whichButton + " , "
											+ mItems[whichButton]);
								} else {
									MultiChoiceID.remove(whichButton);
								}

							}
						});
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								String str = "";
								int size = MultiChoiceID.size();
								for (int i = 0; i < size; i++) {
									str += mItems[MultiChoiceID.get(i)] + ", ";
								}
								showDialog("你选择的是" + str);
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						});
				break;
			case R.id.Button07:
				LayoutInflater factory = LayoutInflater.from(MainActivity.this);
				final View textEntryView = factory.inflate(R.layout.test, null);
				builder.setTitle("自定义输入框");
				builder.setView(textEntryView);
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								EditText userName = (EditText) textEntryView
										.findViewById(R.id.etUserName);
								EditText password = (EditText) textEntryView
										.findViewById(R.id.etPassWord);
								showDialog("姓名 ："
										+ userName.getText().toString() + "密码："
										+ password.getText().toString());
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						});
				break;
			case R.id.Button08:
			    mProgressDialog = new ProgressDialog(MainActivity.this);
	            mProgressDialog.setTitle("读取ing");
	            mProgressDialog.setMessage("正在读取中请稍候");
	            mProgressDialog.setIndeterminate(true);
	            mProgressDialog.setCancelable(true);
	            mProgressDialog.show();
				break;

			default:
				break;
			}
			builder.create().show();
		}


	};
	
	private Button buttons[];	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buttons = new Button[8];
        buttons[0] = (Button) findViewById(R.id.Button01);
        buttons[1] = (Button) findViewById(R.id.Button02);
        buttons[2] = (Button) findViewById(R.id.Button03);
        buttons[3] = (Button) findViewById(R.id.Button04);
        buttons[4] = (Button) findViewById(R.id.Button05);
        buttons[5] = (Button) findViewById(R.id.Button06);
        buttons[6] = (Button) findViewById(R.id.Button07);
        buttons[7] = (Button) findViewById(R.id.Button08);
        
        for (int i = 0; i < buttons.length; i++) {
        	buttons[i].setOnClickListener(listener);
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void showDialog(String str) {
    	new AlertDialog.Builder(MainActivity.this).setMessage(str).show();
    }
}
