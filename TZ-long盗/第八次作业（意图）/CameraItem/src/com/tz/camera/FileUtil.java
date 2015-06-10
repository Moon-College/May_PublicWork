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
	 * ��ͼƬ������Сѹ��ͼƬ�ķ���
	 * @param path ·��
	 * @return
	 */
	public static Bitmap getIamge(String path){
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(path, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//���������ֻ�����800*400�ķֱ��ʣ�������������Ϊ��
//		float zw = 800f;
//		float zh = 400f;
		//���������������������,������ڴ��С����ֹ���ڴ������ֻ������
		float zw = 100f;
		float zh = 50f;
		//���ű�
		int be  =1 ;//��ʾ����
 		if (w > h && w > zw) {//�����ȴ�Ļ����ݿ�ȹ̶���С���� 
			be = (int) (newOpts.outWidth/zw);
		} else if(w<h && h>zh){//����߶ȸߵĻ����ݿ�ȹ̶���С����  
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
	 * ����ѹ������
	 * @param b Ҫѹ����bitmap
	 * @return
	 */
	public static Bitmap compressImg(Bitmap b){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		b.compress(Bitmap.CompressFormat.JPEG, 100, baos);//����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�baos��  
		int options = 100;
		while (baos.toByteArray().length/1024>100) {//ѭ���ж����ѹ����ͼƬ�Ƿ����100kb,���ڼ���ѹ��         
			baos.reset();
			b.compress(Bitmap.CompressFormat.JPEG, options, baos);//����ѹ��options%����ѹ��������ݴ�ŵ�baos��  
			options -=10;//ÿ�ζ�����10
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());//��ѹ���������baos��ŵ�ByteArrayInputStream��  
		Bitmap bitmap = BitmapFactory.decodeStream(bis,null,null);//��ByteArrayInputStream��������ͼƬ  
		return bitmap;
	}
	
	

}
