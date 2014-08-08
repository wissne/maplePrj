package com.maple.scs.util;

import java.lang.reflect.Method;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

public class NetUtil {

	private static final String SET_MOBILE_DATA_ENABLED = "setMobileDataEnabled";

	private static final String GET_MOBILE_DATA_ENABLED = "getMobileDataEnabled";

	private static ConnectivityManager cm;
	
	private static NetUtil netUtil;
	
	public static NetUtil getInstance(Context context) {
		if (netUtil == null)
			netUtil = new NetUtil();
		if (context == null)
			return null;
		cm = (ConnectivityManager) context
			.getSystemService(Context.CONNECTIVITY_SERVICE);
		return netUtil;
	}

	public boolean gprsEnable(boolean bEnable) {
		boolean isOpen = gprsIsOpenMethod(GET_MOBILE_DATA_ENABLED);
		if (isOpen == !bEnable) {
			setGprsEnable(SET_MOBILE_DATA_ENABLED, bEnable);
			Log.i(Constant.TAG, "function gprsEnable(" + bEnable + ")");
		}

		return isOpen;
	}
	
	public boolean isGprsOpen() {
		return gprsIsOpenMethod(GET_MOBILE_DATA_ENABLED);
	}

	public boolean gprsIsOpenMethod(String methodName) {
		Class cmClass = cm.getClass();
		Class[] argClasses = null;
		Object[] argObject = null;

		Boolean isOpen = false;
		try {
			Method method = cmClass.getMethod(methodName, argClasses);

			isOpen = (Boolean) method.invoke(cm, argObject);
		} catch (Exception e) {
			Log.i(Constant.TAG, e.toString());
			e.printStackTrace();
		}

		return isOpen;
	}

	private void setGprsEnable(String methodName, boolean isEnable) {
		Class cmClass = cm.getClass();
		Class[] argClasses = new Class[1];
		argClasses[0] = boolean.class;

		try {
			Method method = cmClass.getMethod(methodName, argClasses);
			method.invoke(cm, isEnable);
		} catch (Exception e) {
			Log.i(Constant.TAG, e.toString());
			e.printStackTrace();
		}
	}

	private NetUtil() {
		super();
	}
	
	
	
}
