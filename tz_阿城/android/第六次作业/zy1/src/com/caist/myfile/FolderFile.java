package com.caist.myfile;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;



public class FolderFile {
    private String filePath;
    private String fileName;
    private Bitmap bitmap;
    private File file;
	private boolean isImage;
    private SoftReference<Bitmap> softBitmap;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public boolean isImage() {
		return isImage;
	}
	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}
	public SoftReference<Bitmap> getSoftBitmap() {
		return softBitmap;
	}
	public void setSoftBitmap(SoftReference<Bitmap> softBitmap) {
		this.softBitmap = softBitmap;
	}
  
    

}
