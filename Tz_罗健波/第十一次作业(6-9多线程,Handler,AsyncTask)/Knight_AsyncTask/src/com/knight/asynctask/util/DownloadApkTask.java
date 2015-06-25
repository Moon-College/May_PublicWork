package com.knight.asynctask.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownloadApkTask extends AsyncTask<String, Integer, String>{

	private ProgressBar progressApk;
	private TextView textView;
	private int maxLength;
	private int count = 0;
	private String sdRoot = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Myapk";
	private Context context;
	
	public DownloadApkTask(Context context,TextView textView,ProgressBar progressBar){
		this.context = context;
		this.textView = textView;
		this.progressApk =progressBar;
	}
	
	@Override
	protected String doInBackground(String... params) {
		String urlApk = params[0];
		
		return loaderApk(urlApk);
	}

	private String loaderApk(String urlApk) {
		URL url = null;
		HttpURLConnection conn = null;
		InputStream inStream = null;
		FileOutputStream os = null;
		try {
			url = new URL(urlApk);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10*1000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Charset", "UTF-8");
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				maxLength = conn.getContentLength();
				progressApk.setMax(maxLength);
				inStream = conn.getInputStream();
				String path = sdRoot+urlApk.substring(urlApk.lastIndexOf("/"));
				File file = new File(sdRoot);
				if (!file.exists()) {
					file.mkdirs();
				}
				os = new FileOutputStream(path);
				int len = 0;
				byte[] buffer = null;
				if (maxLength > 5*1024*1024) {
					buffer = new byte[10*1024];
				}else {
					buffer = new byte[5*1024];
				}
				while ((len = inStream.read(buffer)) != -1) {
					os.write(buffer, 0, len);
					count += len;
					publishProgress(count);
				}
				return path;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		openApk(result);
	}
	
	private void openApk(String result) {
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(result)), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		int prog = values[0];
		progressApk.setProgress(prog);
		textView.setText("当前进度:"+(100*progressApk.getProgress()/maxLength)+"%");
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

}
