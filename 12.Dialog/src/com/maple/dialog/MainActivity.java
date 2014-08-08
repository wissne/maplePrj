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
				builder.setTitle("��ȷ��Ҫ�뿪��");
				builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showDialog("��ѡ����ȷ��");
					}
				});
				builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showDialog("��ѡ����ȡ��");
					}
				});
				break;
			case R.id.Button02:
				builder.setTitle("ͶƱ");
				builder.setMessage("����Ϊʲô������������������");
				builder.setPositiveButton("��Ȥζ��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showDialog("��ѡ������Ȥζ��");
					}
				});
				builder.setNeutralButton("��˼���", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showDialog("��ѡ������˼���");
					}
				});
				builder.setNegativeButton("����ǿ��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						showDialog("��ѡ����ȡ������ǿ��");
					}
				});
				break;
			case R.id.Button03:
				builder.setTitle("�б�ѡ��");
				builder.setItems(mItems, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						showDialog("��ѡ���IDΪ" + which + "," + mItems[which]);
					}
				});
				break;
			case R.id.Button04:
				mSingleChoiceID = -1;
				builder.setTitle("����ѡ��");
				builder.setSingleChoiceItems(mItems, 0, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						mSingleChoiceID = whichButton;
						showDialog("��ѡ���idΪ" + whichButton + " , " + mItems[whichButton]);
					}
					
				});
				
				builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int whichButton) {
	                     if(mSingleChoiceID > 0) {
	                     showDialog("��ѡ�����" + mSingleChoiceID);
	                     }
	                 }
	             });
				builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int whichButton) {

	                 }
	             });
				
				break;
			case R.id.Button05:
				mProgressDialog = new ProgressDialog(MainActivity.this);
                mProgressDialog.setTitle("����������");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setMax(MAX_PROGRESS);
                mProgressDialog.setButton("ȷ��", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //������ӵ������߼�
                    }
                });
                mProgressDialog.setButton2("ȡ��", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //������ӵ������߼�
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
				builder.setTitle("����ѡ��");
				builder.setMultiChoiceItems(mItems, new boolean[] { false,
						false, false, false, false, false, false },
						new DialogInterface.OnMultiChoiceClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton, boolean isChecked) {
								if (isChecked) {
									MultiChoiceID.add(whichButton);
									showDialog("��ѡ���idΪ" + whichButton + " , "
											+ mItems[whichButton]);
								} else {
									MultiChoiceID.remove(whichButton);
								}

							}
						});
				builder.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								String str = "";
								int size = MultiChoiceID.size();
								for (int i = 0; i < size; i++) {
									str += mItems[MultiChoiceID.get(i)] + ", ";
								}
								showDialog("��ѡ�����" + str);
							}
						});
				builder.setNegativeButton("ȡ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						});
				break;
			case R.id.Button07:
				LayoutInflater factory = LayoutInflater.from(MainActivity.this);
				final View textEntryView = factory.inflate(R.layout.test, null);
				builder.setTitle("�Զ��������");
				builder.setView(textEntryView);
				builder.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								EditText userName = (EditText) textEntryView
										.findViewById(R.id.etUserName);
								EditText password = (EditText) textEntryView
										.findViewById(R.id.etPassWord);
								showDialog("���� ��"
										+ userName.getText().toString() + "���룺"
										+ password.getText().toString());
							}
						});
				builder.setNegativeButton("ȡ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						});
				break;
			case R.id.Button08:
			    mProgressDialog = new ProgressDialog(MainActivity.this);
	            mProgressDialog.setTitle("��ȡing");
	            mProgressDialog.setMessage("���ڶ�ȡ�����Ժ�");
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
