package com.tz.filebrowser.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class LoadBitmapTask extends AsyncTask<Void, Void, Bitmap> {
	private int limitWidth = 300;
	private int limitHeight = 300;
	private String path;
	private Handler handler;

	public LoadBitmapTask(String path, Handler handler, int limitWidth,
			int limitHeight) {
		this.path = path;
		this.limitWidth = limitWidth;
		this.limitHeight = limitHeight;
		this.handler = handler;
	}

	@Override
	protected Bitmap doInBackground(Void... params) {
		return decodeBitmap();
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		Message msg = handler.obtainMessage();
		msg.obj = result;
		handler.sendMessage(msg);
	}

	private Bitmap decodeBitmap() {
		BitmapFactory.Options options = new Options();

		// 获取图片的尺寸
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		int bitmapWidth = options.outWidth;
		int bitmapHeight = options.outHeight;

		// 计算缩放比例
		int widthScale = bitmapWidth / limitWidth;
		int heightScale = bitmapHeight / limitHeight;
		options.inJustDecodeBounds = false;
		options.inSampleSize = Math.min(widthScale, heightScale);
		return BitmapFactory.decodeFile(path, options);
	}
}
