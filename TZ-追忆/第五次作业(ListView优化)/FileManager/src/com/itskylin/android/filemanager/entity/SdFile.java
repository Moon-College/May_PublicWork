package com.itskylin.android.filemanager.entity;

import java.io.File;

import android.graphics.Bitmap;

/**
 * ClassName: SdFile
 * @Description: TODO
 * @author BlueSky QQ：345066543
 * @date 2015年5月27日
 */
public class SdFile {

	private String name;
	private File file;
	private Bitmap bitmap;
	private boolean isDir;
	public SdFile(String name, File file, Bitmap bitmap, boolean isDir) {
		super();
		this.name = name;
		this.file = file;
		this.bitmap = bitmap;
		this.isDir = isDir;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public boolean isDir() {
		return isDir;
	}
	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}
	
	
}
