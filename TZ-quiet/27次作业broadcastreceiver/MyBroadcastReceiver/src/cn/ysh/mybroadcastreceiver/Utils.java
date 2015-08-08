package cn.ysh.mybroadcastreceiver;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Utils {
	private PackageManager packageManager;
	private ActivityManager am;
	private Context context;
	public Utils(Context context){
		packageManager = context.getPackageManager();
		this.context =context;
		am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	}
	
	public List<String> getRunningUserPackageName(){
		List<PackageInfo> list = new ArrayList<PackageInfo>();
		list = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES|PackageManager.GET_UNINSTALLED_PACKAGES);
		List<RunningAppProcessInfo> runningAppInfo = am.getRunningAppProcesses();
		List<String> runningUserAppList = new ArrayList<String>();
		for(PackageInfo info : list){
			ApplicationInfo appInfo = info.applicationInfo;
			//用户应用
			if((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
				//正在运行的用户应用
				for(RunningAppProcessInfo runningInfo : runningAppInfo){
					if(appInfo.processName.equals(runningInfo.processName) && !(appInfo.packageName.equals(context.getPackageName()))){
						runningUserAppList.add(appInfo.packageName);
					}
				}
			}
		}
		return runningUserAppList;
	}
}
