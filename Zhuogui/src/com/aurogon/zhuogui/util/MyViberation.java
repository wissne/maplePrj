package com.aurogon.zhuogui.util;

import android.app.Activity;
import android.os.Vibrator;

public class MyViberation {

	private Vibrator vibrator;  
	
	public void doVibrate(Activity activity) {
		vibrator = (Vibrator) activity.getSystemService(Activity.VIBRATOR_SERVICE);   
		long[] pattern = {800,50}; // OFF/ON/OFF/ON...   
		vibrator.vibrate(pattern, -1);
		//-1���ظ�����-1Ϊ��pattern��ָ���±꿪ʼ�ظ�   
	}
}
