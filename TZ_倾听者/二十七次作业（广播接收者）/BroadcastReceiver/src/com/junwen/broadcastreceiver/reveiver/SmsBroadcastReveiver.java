package com.junwen.broadcastreceiver.reveiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsBroadcastReveiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//短信拦截
		Bundle extras = intent.getExtras();
		Object[] object = (Object[]) extras.get("pdus");
		SmsMessage[] message = new SmsMessage[object.length];
		for (int i = 0; i < message.length; i++) {
			message[i]= SmsMessage.createFromPdu((byte[])object[i]);
		}
		for (SmsMessage smsMessage : message) {
			System.out.println("短信来源："
					+ smsMessage.getDisplayOriginatingAddress() + "\n"
					+ "短信内容：" + smsMessage.getDisplayMessageBody() + "\n");
		}
		this.abortBroadcast();
	}
}
