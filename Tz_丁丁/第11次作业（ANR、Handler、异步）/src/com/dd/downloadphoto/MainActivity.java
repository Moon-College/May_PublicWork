package com.dd.downloadphoto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView textView1;
	private ProgressBar progressBar1;
	private Button button1,button2;
	private ImageView imageView1;
	private int max,apkMax;
	private String photoUrl = "http://img6.faloo.com/Picture/0x0/0/464/464742.jpg";
	private String apkUrl = "http://www.apk3.com/uploads/soft/201504/ftt2pzev0cg.apk";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Reflection.findView(this);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				download d = new download();
				d.execute(photoUrl);
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				downloadApk d = new downloadApk();
				d.execute(apkUrl);
			}
		});
	}
	class downloadApk extends AsyncTask<String, Integer, String>{


		@Override
		protected void onProgressUpdate(Integer... values) {
			textView1.setText("已下载"+values[0]+"%");
			super.onProgressUpdate(values);
		}

		@Override
		protected String doInBackground(String... params) {
			String down = down(params[0]);
			return down;
		}

		private String down(String string) {
			String path = Environment.getExternalStorageDirectory().getAbsolutePath()+string.substring(string.lastIndexOf("/"));
			URL url;
			try {
				url = new URL(string);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(5000);
				connection.setRequestMethod("GET");
				apkMax = connection.getContentLength();
				progressBar1.setMax(apkMax);
				if (connection.getResponseCode()==200) {
					InputStream is = connection.getInputStream();
					FileOutputStream fos = new FileOutputStream(path);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
						fos.write(buffer, 0, len);
						progressBar1.setProgress(progressBar1.getProgress() +len);
						int i = 100*progressBar1.getProgress()/apkMax;
						publishProgress(i);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return path;
		}

		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null) {
				textView1.setText("下载完成");
				Toast.makeText(MainActivity.this, "安装", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(Intent.ACTION_VIEW); 
				intent.setDataAndType(Uri.fromFile(new File(result)), "application/vnd.android.package-archive"); 
				startActivity(intent);
//				imageView1.setImageBitmap(result);
			}
		}
	}
	class download extends AsyncTask<String, Integer, Bitmap>{
		
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			textView1.setText("已下载"+values[0]+"%");
			super.onProgressUpdate(values);
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap down = down(params[0]);
			return down;
		}
		
		private Bitmap down(String string) {
			String path = Environment.getExternalStorageDirectory().getAbsolutePath()+string.substring(string.lastIndexOf("/"));
			URL url;
			try {
				url = new URL(string);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(5000);
				connection.setRequestMethod("GET");
				max = connection.getContentLength();
				progressBar1.setMax(max);
				if (connection.getResponseCode()==200) {
					InputStream is = connection.getInputStream();
					FileOutputStream fos = new FileOutputStream(path);
					byte[] buffer = new byte[1];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
						fos.write(buffer, 0, len);
						progressBar1.setProgress(progressBar1.getProgress() +len);
						int i = 100*progressBar1.getProgress()/max;
						publishProgress(i);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Bitmap bitmap = BitmapFactory.decodeFile(path);
			return bitmap;
		}
		
		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if (result != null) {
				imageView1.setImageBitmap(result);
				textView1.setText("下载完成");
			}
		}
	}
}
