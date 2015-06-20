package com.example.tz_download_asyn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class DownloadImageActivity extends Activity {

	private Button btn_download_image;

	private ProgressBar progressBar;

	private ImageView image;
	
	private  final String SD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();

	private int fileLen;
	private static final String imageUrl = "http://pic.nipic.com/2008-05-23/2008523131945106_2.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_image);
		btn_download_image = (Button) findViewById(R.id.btn_download_image);
		image = (ImageView) findViewById(R.id.image);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		btn_download_image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AsyncDownloadImage asyn = new AsyncDownloadImage();
				asyn.execute(imageUrl);
			}
		});
	}

	private class AsyncDownloadImage extends AsyncTask<String, Integer, Bitmap> {

		//在后台加载 
		@Override
		protected Bitmap doInBackground(String... params) {
			return downloadMeiNv(params[0]);
		}
		
		private Bitmap downloadMeiNv(String url) {
			URL u;
			Bitmap bitmap;
			try {
				
				u = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) u.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5000);
				if(conn.getResponseCode() == 200) {
					//获取文件的长度
					fileLen = conn.getContentLength();
					progressBar.setMax(fileLen);
					InputStream is = conn.getInputStream();
					//先写到sd卡中
					//获取文件名
					String fileName = url.substring(url.lastIndexOf("/"));
					FileOutputStream os = new FileOutputStream(SD_ROOT  + "/" + fileName);
					int len = 0;
					byte [] buffer = new byte[512]; //内存中缓存
					while((len = is.read(buffer)) != -1) {
						os.write(buffer, 0, len);
						//每读写一次，表示文件下载了len长度
						this.publishProgress(len);
					}
					os.close();
					bitmap = BitmapFactory.decodeFile(SD_ROOT  + "/" + fileName);
					return bitmap;
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			Log.e("wdj","result = " + result);
			if (result != null) {
				image.setImageBitmap(result);
			}
		}

		
		@Override
		protected void onProgressUpdate(Integer... values) {
			Log.e("wdj", "values = " + values[0]);
			progressBar.setProgress(progressBar.getProgress() + values[0]);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

	}
}
