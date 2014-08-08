package com.maple.scs.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.maple.scs.receiver.ScreenReceiver;
import com.maple.scs.receiver.TimeReceiver;
import com.maple.scs.thread.SCSThread;
import com.maple.scs.util.Constant;

public class SCSThreadService extends Service{
	private SCSThread thread;
	
	private TimeReceiver timeReceiver;
	
	public SCSThread getThread() {
		return thread;
	}

	public void setThread(SCSThread thread) {
		this.thread = thread;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(Constant.TAG, "onBind");
		return null;
	}

	@Override
	public void onCreate() {
		Log.i(Constant.TAG, "onCreate");
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		Log.i(Constant.TAG, "onDestroy");
		super.onDestroy();
		
		try {
			if (timeReceiver != null) {
				unregisterReceiver(timeReceiver);
				Log.i(Constant.TAG, "unregisterReceiver timeReceiver");
			}
		} catch (Exception e) {
			Log.e(Constant.TAG, e.toString());
			e.printStackTrace();
		}		
		
		try {
			if (thread != null && thread.isAlive()) {
				thread.stopRequest();
			}
			Log.i(Constant.TAG, "onDestroy done");
		} catch (Exception e) {
			Log.e(Constant.TAG, e.toString());
			e.printStackTrace();
		}		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(Constant.TAG, "onStartCommand");
		super.onStartCommand(intent, flags, startId);
		
		try {
			Log.i(Constant.TAG, "registerReceiver");
		} catch (Exception e) {
			Log.e(Constant.TAG, e.toString());
			e.printStackTrace();
		}
		
		SharedPreferences sharedata = getSharedPreferences(Constant.DATA, 0);  
        int timeDisable = sharedata.getInt(Constant.CURRENT_TIME_DISABLE, 60);  
        int timeEnable = sharedata.getInt(Constant.CURRENT_TIME_ENABLE, 1);
        boolean toggleThread = sharedata.getBoolean(Constant.TOGGLE_THREAD, false);
        thread = new SCSThread();
        thread.setCurDisable(timeDisable);
        thread.setCurEnable(timeEnable);
		thread.setContext(getApplicationContext());
		if (toggleThread && !thread.isAlive())
			thread.start();
		
		try {
			timeReceiver = new TimeReceiver();
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(Constant.ACTION_TYPE.ENABELE_GPRS);
			intentFilter.addAction(Constant.ACTION_TYPE.DISABLE_GPRS);
			registerReceiver(timeReceiver, intentFilter);
			Log.i(Constant.TAG, "registerReceiver timeReceiver");
		} catch (Exception e) {
			Log.e(Constant.TAG, e.toString());
			e.printStackTrace();
		}
		
		return START_STICKY;
	}
	
	
}
