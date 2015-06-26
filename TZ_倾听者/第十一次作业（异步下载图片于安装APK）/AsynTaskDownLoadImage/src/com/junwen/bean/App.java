package com.junwen.bean;

import android.graphics.drawable.Drawable;

public class App {

	private String packageName; // 报名
	private Drawable icon; // 图标
	private String name; // 应用名字
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	
	
	
	
}
