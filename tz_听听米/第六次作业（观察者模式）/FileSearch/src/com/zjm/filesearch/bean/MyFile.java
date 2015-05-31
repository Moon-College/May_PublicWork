package com.zjm.filesearch.bean;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class MyFile {
	
	private String path;
	
	private String name;
	
	private SoftReference<Bitmap> bitmap;
	
	private File file;
	
	private boolean isPic;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SoftReference<Bitmap> getBitmap() {
		return bitmap;
	}

	public void setBitmap(SoftReference<Bitmap> bitmap) {
		this.bitmap = bitmap;
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

	
	
	

}
