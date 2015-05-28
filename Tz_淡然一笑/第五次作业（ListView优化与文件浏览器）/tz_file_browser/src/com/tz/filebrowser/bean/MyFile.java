package com.tz.filebrowser.bean;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

/**
 * �ļ�ʵ����
 * 
 * @author fcc
 * 
 */
public class MyFile {
	/**
	 * �ļ���
	 */
	private String fileName;
	/**
	 * �ļ�·��
	 */
	private String filePath;
	/**
	 * ͼƬ
	 */
//	private Bitmap bitmap;
	private SoftReference<Bitmap> bitmap;  //������
	/**
	 * �ļ�����
	 */
	private File file;
	/**
	 * �Ƿ���ͼƬ
	 */
	private boolean isPic;
	/**
	 * �Ƿ���Ŀ¼
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
