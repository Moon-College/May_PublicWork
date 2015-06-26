package com.example.tz_download_asyn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class DownloadAPKActivity extends Activity {
	// 车轮考驾照
	private static final String apkUrl = "http://shouji.360tpcdn.com/150430/f609c463cc35fd450bebe8b26543ee0d/cn.eclicks.drivingtest_61.apk";

	private Button btn_apk;

	private ProgressBar bar;

	private final String SD_ROOT = Environment.getExternalStorageDirectory()
			.getAbsolutePath();

	private int fileLen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apk);
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		btn_apk = (Button) findViewById(R.id.btn_apk);
		bar = (ProgressBar) findViewById(R.id.bar);

		btn_apk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AsyncDownloadApk apk = new AsyncDownloadApk();
				apk.execute(apkUrl);
			}
		});
	}

	private class AsyncDownloadApk extends AsyncTask<String, Integer, Void> {

		// 在后台加载
		@Override
		protected Void doInBackground(String... params) {
			return downloadAPK(params[0]);
		}

		private Void downloadAPK(String url) {
			URL u;
			Bitmap bitmap;
			try {

				u = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) u.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5000);
				if (conn.getResponseCode() == 200) {
					// 获取文件的长度
					fileLen = conn.getContentLength();
					bar.setMax(fileLen);
					InputStream is = conn.getInputStream();
					// 先写到sd卡中
					// 获取文件名
					String fileName = url.substring(url.lastIndexOf("/"));
					FileOutputStream os = new FileOutputStream(SD_ROOT + "/"
							+ fileName);
					int len = 0;
					byte[] buffer = new byte[1024]; // 内存中缓存
					while ((len = is.read(buffer)) != -1) {
						os.write(buffer, 0, len);
						// 每读写一次，表示文件下载了len长度
						this.publishProgress(len);
					}
					os.close();
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
		protected void onProgressUpdate(Integer... values) {
			Log.e("wdj", "values = " + values[0]);
			bar.setProgress(bar.getProgress() + values[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// 表明现在下载apk到了sd卡了，怎么办呢？
			String fileName = apkUrl.substring(apkUrl.lastIndexOf("/"));
			String filePath = SD_ROOT + "/" + fileName;
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(new File(filePath)),
					"application/vnd.android.package-archive");
			startActivity(intent);
		}

	}

}
