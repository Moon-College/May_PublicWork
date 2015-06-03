package com.tz.file.bean;

import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class FileItem {
	private String fileName;
	
	private String filePath;
	
	private String fileSize;
	
	private boolean isDir; //true:Ä¿Â¼ false£ºÎÄ¼þ
	
	private String fileType;
	
	private String createTime;
	
	private SoftReference<Bitmap> softBitmap;
	
	public SoftReference<Bitmap> getSoftBitmap() {
		return softBitmap;
	}
	public void setSoftBitmap(SoftReference<Bitmap> softBitmap) {
		this.softBitmap = softBitmap;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Override
	public String toString() {
		return "fileName = " + fileName + ",filePath = " + filePath + ",fileSize = " + fileSize + ",createTime = " + createTime
				 + ",fileType = " + fileType;
	}

	

}
