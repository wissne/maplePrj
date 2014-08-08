package com.maple.scs.util;

public class Constant {
	public static final String TAG = "SCS_TAG";
	public static final String CURRENT_TIME_DISABLE = "CURRENT_TIME_DISABLE";
	public static final String CURRENT_TIME_ENABLE = "CURRENT_TIME_ENABLE";
	public static final int DEFAULT_TIME_DISABLE = 60;
	public static final int DEFAULT_TIME_ENABLE = 1;
	public static final String DATA = "data";
	public static final String TOGGLE_THREAD = "TOGGLE_THREAD";
	public static final String TOGGLE_SCREEN = "TOGGLE_SCREEN";
	public static final String TOGGLE_CONN_COUNT = "TOGGLE_CONN_COUNT";
	public static final int [] TIME_ARRAY = {1,2,4,8,16,32,64,128};
	public static final String MSG = "msg";
	public interface ACTION_TYPE {
		public static final String ENABELE_GPRS = "com.maple.enableGprs";
		public static final String DISABLE_GPRS = "com.maple.disableGprs";
	}
}
