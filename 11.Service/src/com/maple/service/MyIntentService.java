package com.maple.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

	private static final String TAG = "MyIntentService";

	public MyIntentService(String name) {
		super(name);
	}
	
	public MyIntentService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.i(TAG, "MyIntentService Thread Id:" + Thread.currentThread().getId());
		Log.i(TAG, "File loading...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
