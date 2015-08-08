package com.tz.michael.utils;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;

public class BitmapUtils {

	/**
	 * 根据路径来拿到一个指定宽高的bitmap
	 * @param path 图片所在文件的路径
	 * @param width  目标bitmap的宽
	 * @param height  目标bitmap的高
	 * @return 缩小后的bitmap
	 */
	public static Bitmap norrowBitmap(String path,int width,int height){
		BitmapFactory.Options opts=new Options();
		opts.inJustDecodeBounds=true;
		BitmapFactory.decodeFile(path, opts);
		int originalWidth=opts.outWidth;
		int originalHeight=opts.outHeight;
		float ratio=Math.max((float)originalWidth/width, (float)originalHeight/height);
		if(ratio<1){
			ratio=1;
		}
		opts.inSampleSize=(int) ratio;
		opts.inJustDecodeBounds=false;
		return BitmapFactory.decodeFile(path, opts);
	}
	
	/**
	 * 根据流来拿到一个指定宽高的bitmap
	 * @param in 包含图片的输入流
	 * @param width 目标bitmap的宽
	 * @param height 目标bitmap的高
	 * @return 缩小后的bitmap
	 */
	public static Bitmap norrowBitmap(InputStream in,int width,int height){
		BitmapFactory.Options opts=new Options();
		opts.inJustDecodeBounds=true;
		BitmapFactory.decodeStream(in, null, opts);
		int originalWidth=opts.outWidth;
		int originalHeight=opts.outHeight;
		float ratio=Math.max((float)originalWidth/width, (float)originalHeight/height);
		if(ratio<1){
			ratio=1;
		}
		opts.inSampleSize=(int) ratio;
		opts.inJustDecodeBounds=false;
		opts.inScaled=true;
		return BitmapFactory.decodeStream(in, null, opts);
	}
	
	/**
	 * 根据资源文件的id来拿到一个指定宽高的bitmap
	 * @param context 上下文
	 * @param id 对应图片文件的id
	 * @param width 目标bitmap的宽
	 * @param height 目标bitmap的高
	 * @return 缩小后的bitmap
	 */
	public static Bitmap norrowBitmap(Context context,int id,int width,int height){
		BitmapFactory.Options opts=new Options();
		opts.inJustDecodeBounds=true;
		BitmapFactory.decodeResource(context.getResources(),id, opts);
		int originalWidth=opts.outWidth;
		int originalHeight=opts.outHeight;
		float ratio=Math.max((float)originalWidth/width, (float)originalHeight/height);
		if(ratio<1){
			ratio=1;
		}
		opts.inSampleSize=(int) ratio;
		opts.inJustDecodeBounds=false;
		return BitmapFactory.decodeResource(context.getResources(),id, opts);
	}
	
}
