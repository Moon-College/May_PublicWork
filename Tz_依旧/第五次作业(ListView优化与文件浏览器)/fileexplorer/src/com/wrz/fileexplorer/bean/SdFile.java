package com.wrz.fileexplorer.bean;

import java.io.File;

import android.graphics.Bitmap;

public class SdFile {

	private String name; // 文件名称
	private String filePath; // 文件路径
	private Bitmap bitmap; // 文件图片
	private File file; // 文件对象
	private boolean isPic; // 是否是图片
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
