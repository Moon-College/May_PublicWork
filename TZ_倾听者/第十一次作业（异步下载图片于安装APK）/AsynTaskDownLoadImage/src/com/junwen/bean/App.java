package com.junwen.bean;

import android.graphics.drawable.Drawable;

public class App {

	private String packageName; // ����
	private Drawable icon; // ͼ��
	private String name; // Ӧ������
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
