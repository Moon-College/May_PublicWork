package com.jzlg.entity;

import java.io.File;

import android.graphics.Bitmap;

public class MyFile {
	
	private String name;//文件 名称
	
	private String filepath;//文件 路径
	
	private File file;//文件对象

	private Bitmap bitmap;//图片 资源 
	
	private boolean TNT;//判断 是否为 图片

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public boolean isTNT() {
		return TNT;
	}

	public void setTNT(boolean tNT) {
		TNT = tNT;
	}
		
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
