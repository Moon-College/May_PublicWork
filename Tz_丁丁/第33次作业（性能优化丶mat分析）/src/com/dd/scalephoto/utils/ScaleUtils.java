package com.dd.scalephoto.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

public class ScaleUtils {
	public static Bitmap getScaleBitmapByUri(Uri uri,Context context,int w) throws FileNotFoundException{
		Bitmap bitmap;
		int meaW;
		InputStream is = context.getContentResolver().openInputStream(uri);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeStream(is, null, options);
		meaW = options.outWidth;
		if (meaW>w) {
			options.inSampleSize = meaW/w;
			options.inJustDecodeBounds = false;
			String path = getPicPathByUri(uri,context);
			bitmap = BitmapFactory.decodeFile(path, options);
		}
		return bitmap;
	}
	public static Bitmap getScaleBitmapByPath(String path,Context context,int w){
		Bitmap bitmap;
		int meaW;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeFile(path, options);
		meaW = options.outWidth;
		if (meaW>w) {
			options.inSampleSize = meaW/w;
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(path, options);
		}else {
			bitmap = BitmapFactory.decodeFile(path);
		}
		return bitmap;
	}
	private static String getPicPathByUri(Uri uri, Context context) {
		String path = "";
		Uri imageUri = uri;
		Cursor cursor = context.getContentResolver().query(imageUri, null, null, null, null);
		if (cursor.moveToNext()) {
			path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
			
		}
		return path;
	}
}
