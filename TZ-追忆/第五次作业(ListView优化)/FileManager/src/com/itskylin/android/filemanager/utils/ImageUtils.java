package com.itskylin.android.filemanager.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * ClassName: ImageUtils
 * 
 * @Description: TODO
 * @author BlueSky QQ：345066543
 * @date 2015年5月27日
 */
public class ImageUtils {

	public static Bitmap getThumbnail(Context context, String path) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;

		Bitmap bitmap = BitmapFactory.decodeFile(path, options);
		int viewWidth = DisplayUtil.dip2px(context, 100.0f);

		int scaleWidth = bitmap.getWidth() / viewWidth;
		int scaleHeight = bitmap.getHeight() / viewWidth;
		int scale = (int) Math.sqrt(scaleWidth * scaleHeight);

		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;
		return bitmap;
	}
}
