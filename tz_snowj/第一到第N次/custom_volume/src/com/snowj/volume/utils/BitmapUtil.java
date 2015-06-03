package com.snowj.volume.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {

	private BitmapFactory.Options optionsBitmap;

	public BitmapUtil() {
		super();
		optionsBitmap = new BitmapFactory.Options();
		optionsBitmap.inJustDecodeBounds = false;
		optionsBitmap.inSampleSize = 2;
	}

	/**
	 * 压缩图片
	 * 
	 * @param image
	 *            图片
	 * @return 压缩后的图片
	 */
	public Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//
		int options = 90;
		int longs = baos.toByteArray().length;
		if (longs / 1024 > 300) {
			ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//
			image= BitmapFactory.decodeStream(isBm,null,optionsBitmap);
			baos.reset();//
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);//
		}
		
		while (baos.toByteArray().length / 1024 > 30&&options>10) { //
			baos.reset();//
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);//
			longs = baos.toByteArray().length;
			options -= 10;//
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//
		Bitmap bitmap=null;
		try
		{
			bitmap = BitmapFactory.decodeStream(isBm, null, null);//
		} catch (Exception e)
		{
			System.out.println("ya suo tupian ----------------------");
			e.printStackTrace();
		}
		return bitmap;
		
	}
	

}
