package com.dingding.fivetwentyseven.bean;

import java.io.File;

import android.graphics.Bitmap;

public class SdFile {
	private String name;
	private String filePath;
	private File file;
	private Bitmap bitmap;
	private boolean isPic;
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
	public boolean isPic() {
		return isPic;
	}
	public void setPic(boolean isPic) {
		this.isPic = isPic;
	}
}