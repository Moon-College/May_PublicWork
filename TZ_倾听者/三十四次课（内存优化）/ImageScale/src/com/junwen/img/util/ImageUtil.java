package com.junwen.img.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class ImageUtil {

	/**
	 * ����ָ���ÿ�ߣ�����һ��Bitmap
	 * @param pathName ͼƬ��·��
	 * @param newWidht ���
	 * @param newHeight �߶�
	 * @return
	 */
	public static Bitmap getBitamp(String pathName,int newWidht,int newHeight){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		int outWidth = options.outWidth;
		int outHeight = options.outHeight;
		if(outWidth > newWidht && outHeight > newHeight){
			options.inSampleSize = (outWidth / newWidht + outHeight / newHeight) /2 ;
			options.inJustDecodeBounds = false;
		}
		Bitmap bitmap = BitmapFactory.decodeFile(pathName,options);
		return bitmap;
	}
	
	/**
	 * ����ָ���ÿ�ߣ�����һ���µ�Bitmap
	 * @param bitmap
	 * @param w ���
	 * @param h �߶�
	 * @return
	 */
    public static Bitmap getBitmap(Bitmap bitmap, int w, int h)   
    {    
        Bitmap BitmapOrg = bitmap;    
        int width = BitmapOrg.getWidth();    
        int height = BitmapOrg.getHeight();    
        int newWidth = w;    
        int newHeight = h;    
  
        float scaleWidth = ((float) newWidth) / width;    
        float scaleHeight = ((float) newHeight) / height;    
  
        Matrix matrix = new Matrix();    
        matrix.postScale(scaleWidth, scaleHeight);    
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,    
                        height, matrix, true);    
        return resizedBitmap;    
    }  
}
