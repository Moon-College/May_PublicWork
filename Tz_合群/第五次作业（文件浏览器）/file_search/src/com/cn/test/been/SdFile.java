package com.cn.test.been;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

/**
 * Created on2015-5-28 ÉÏÎç10:04:12 SbFile.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class SdFile {
	private String name;
	private String filepath;
	private File file;
	private Bitmap bitmap;
	private boolean ispic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
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

	public boolean isIspic() {
		return ispic;
	}

	public void setIspic(boolean ispic) {
		this.ispic = ispic;
	}

}
