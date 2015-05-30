package com.tangcheng.demo;


import java.io.File;

import android.graphics.Bitmap;

public class FileInfo  {

	private File file;
	private int type;
	private boolean isChecked;
	private String name;
	private String filePath;
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

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	public int getType() {
		return type;
	}

    /**
     * ±È½Ï
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
	public int compareTo(FileInfo another) {
		return this.getType()-another.getType();
	}

}
