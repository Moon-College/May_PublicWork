package com.junwen.bean;

import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class FileItem {
	//�ļ�����
	private String file_Name;
	//�ļ�·��
	private String file_path;
	//��ͨͼƬ
	private Bitmap bitmap;
	//������ͼƬ
	private SoftReference<Bitmap> softBitmap;
	//�Ƿ���ͼƬ
	private boolean ispic;
	public String getFile_Name() {
		return file_Name;
	}
	public void setFile_Name(String file_Name) {
		this.file_Name = file_Name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public SoftReference<Bitmap> getSoftBitmap() {
		return softBitmap;
	}
	public void setSoftBitmap(SoftReference<Bitmap> softBitmap) {
		this.softBitmap = softBitmap;
	}
	public boolean isIspic() {
		return ispic;
	}
	public void setIspic(boolean ispic) {
		this.ispic = ispic;
	}
	
}
