package com.tz.michael.broadcastreceivers;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
/**
 * 短信拦截广播接受者
 * @author michael
 *
 */
public class MessageInterceptor extends BroadcastReceiver {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//获取短信内容
		Bundle bundle=intent.getExtras();
		//多条短信
		Object[]objs = (Object[]) bundle.get("pdus");
		for(Object object:objs){
			byte[] pdu=(byte[])object;
			//转化得到一个消息对象
			SmsMessage message = SmsMessage.createFromPdu(pdu);
			//内容
			String body = message.getMessageBody();
			//发送者
			String sender = message.getOriginatingAddress();
			//收到的时间
			long millis = message.getTimestampMillis();
			String date = sdf.format(new Date(millis));
			if ("7788".equals(sender)) {
				Log.d("tz", "拦截："+sender+","+body+","+date);
				abortBroadcast();
			}
		}
	}

}
