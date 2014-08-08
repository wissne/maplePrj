package com.aurogon.zhuogui.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.MulticastLock;

public class ZGUtil {

	public static void saveStringData(Activity activity, String str, String val) {
		SharedPreferences.Editor editor = activity.getSharedPreferences(
				Constant.HOST_DATA.DATA, 0).edit();
		editor.putString(str, val);
		editor.commit();
	}

	public static void saveIntData(Activity activity, String str, int val) {
		SharedPreferences.Editor editor = activity.getSharedPreferences(
				Constant.HOST_DATA.DATA, 0).edit();
		editor.putInt(str, val);
		editor.commit();
	}

	public static void saveBooleanData(Activity activity, String str,
			boolean val) {
		SharedPreferences.Editor editor = activity.getSharedPreferences(
				Constant.HOST_DATA.DATA, 0).edit();
		editor.putBoolean(str, val);
		editor.commit();
	}

	public static String getStringData(Activity activity, String str) {
		SharedPreferences sharedata = activity.getSharedPreferences(
				Constant.HOST_DATA.DATA, 0);
		return sharedata.getString(str, null);
	}

	public static int getIntData(Activity activity, String str) {
		SharedPreferences sharedata = activity.getSharedPreferences(
				Constant.HOST_DATA.DATA, 0);
		return sharedata.getInt(str, 0);
	}

	public static boolean getBooleanData(Activity activity, String str) {
		SharedPreferences sharedata = activity.getSharedPreferences(
				Constant.HOST_DATA.DATA, 0);
		return sharedata.getBoolean(str, false);
	}
	
	public static void enableMulticastLock(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		MulticastLock multicastLock = wifiManager  
		        .createMulticastLock("multicast.test");  
		multicastLock.acquire();
	}


	public static String getWIFIIP(Context context) {
		WifiManager wifimanage = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);

		if (!wifimanage.isWifiEnabled()) {
			wifimanage.setWifiEnabled(true);
		}

		WifiInfo wifiinfo = wifimanage.getConnectionInfo();

		String ip = intToIp(wifiinfo.getIpAddress());
		return ip;
	}

	public static String intToIp(int i) {
		return "" + (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "."
				+ ((i >> 16) & 0xFF) + "." + ((i >> 24) & 0xFF);
	}
}
