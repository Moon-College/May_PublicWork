package com.keven.customview.activity;

import java.io.File;
import java.lang.ref.SoftReference;

import android.R.bool;
import android.graphics.Bitmap;

public class FileDec {
	private Bitmap bitmap;
	private String fileName;
	private String filePath;
    private File file;
    private boolean isPic;
    private SoftReference<Bitmap> softBitmap;
	public SoftReference<Bitmap> getSoftBitmap() {
		return softBitmap;
	}

	public void setSoftBitmap(SoftReference<Bitmap> softBitmap) {
		this.softBitmap = softBitmap;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean isPic() {
		return isPic;
	}

	public void setPic(boolean isPic) {
		this.isPic = isPic;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
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

}
