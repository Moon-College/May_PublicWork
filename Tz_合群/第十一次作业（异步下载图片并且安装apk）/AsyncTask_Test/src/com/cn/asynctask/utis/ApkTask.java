package com.cn.asynctask.utis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created on2015-6-15 下午8:51:00 ApkTask.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class ApkTask extends AsyncTask<String, Integer, File> {
	private int max_length;
	private ProgressBar progressBar;
	private TextView tv_img;
	private Context context;
	private String APKName;
	public ApkTask(ProgressBar progressBar, TextView tv1,
			Context context) {
		super();
		this.progressBar = progressBar;
		this.tv_img = tv1;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		tv_img.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.VISIBLE);
		super.onPreExecute();
	}

	@Override
	protected File doInBackground(String... params) {
		// TODO Auto-generated method stub
		String path = params[0];
		File file = downloadAPKFile(path);
		return file;
	}

	private File downloadAPKFile(String path) {
		// TODO Auto-generated method stub
		HttpURLConnection conn = null;
		InputStream is = null;
		FileOutputStream fos = null;
		File file = null;
		URL url = null;
		try {
			url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				max_length = conn.getContentLength();
				progressBar.setMax(max_length);
				APKName = path.substring(path.lastIndexOf("/") + 1);
				String file_path = Environment.getExternalStorageDirectory()
						.getAbsolutePath()
						+ "/"
						+ APKName;
				file = new File(file_path);
				fos = new FileOutputStream(file);
				byte[] buffer = new byte[4096];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					publishProgress(len);
				}

			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
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
		return file;
	}
	/**
	 * 打开APK的方法
	 * @param file
	 */
	private void openAPKFile(File file) {
		Intent intent = new Intent();
		// 打开新任务标签
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	@Override
	protected void onPostExecute(File result) {
		// TODO Auto-generated method stub
		if(result!=null){
			openAPKFile(result);
		}
		tv_img.setText("下载APK完成");
		super.onPostExecute(result);
	}
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		int len = progressBar.getProgress()+values[0];
		progressBar.setProgress(len);
		String str = progressBar.getProgress()*100/progressBar.getMax() +"%";
		tv_img.setText(APKName+"下载进度："+str);
		super.onProgressUpdate(values);
	}
}
