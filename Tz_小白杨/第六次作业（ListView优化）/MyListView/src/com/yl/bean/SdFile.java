package com.yl.bean;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class SdFile {
	private String name;
	private Bitmap img;
	private String path;
	private File file;
	private boolean isimg;
	private SoftReference<Bitmap> softBitmap;
	public boolean isIsimg() {
		return isimg;
	}
	public void setIsimg(boolean isimg) {
		this.isimg = isimg;
	}
	public SoftReference<Bitmap> getSoftBitmap() {
		return softBitmap;
	}
	public void setSoftBitmap(SoftReference<Bitmap> softBitmap) {
		this.softBitmap = softBitmap;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Bitmap getImg() {
		return img;
	}
	public void setImg(Bitmap img) {
		this.img = img;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
