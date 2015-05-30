package com.itskylin.android.filemanager.entity;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

/**
 * ClassName: SdFile
 * 
 * @Description: TODO
 * @author BlueSky QQ：345066543
 * @date 2015年5月27日
 */
public class SdFile {

	private String name;
	private File file;
	private Bitmap bitmap;
	private SoftReference<Bitmap> softBitmap;
	private boolean isDir;
	private boolean isPic;

	public SdFile(String name, File file, Bitmap bitmap, boolean isDir,
			boolean isPic) {
		super();
		this.name = name;
		this.file = file;
		this.bitmap = bitmap;
		this.isDir = isDir;
		this.isPic = isPic;
	}

	public SdFile(String name, File file, Bitmap bitmap,
			SoftReference<Bitmap> softBitmap, boolean isDir, boolean isPic) {
		super();
		this.name = name;
		this.file = file;
		this.bitmap = bitmap;
		this.softBitmap = softBitmap;
		this.isDir = isDir;
		this.isPic = isPic;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getName() {
		return name;
	}

	public File getFile() {
		return file;
	}

	public SoftReference<Bitmap> getSoftBitmap() {
		return softBitmap;
	}

	public boolean isDir() {
		return isDir;
	}

	public boolean isPic() {
		return isPic;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setSoftBitmap(SoftReference<Bitmap> softBitmap) {
		this.softBitmap = softBitmap;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

	public void setPic(boolean isPic) {
		this.isPic = isPic;
	}
}