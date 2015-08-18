package com.tz.asy.common;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tz.asy.utils.MyLog;

public class LocationReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//不停收到系统广播
		//先验证服务是否正在运行
		if(ServiceConnection.isServiceRunning(context, "com.tz.fivecourier.common.LocationService")){
			MyLog.d("LocationService Running normal");
		}else{
			Intent service = new Intent();
			service.setClass(context, LocationService.class);
			context.startService(service);//开启服务
		}
		
	}

}
