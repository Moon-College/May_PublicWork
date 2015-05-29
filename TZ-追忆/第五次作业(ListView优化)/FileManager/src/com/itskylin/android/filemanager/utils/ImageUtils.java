package com.itskylin.android.filemanager.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * ClassName: ImageUtils
 * 
 * @Description: TODO
 * @author BlueSky QQ：345066543
 * @date 2015年5月27日
 */
public class ImageUtils {

	private static String TAG = "ImageUtils";
	private static final int LIMIT_WIDTH = 100;
	private static final int LIMIT_HEIGHT = 100;

	public static Bitmap getThumbnail(Context context, String path) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;

		Bitmap bitmap = null;
		Log.i(TAG, "image path = " + path);
		bitmap = BitmapFactory.decodeFile(path, options);

		int scaleWidth = options.outWidth / LIMIT_WIDTH;
		int scaleHeight = options.outHeight / LIMIT_HEIGHT;
		Log.i(TAG, "options.outWidth=" + options.outWidth
				+ ",options.outHeight = " + options.outHeight);
		Log.i(TAG, "-------------------------------------");

		Log.i(TAG, "W=" + scaleWidth + ",h = " + scaleHeight);
		if (scaleWidth > LIMIT_WIDTH && scaleHeight > LIMIT_HEIGHT) {
			if (scaleWidth > scaleHeight) {
				options.inSampleSize = (scaleWidth / LIMIT_WIDTH + scaleHeight
						/ LIMIT_HEIGHT) / 2;
			} else {
				options.inSampleSize = (scaleWidth / LIMIT_HEIGHT + scaleHeight
						/ LIMIT_WIDTH) / 2;
			}
		} else {
			options.inSampleSize = (scaleWidth + scaleHeight) / 2;
		}
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(path, options);
		return bitmap;
	}
}
