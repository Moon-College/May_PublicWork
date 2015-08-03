package cn.ysh.mybroadcastreceiver;

import java.util.List;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class KillBackgroundProcess extends BroadcastReceiver{
	private List<String> userPackageName;
	private ActivityManager am;
	private Utils utils;
	
	public KillBackgroundProcess(Context context){
		am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		utils = new Utils(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
			this.userPackageName = utils.getRunningUserPackageName();
			for(String str: userPackageName){
				Log.i("INFO", str);
				am.killBackgroundProcesses(str);
			}
		}
	}

}
