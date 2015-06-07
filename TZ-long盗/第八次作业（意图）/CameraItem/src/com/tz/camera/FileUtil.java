package com.tz.camera;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FileUtil {
	
	
	/**
	 * 按图片比例大小压缩图片的方法
	 * @param path 路径
	 * @return
	 */
	public static Bitmap getIamge(String path){
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(path, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//现在主流手机的是800*400的分辨率，所以我们设置为，
//		float zw = 800f;
//		float zh = 400f;
		//但是由于运行在虚拟机上,虚拟机内存较小。防止报内存溢出，只能设置
		float zw = 100f;
		float zh = 50f;
		//缩放比
		int be  =1 ;//表示缩放
 		if (w > h && w > zw) {//如果宽度大的话根据宽度固定大小缩放 
			be = (int) (newOpts.outWidth/zw);
		} else if(w<h && h>zh){//如果高度高的话根据宽度固定大小缩放  
			be = (int) (newOpts.outHeight/zh);
		}
		if (be<=0) {
			be = 1;
		}
		newOpts.inSampleSize = be;
		bitmap = BitmapFactory.decodeFile(path, newOpts);	
		return compressImg(bitmap);
		
	}
	/**
	 * 质量压缩方法
	 * @param b 要压缩的bitmap
	 * @return
	 */
	public static Bitmap compressImg(Bitmap b){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		b.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
		int options = 100;
		while (baos.toByteArray().length/1024>100) {//循环判断如果压缩后图片是否大于100kb,大于继续压缩         
			baos.reset();
			b.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中  
			options -=10;//每次都减少10
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中  
		Bitmap bitmap = BitmapFactory.decodeStream(bis,null,null);//把ByteArrayInputStream数据生成图片  
		return bitmap;
	}
	
	

}
