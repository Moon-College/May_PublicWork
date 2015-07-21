package com.tz.michael.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CallHandle extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String number = getResultData();
		//ip拨号
		//setResultData("17951"+number);
		//电话拦截
		if ("6677".equals(number)) {
			//终止
			/*sendOrderedBroadcast(broadcastIntent, PERMISSION, new OutgoingCallReceiver(),
	                null, Activity.RESULT_OK, number, null);*/
			/**
			 * 有序广播指定接受者后，不可以终止，无序广播不可以终止
			 */
			//abortBroadcast();
			setResultData(null);
		}
	}

}
