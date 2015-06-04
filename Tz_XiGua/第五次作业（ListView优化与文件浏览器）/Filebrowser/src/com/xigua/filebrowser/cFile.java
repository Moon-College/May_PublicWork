package com.xigua.filebrowser;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class cFile {
	private String name;
	private String filePath;
	private File file;
	private Bitmap bitmap;
	private boolean isPic;
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
	public boolean isPic() {
		return isPic;
	}
	public void setPic(boolean isPic) {
		this.isPic = isPic;
	}
}
