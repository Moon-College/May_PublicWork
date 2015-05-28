package com.jim.mysdcardlist.beans;

import java.io.File;

import android.graphics.Bitmap;

public class SDFiler {
	private String file_name;// 文件名
	private int file_img;// 文件图标
	private String file_url;// 文件路径
	private Bitmap bitmap;// 图片
	private String file_count;// 文件个数
	private boolean isPic;// 是否是图片
	private File file;// 文件

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFile_name() {
		return file_name;
	}

	public int getFile_img() {
		return file_img;
	}

	public String getFile_url() {
		return file_url;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public String getFile_count() {
		return file_count;
	}

	public boolean isPic() {
		return isPic;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setFile_img(int file_img) {
		this.file_img = file_img;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public void setFile_count(String file_count) {
		this.file_count = file_count;
	}

	public void setPic(boolean isPic) {
		this.isPic = isPic;
	}

}
