package com.qfx.filebrowser.bean;

import android.graphics.Bitmap;

public class SdFile {

	private String fileName;
	private String filePath;
	private Bitmap fileBmp;
	private boolean isDirectory;
	
	
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

	public Bitmap getFileBmp() {
		return fileBmp;
	}

	public void setFileBmp(Bitmap fileBmp) {
		this.fileBmp = fileBmp;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	
	
}
