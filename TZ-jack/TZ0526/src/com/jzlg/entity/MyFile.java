package com.jzlg.entity;

import java.io.File;

import android.graphics.Bitmap;

public class MyFile {
	
	private String name;//�ļ� ����
	
	private String filepath;//�ļ� ·��
	
	private File file;//�ļ�����

	private Bitmap bitmap;//ͼƬ ��Դ 
	
	private boolean TNT;//�ж� �Ƿ�Ϊ ͼƬ

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
