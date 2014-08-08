package com.maple.scs.util;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

public class Util {
	
	public static boolean isServiceRunning(Context context, String serviceName) {
		
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
		if (serviceList.size() < 1) {
			return false;
		}
		for (RunningServiceInfo service : serviceList) {
			if (service.service.getClassName().equals(serviceName))
				return true;
		}
		return false;
	}

}
