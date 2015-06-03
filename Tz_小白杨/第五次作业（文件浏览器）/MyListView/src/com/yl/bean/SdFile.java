package com.yl.bean;

import java.io.File;

import android.graphics.Bitmap;

public class SdFile {
	private String name;
	private Bitmap img;
	private String path;
	private File file;
	private boolean isbak;
	public boolean isIsbak() {
		return isbak;
	}
	public void setIsbak(boolean isbak) {
		this.isbak = isbak;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Bitmap getImg() {
		return img;
	}
	public void setImg(Bitmap img) {
		this.img = img;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
