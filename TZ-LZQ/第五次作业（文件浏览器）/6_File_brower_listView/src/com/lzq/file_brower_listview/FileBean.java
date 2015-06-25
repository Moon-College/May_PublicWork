package com.lzq.file_brower_listview;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class FileBean {

	public String file_path;
	public File file;
	public Bitmap bitmap;
	public String name;
	public boolean isPic;
	public SoftReference<Bitmap> softBitmap;

}
