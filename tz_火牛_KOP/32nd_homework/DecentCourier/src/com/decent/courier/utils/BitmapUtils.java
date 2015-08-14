package com.decent.courier.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

public class BitmapUtils {
	/**
	 * 通过图片uri进行缩放
	 * @param uri
	 * @param context
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Bitmap getScaleBitmapByUri(Uri uri,Context context) throws FileNotFoundException{
		Bitmap bitmap;
		int w;
		InputStream is = context.getContentResolver().openInputStream(uri);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeStream(is,null,options);
		w = options.outWidth;
		if(w>DecentConstants.BITMAP_STANDRD_WIDTH){
			options.inSampleSize = w/DecentConstants.BITMAP_STANDRD_WIDTH;
			options.inJustDecodeBounds = false;
			String path = getPicPathByUri(uri, context);
			bitmap = BitmapFactory.decodeFile(path, options);
		}else{
			bitmap = BitmapFactory.decodeStream(is);
		}
		return bitmap;
	}
	
	/**
	 * 通过图片路径进行图片缩放
	 * @param path
	 * @param context
	 * @return
	 */
	public static Bitmap getScaleBitmapByPath(String path,Context context){
		Bitmap bitmap;
		int w;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeFile(path, options);
		w = options.outWidth;
		if(w>DecentConstants.BITMAP_STANDRD_WIDTH){
			options.inSampleSize = w/DecentConstants.BITMAP_STANDRD_WIDTH;
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(path, options);
		}else{
			bitmap = BitmapFactory.decodeFile(path);
		}
		return bitmap;
	}
	
	/**
	 * 通过uri获取图片的路�?
	 * @param uri
	 * @param context
	 * @return
	 */
	public static String getPicPathByUri(Uri uri,Context context){
		String path = "";
		Uri imageUri = uri;
		Cursor cursor = context.getContentResolver().query(imageUri, null, null, null, null);
		if(cursor.moveToNext()){
			path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
		}
		return path;
	}
}
