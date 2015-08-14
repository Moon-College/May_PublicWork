package com.tz.michael.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.tz.michael.activity.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 三级缓存的图片下载工具类
 * @author michael
 *
 */
public class CacheImageLoader {

	private static Context mContext;
	
	private DefaultDrawable defaultDrawable=new DefaultDrawable();
	
	/**一级缓存bitmap的最大数量*/
	private static final int MAX_CAPACITY=20;
	
	/**一级缓存集合,强引用*/
	@SuppressWarnings("unchecked")
	private static LinkedHashMap<String, Bitmap> firstCache=new LinkedHashMap(MAX_CAPACITY, 0.75f, true){
		//根据返回值来移除map中最“老”的值--Lru算法
		protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
			if(this.size()>MAX_CAPACITY){
				//加入二级缓存
				secondCache.put((String) eldest.getKey(), new SoftReference<Bitmap>((Bitmap) eldest.getValue()));
				//加入本地缓存（三级缓存）
				addToDiskCache((String) eldest.getKey(),(Bitmap) eldest.getValue());
				return true;//从一级缓存中把对应的bitmap移除
			}
			return false;//不移除
		};
	};
	/**二级缓存集合，软引用*/
	private static ConcurrentHashMap<String, SoftReference<Bitmap>> secondCache=new ConcurrentHashMap<String, SoftReference<Bitmap>>();
	
	/**
	 * 加载图片的方法
	 * @param path 图片的地址
	 * @param img 放图片的ImageView
	 */
	public  void loadImage(String path,ImageView img){
		//从缓存中读取图片
		Bitmap bitmap=getBitmapFromCache(path);
		if(bitmap!=null){
			//已经下载的或有线程正在下载同一个图片的，打上cancle标记，方便异步任务中检查处理
			addCancleTagToTask(path,img);
			img.setImageBitmap(bitmap);
		}else{
			//加载之前，显示默认的图片，这里使用颜色来填充
			img.setImageDrawable(defaultDrawable);
			//从网络上加载图片
			ImageDownloadTask downTask=new ImageDownloadTask(img);
			downTask.execute(path);
		}
	}

	/**
	 * 正在下载的具有相同的key的或者之前现在完成的（未被回收的异步任务），加上cancle标记
	 * @param path
	 * @param img
	 */
	private void addCancleTagToTask(String path, ImageView img) {
		ImageDownloadTask task=new ImageDownloadTask(img);
		if(task!=null){
			String key=task.pathTask;
			if(key==null||!key.equals(path)){
				task.cancel(true);
			}
		}
	}

	private CacheImageLoader(){
		
	}
	
	private CacheImageLoader(Context context) {
		mContext=context;
	}

	
	private static CacheImageLoader instance;
	public static CacheImageLoader getInstance(Context context){
		if(instance==null){
			instance=new CacheImageLoader(context);
		}
		return instance;
	}
	
	/**
	 * 加入本地缓存（三级）
	 * @param key
	 * @param value
	 */
	protected static void addToDiskCache(String key, Bitmap value) {
		String fileName=StringUtils.encodeByMD5(key);
		String filePath=mContext.getCacheDir()+File.separator+fileName;
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(filePath);
			value.compress(Bitmap.CompressFormat.JPEG, 100, os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * 从缓存中拿取图片
	 * @param path
	 * @return
	 */
	private Bitmap getBitmapFromCache(String path) {
		// 从一级缓存中获取
		synchronized (firstCache) {
			Bitmap bitmap=firstCache.get(path);
			if (bitmap!=null) {
				//刷新一下顺序
				firstCache.remove(path);
				firstCache.put(path, bitmap);
				return bitmap;
			}
		}
		//从二级缓存中获取
		SoftReference<Bitmap> sf=secondCache.get(path);
		if(sf!=null){
			Bitmap bitmap=sf.get();
			if(bitmap!=null){
				addToFirstCache(path, bitmap);
				return bitmap;
			}
		}else{
			//软引用被回收掉了
			secondCache.remove(path);
		}
		//从本地获取（三级）
		Bitmap bitmap=getBitmapFromLocal(path);
		if(bitmap!=null){
			addToFirstCache(path, bitmap);
			return bitmap;
		}
		return null;
	}
	
	/**
	 * 从本地获取
	 * @param path
	 * @return
	 */
	private Bitmap getBitmapFromLocal(String path) {
		String fileName=StringUtils.encodeByMD5(path);
		if(fileName==null){
			return null;
		}
		String filePath=mContext.getCacheDir()+File.separator+fileName;
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(filePath);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	class DefaultDrawable extends ColorDrawable{
		public DefaultDrawable() {
			super(Color.GRAY);
		}
	}
	
	/**
	 * 加载图片的异步类
	 * @author admin
	 *
	 */
	class ImageDownloadTask extends AsyncTask<String, Void, Bitmap>{

		private String pathTask;
		private ImageView imageView;
		
		public ImageDownloadTask(ImageView imageView) {
			super();
			this.imageView = imageView;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			pathTask=params[0];
			return getBitmapByPath(pathTask);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if(isCancelled()){//判断如果cancle标记为true，把result置为null
				result=null;
			}
			if(result!=null){//如果cancle标记为false，把bitmap放入一级缓存
				//下载完成后，添加到一级缓存
				addToFirstCache(pathTask,result);
				imageView.setImageBitmap(result);
			}
		}
		
	}

	/**
	 * 根据路径获得对应的输入流
	 * @param path
	 * @return
	 */
	public Bitmap getBitmapByPath(String path) {
		HttpURLConnection conn;
		InputStream in=null;
		try {
			conn = (HttpURLConnection) new URL(path).openConnection();
			in=conn.getInputStream();
			Bitmap bitmap=BitmapUtils.norrowBitmap(in, 800, 350);
			if(bitmap!=null){
				return bitmap;
			}else{
				Bitmap bitmapF=BitmapUtils.norrowBitmap(mContext, R.drawable.mc, 800, 350);
				return bitmapF;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 添加到一级缓存
	 * @param pathTask
	 * @param result
	 */
	public void addToFirstCache(String pathTask, Bitmap result) {
		if(result!=null){
			synchronized (firstCache) {
				firstCache.put(pathTask, result);
			}
		}
	}
	
}
