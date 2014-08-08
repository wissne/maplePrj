package com.maple.statusbar;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class StatusService extends IntentService {

	private static final String TAG = "StatusService";
	private static final int KUKA = 0;
	
	public StatusService() {
		super("StatusService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "��ʼ����....");
		showNotification(false);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showNotification(true);
		Log.i(TAG, "�������");
	}

	private void showNotification(boolean isFinished) {
		// TODO Auto-generated method stub
		Notification notification;
		PendingIntent pendingIntend = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		if (!isFinished) {
			notification = new Notification(R.drawable.ic_launcher, "��ʼ����", System.currentTimeMillis());
			notification.setLatestEventInfo(this, "����", "����������", pendingIntend);
			manager.notify(R.layout.activity_main, notification);
		} else {
			notification = new Notification(R.drawable.ic_launcher, "�������", System.currentTimeMillis());
			notification.setLatestEventInfo(this, "����", "�������", pendingIntend);
		}
		notification.defaults = Notification.DEFAULT_ALL;
		manager.notify(R.layout.activity_main, notification);
	}

}
