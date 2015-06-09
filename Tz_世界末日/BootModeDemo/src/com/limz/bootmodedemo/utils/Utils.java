package com.limz.bootmodedemo.utils;

import java.util.ArrayList;

import com.limz.bootmodedemo.manage.ActivityStackManager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Utils {
	
	public static void showActivityToast(Context context) {
		ActivityStackManager manager = ActivityStackManager.getInstance(context);
		ArrayList<Activity> list = manager.getActivityList();
		if(list == null || list.size() == 0) {
			Toast.makeText(context, "ActivityջΪ��", Toast.LENGTH_LONG).show();
			return;
		}
		StringBuffer sb = new StringBuffer();
		Log.d("mingzhu", "list size = " + list.size());
		for(int i=0; i<list.size(); i++) {
			sb.append(list.get(i).hashCode());
			sb.append(" -> ");
		}
		String string = "";
		if(sb.length() > 3) {
			string = (String) sb.subSequence(0, sb.length() - 3);
		}
		Toast.makeText(context, "ActivityջΪ " + string, Toast.LENGTH_LONG).show();
		Log.d("mingzhu", "Activity start : ");
		Log.d("mingzhu", string);
		Log.d("mingzhu", "Activity end ");
		
	}
}
