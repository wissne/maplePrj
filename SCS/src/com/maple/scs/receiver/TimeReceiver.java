package com.maple.scs.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.maple.scs.util.Constant;
import com.maple.scs.util.NetUtil;

public class TimeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		NetUtil net = NetUtil.getInstance(context);
		
		if (Constant.ACTION_TYPE.ENABELE_GPRS.equals(intent.getAction())) {
			net.gprsEnable(true);
			Log.d(Constant.TAG, "Receive: Enable");
		} else if (Constant.ACTION_TYPE.DISABLE_GPRS.equals(intent.getAction())) {
			net.gprsEnable(false);
			Log.d(Constant.TAG, "Receive: Disable");
		} else {
			Log.d(Constant.TAG, "nothing: " + intent.getAction());
		}
	}

}
