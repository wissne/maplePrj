package com.example.picpopupwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	//�Զ���ĵ�������
	SelectPicPopupWindow menuWindow;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) this.findViewById(R.id.text);
        //�����ֿؼ���Ӽ�������������Զ��崰��
        tv.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				//ʵ����SelectPicPopupWindow
				menuWindow = new SelectPicPopupWindow(MainActivity.this, itemsOnClick);
				//��ʾ����
				menuWindow.showAtLocation(MainActivity.this.findViewById(R.id.main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //����layout��PopupWindow����ʾ��λ��
			}
		});
    }
    
    //Ϊ��������ʵ�ּ�����
    private OnClickListener  itemsOnClick = new OnClickListener(){

		public void onClick(View v) {
			menuWindow.dismiss();
			switch (v.getId()) {
			case R.id.btn_take_photo:
				break;
			case R.id.btn_pick_photo:				
				break;
			default:
				break;
			}
			
				
		}
    	
    };
    
}
