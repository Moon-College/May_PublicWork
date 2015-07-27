package com.zht.killprocess;

import android.graphics.drawable.Drawable;

public class TaskInfo {
     private Drawable icon;
     private String name;
     private int memory;
     private String packageName;
     private boolean isUserTask;
     private boolean isChecked;
     private int pid;
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getMemory() {
		return memory;
	}
	public void setMemory(int memory) {
		this.memory = memory;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public boolean isUserTask() {
		return isUserTask;
	}
	public void setUserTask(boolean isUserTask) {
		this.isUserTask = isUserTask;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public TaskInfo(Drawable icon, String name, int memory,
			String packageName, boolean isUserTask, boolean isChecked, int pid) {
		super();
		this.icon = icon;
		this.name = name;
		this.memory = memory;
		this.packageName = packageName;
		this.isUserTask = isUserTask;
		this.isChecked = isChecked;
		this.pid = pid;
	}
	public TaskInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
     
     
}
