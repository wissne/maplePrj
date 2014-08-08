package com.maple.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	private static final String TAG = "MyService";

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new MyThread().start();
		return START_STICKY;
	}
	
	private class MyThread extends Thread {
		
		@Override
		public void run() {
			super.run();
			Log.i(TAG, "MyService Thread Id:" + Thread.currentThread().getId());
			Log.i(TAG, "File loading...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
