package com.maple.scs.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.maple.scs.util.Constant;
import com.maple.scs.util.NetUtil;

public class ScreenReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		NetUtil net = NetUtil.getInstance(context);
		
		if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
			net.gprsEnable(true);
			Log.d(Constant.TAG, "on");
			saveBooleanData(context, Constant.TOGGLE_SCREEN, true);
		} else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
//			net.gprsEnable(false);
			Log.d(Constant.TAG, "off");
			saveBooleanData(context, Constant.TOGGLE_SCREEN, false);
			saveIntData(context, Constant.TOGGLE_CONN_COUNT, 1);
		} else {
			Log.d(Constant.TAG, "nothing: " + intent.getAction());
		}
	}
	
	private void saveBooleanData(Context context, String str, boolean val) {
		SharedPreferences.Editor editor = context.getSharedPreferences(Constant.DATA, 0).edit();  
		editor.putBoolean(str, val);
		editor.commit();
	}
	
	private void saveIntData(Context context, String str, int val) {
		SharedPreferences.Editor editor = context.getSharedPreferences(Constant.DATA, 0).edit();  
		editor.putInt(str, val);
		editor.commit();
	}

}
