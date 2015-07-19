package com.junwen.broadcastreceiver.reveiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkBroadcastReveiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取管理器
		ConnectivityManager com = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = com.getActiveNetworkInfo();
		if(activeNetworkInfo!=null){
			if(activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
				System.out.println("3G/4G网络");
			}else if(activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI){
				System.out.println("Wifi网络");
			}
		}else{
			System.out.println("网络断开了!!!!");
		}
	}

}
