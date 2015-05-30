package com.junwen.bean;

import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class FileItem {
	//文件名字
	private String file_Name;
	//文件路径
	private String file_path;
	//普通图片
	private Bitmap bitmap;
	//软引用图片
	private SoftReference<Bitmap> softBitmap;
	//是否是图片
	private boolean ispic;
	public String getFile_Name() {
		return file_Name;
	}
	public void setFile_Name(String file_Name) {
		this.file_Name = file_Name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public SoftReference<Bitmap> getSoftBitmap() {
		return softBitmap;
	}
	public void setSoftBitmap(SoftReference<Bitmap> softBitmap) {
		this.softBitmap = softBitmap;
	}
	public boolean isIspic() {
		return ispic;
	}
	public void setIspic(boolean ispic) {
		this.ispic = ispic;
	}
	
}
