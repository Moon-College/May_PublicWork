package com.rocy.classqq.bean;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class SDCard {
     private String name;
     private File file;
     private Bitmap bitmap;
     private boolean isMap;
     private boolean isDownLoad;
     private SoftReference<Bitmap> softBitmap;
     
     
     
     
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * @return the bitmap
	 */
	public Bitmap getBitmap() {
		return bitmap;
	}
	/**
	 * @param bitmap the bitmap to set
	 */
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	/**
	 * @return the isMap
	 */
	public boolean isMap() {
		return isMap;
	}
	/**
	 * @param isMap the isMap to set
	 */
	public void setMap(boolean isMap) {
		this.isMap = isMap;
	}
	/**
	 * @return the isDownLoad
	 */
	public boolean isDownLoad() {
		return isDownLoad;
	}
	/**
	 * @param isDownLoad the isDownLoad to set
	 */
	public void setDownLoad(boolean isDownLoad) {
		this.isDownLoad = isDownLoad;
	}
	/**
	 * @return the softBitmap
	 */
	public SoftReference<Bitmap> getSoftBitmap() {
		return softBitmap;
	}
	/**
	 * @param softBitmap the softBitmap to set
	 */
	public void setSoftBitmap(SoftReference<Bitmap> softBitmap) {
		this.softBitmap = softBitmap;
	}
	
     
}
