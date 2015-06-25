package com.cn.asynctask.utis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created on2015-6-15 下午8:45:35 ImageTask.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class ImageTask extends AsyncTask<String, Integer, Bitmap> {
	private int max_length;
	private ImageView imageView;
	private ProgressBar progressBar;
	private TextView tv;
	private String IMAGE_PATH = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/test.jpg";

	public ImageTask(ImageView image, TextView tv2, ProgressBar pb) {
		super();
		this.imageView = image;
		this.progressBar = pb;
		this.tv = tv2;

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		progressBar.setVisibility(View.VISIBLE);
		tv.setVisibility(View.VISIBLE);
		super.onPreExecute();
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		// TODO Auto-generated method stub
		String path = params[0];
		Bitmap bitmap = downloadBitmap(path);
		return bitmap;
	}

	private Bitmap downloadBitmap(String path) {
		// TODO Auto-generated method stub
		URL url = null;
		HttpURLConnection connection = null;
		InputStream is = null;
		FileOutputStream outputStream = null;
		Bitmap bitmap = null;
		try {
			url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				max_length = connection.getContentLength();
				progressBar.setMax(max_length);
				File file = new File(IMAGE_PATH);
				outputStream = new FileOutputStream(file);
				byte[] buffer = new byte[64];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					outputStream.write(buffer, 0, len);
					publishProgress(len);
				}
				bitmap = BitmapFactory.decodeFile(IMAGE_PATH);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		tv.setText("下载完成");
		imageView.setImageBitmap(result);
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		int len = progressBar.getProgress() + values[0];
		progressBar.setProgress(len);
		String str = progressBar.getProgress() * 100 / progressBar.getMax()
				+ "%";
		tv.setText("下载完成" + str);
		super.onProgressUpdate(values);
	}
}