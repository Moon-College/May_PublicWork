package com.limz.bootmodedemo.manage;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;

public class ActivityStackManager {
	private ArrayList<Activity> activities;
	private static ActivityStackManager manager;
	
	public ActivityStackManager(Context context) {
		activities = new ArrayList<Activity>();
		manager = this;
	}
	
	public ActivityStackManager() {
	}

	public static ActivityStackManager getInstance(Context context) {
		if(context == null) {
			return null;
		}
		if(manager == null) {
			manager = new ActivityStackManager(context);
		}
		return manager;
	}
	
	public void addActivity(Activity activity) {
		if(activities != null) {
			activities.add(activity);
		}
	}
	
	public ArrayList<Activity> getActivityList() {
		return activities;
	}
	
	public void removeActivityList(Activity activity) {
		if(activities != null) {
			if(activities.contains(activity)) {
				activities.remove(activity);
			}
		}
	}
}
