package com.riders.browser.entity;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class SdFile {
	private String fileName;
	private String filePath;
	private File file;
	private Bitmap bitmap;
	private SoftReference<Bitmap> softReference;
	public SoftReference<Bitmap> getSoftReference() {
		return softReference;
	}
	public void setSoftReference(SoftReference<Bitmap> softReference) {
		this.softReference = softReference;
	}
	private boolean isPic;
	public boolean isPic() {
		return isPic;
	}
	public void setPic(boolean isPic) {
		this.isPic = isPic;
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
	
}
