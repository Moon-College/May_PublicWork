package com.jim.sdlist.beans;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class SDEntitiy {
	private String file_name;// 文件名
	private String file_path;// 文件路径
	private String file_count;// 文件数量或大小
	private Bitmap bitmap;// 图标
	private SoftReference<Bitmap> softBitmap;// 图片文件的软应用图标
	private boolean isPic;// 判断该文件是否是图片
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_count() {
		return file_count;
	}

	public void setFile_count(String file_count) {
		this.file_count = file_count;
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

	public boolean isPic() {
		return isPic;
	}

	public void setPic(boolean isPic) {
		this.isPic = isPic;
	}
}
