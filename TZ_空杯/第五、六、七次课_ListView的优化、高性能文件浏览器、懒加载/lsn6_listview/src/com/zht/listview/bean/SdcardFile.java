/**
 * Project Name:lsn6_listview
 * File Name:SdcardFile.java
 * Package Name:com.zht.listview.bean
 * Date:2015-6-9ÉÏÎç10:25:22
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.listview.bean;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

/**
 * ClassName:SdcardFile <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-9 ÉÏÎç10:25:22 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class SdcardFile {
	private String name;
	private String filePath;
	private File file;
	private Bitmap bitmap;
	private boolean isBitmap;
	private SoftReference<Bitmap> softBitmap;
	
	
	

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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public boolean isBitmap() {
		return isBitmap;
	}

	public void setBitmap(boolean isBitmap) {
		this.isBitmap = isBitmap;
	}

}
