package com.tz.filebrowser.bean;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

/**
 * 文件实体类
 * 
 * @author fcc
 * 
 */
public class MyFile {
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 图片
	 */
//	private Bitmap bitmap;
	private SoftReference<Bitmap> bitmap;  //软引用
	/**
	 * 文件对象
	 */
	private File file;
	/**
	 * 是否是图片
	 */
	private boolean isPic;
	/**
	 * 是否是目录
	 */
	private boolean isDir;

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

//	public Bitmap getBitmap() {
//		return bitmap;
//	}

//	public void setBitmap(Bitmap bitmap) {
//		this.bitmap = bitmap;
//	}
	
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

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

}
