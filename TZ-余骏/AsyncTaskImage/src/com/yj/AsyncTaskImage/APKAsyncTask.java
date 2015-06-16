package com.yj.AsyncTaskImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class APKAsyncTask extends AsyncTask<String, Integer, Boolean>{
	
	private ProgressBar progressBar;
	private int total_length;
	private TextView textView;
	private int file_length;
	private Activity activity;
	private String path;
	
	
	public APKAsyncTask(ProgressBar progressBar,TextView textView,Activity activity) {
		this.progressBar = progressBar;
		this.textView = textView;
		this.activity = activity;
	}
		

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		progressBar.setProgress(progressBar.getProgress() + values[0]);
		textView.setText(String.valueOf((int)(total_length*100/(float)file_length))+"%");
	}


	@Override
	protected Boolean doInBackground(String... params) {
		URL url;
		boolean result = false;
		path = params[0];
		try {
			url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() == 200){
			    InputStream is = conn.getInputStream();	
			    File file = new File(Environment.getExternalStorageDirectory(), path.substring(path.lastIndexOf("/") + 1));
			    FileOutputStream fos = new FileOutputStream(file);
			    byte[] buffer = new byte[1024];
			    int len = 0;
			    total_length = 0;
			    file_length = conn.getContentLength();
			    progressBar.setMax(file_length);
			    while((len = is.read(buffer)) != -1){
			    	fos.write(buffer, 0, len);
			    	total_length += len;
			    	publishProgress(len);
			    }
			    is.close();
			    fos.close();
			    result = true;
			} 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}


	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result) {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			String name = path.substring(path.lastIndexOf("/") + 1);
			File file = new File(Environment.getExternalStorageDirectory(),name);
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
			activity.startActivity(intent);
		}else{ 
			//下载失败
			Log.e("error", "下载失败");
		}
		
	}
	
	
}
