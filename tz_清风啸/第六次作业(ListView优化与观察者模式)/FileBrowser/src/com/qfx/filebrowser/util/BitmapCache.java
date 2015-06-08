package com.qfx.filebrowser.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;

public class BitmapCache {

	private static BitmapCache mBitmapCache;
	
	private HashMap<String, SoftReference<Bitmap>> mCache;
	
	private BitmapCache() {
		mCache = new HashMap<String, SoftReference<Bitmap>>();
	}
	
	/**
	 * 单利模式
	 * @return
	 */
	public static BitmapCache getInstance() {
		if (mBitmapCache == null) {
			mBitmapCache = new BitmapCache();
		}
		return mBitmapCache;
	}
	
	public void putBitmapToCache(String key, Bitmap value) {
		SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(value);
		mCache.put(key, softReference);
	}
	
	public Bitmap getBitmapFromCache(String key) {
		if (mCache.get(key) == null) {
			return null;
		}
		return mCache.get(key).get();
	}
	
	
}
