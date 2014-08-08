package com.maple.scs.thread;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.maple.scs.util.Constant;
import com.maple.scs.util.NetUtil;

public class SCSThread extends Thread {
	
	private Context context;
	
	private boolean stopRequest = false;
	
	private int curDisable;
	
	private int curEnable;

	private boolean isDebug = false;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public int getCurDisable() {
		return curDisable;
	}

	public void setCurDisable(int curDisable) {
		this.curDisable = curDisable;
	}

	public int getCurEnable() {
		return curEnable;
	}

	public void setCurEnable(int curEnable) {
		this.curEnable = curEnable;
	}

	public SCSThread(int curDisable, int curEnable) {
		super();
		this.curDisable = curDisable;
		this.curEnable = curEnable;
	}
	
	public SCSThread() {
		super();
	}
	
	public void sleep(int time) throws InterruptedException {
		int m = time * 60;
		if (isDebug)
			m = 10; 
		SharedPreferences sharedata = context.getSharedPreferences(Constant.DATA, 0);  
		boolean isScreenOn = false;
		int t = 5;
		while (m > 0 && !stopRequest) {
			Thread.sleep(1000);
			m--;
			t--;
			if (t <0) {
				isScreenOn = sharedata.getBoolean(Constant.TOGGLE_SCREEN, false);  
				t = 5;
			}
			 if (isScreenOn) {
				 break;
			 }
		}
	}
	
	@Override
	public void run() {
		super.run();
		Log.i(Constant.TAG, "MyService Thread Id:" + Thread.currentThread().getId());
		try {
			NetUtil net = NetUtil.getInstance(context);
			SharedPreferences sharedata = context.getSharedPreferences(Constant.DATA, 0);  
			int time = 1;
			while (!stopRequest) {
				/*
				 * Get conn count
				 */
//				Log.i(Constant.TAG, "Enable: " + curEnable + " mins");
				sleep(curEnable);
				boolean isScreenOn = sharedata.getBoolean(Constant.TOGGLE_SCREEN, false);  
//		        Intent.ACTION_SCREEN_ON.equals(context..getAction());
		        if (isScreenOn) {
//		        	Log.i(Constant.TAG, "Screen is on, no need disable...");
		        	continue;
		        }
//				net.gprsEnable(false);
				disableGprs();

				
				int connCount = sharedata.getInt(Constant.TOGGLE_CONN_COUNT, 1);
				Log.i(Constant.TAG, "TOGGLE_CONN_COUNT: " + connCount);
				time = Constant.TIME_ARRAY[connCount - 1];
				connCount++;
				if (connCount >= Constant.TIME_ARRAY.length)
					connCount = Constant.TIME_ARRAY.length;
				saveIntData(context, Constant.TOGGLE_CONN_COUNT, connCount);
				
				if (time < curDisable) {
					Log.i(Constant.TAG, "Disable: " + time + " mins");
					sleep(time);
				}
				else {
					Log.i(Constant.TAG, "Disable: " + curDisable + " mins");
					sleep(curDisable);
				}
				isScreenOn = sharedata.getBoolean(Constant.TOGGLE_SCREEN, false);  
				if (isScreenOn)
		        	continue;
			
					
//				net.gprsEnable(true);
				enableGprs();
			}
			enableGprs();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Log.i(Constant.TAG, "Thread stopped...");
	}

	private void enableGprs() {
		Intent intent = new Intent();
		intent.setAction(Constant.ACTION_TYPE.ENABELE_GPRS);
		context.sendBroadcast(intent);
	}

	private void disableGprs() {
		Intent intent = new Intent();
		intent.setAction(Constant.ACTION_TYPE.DISABLE_GPRS);
		context.sendBroadcast(intent);
	}
	
	public void stopRequest() {
		this.stopRequest = true;
		Log.i(Constant.TAG, "Stop thread");
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