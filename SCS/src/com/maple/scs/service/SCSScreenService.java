package com.maple.scs.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.maple.scs.receiver.ScreenReceiver;
import com.maple.scs.util.Constant;

public class SCSScreenService extends Service {
	
	private ScreenReceiver screenOnOffReceiver;

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
			if (screenOnOffReceiver != null) {
				unregisterReceiver(screenOnOffReceiver);
				Log.i(Constant.TAG, "unregisterReceiver");
			}
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
			screenOnOffReceiver = new ScreenReceiver();
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(Intent.ACTION_SCREEN_ON);
			intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
			registerReceiver(screenOnOffReceiver, intentFilter);
			Log.i(Constant.TAG, "registerReceiver");
		} catch (Exception e) {
			Log.e(Constant.TAG, e.toString());
			e.printStackTrace();
		}
		
		return START_STICKY;
	}
	
}

