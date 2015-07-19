package com.junwen.broadcastreceiver.reveiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadcastReveiver extends BroadcastReceiver{
	//回调
	private OnReveiver onReveiverListener;
	@Override
	public void onReceive(Context context, Intent intent) {
		//当收到有序广播，则回调到Activity里
		if(onReveiverListener!=null){
			onReveiverListener.onReveiver(intent);
		}
		
	}
	
	public interface OnReveiver{
		void onReveiver(Intent intent);
	}
	public void setOnReveiverListener(OnReveiver onReveiverListener) {
		this.onReveiverListener = onReveiverListener;
	}
	
}
