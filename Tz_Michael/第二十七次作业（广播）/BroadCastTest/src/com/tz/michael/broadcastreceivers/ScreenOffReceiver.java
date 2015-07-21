package com.tz.michael.broadcastreceivers;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/**
 * 监听屏幕变化的广播接受者
 * @author michael
 *
 */
public class ScreenOffReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action=intent.getAction();
		if(action.equals("android.intent.action.SCREEN_OFF")){
			Log.e("SCREEN_OFF--", "屏幕锁定了");
			ActivityManager am=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			am.killBackgroundProcesses(context.getPackageName());
		}else if(action.equals("android.intent.action.SCREEN_ON")){
			Log.e("SCREEN_ON--", "屏幕解锁了");
		}
	}

}
