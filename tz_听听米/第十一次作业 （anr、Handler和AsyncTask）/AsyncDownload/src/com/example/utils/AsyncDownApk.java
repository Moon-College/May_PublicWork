package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncDownApk extends AsyncTask<String, Integer, File> {
	
	private int maxLength;
	private ProgressBar progress;
	private TextView tv_progress;
	private boolean cancel;
	private Context context;
	
	private AsyncDownApk(){}
	
	

	public AsyncDownApk(Context context,ProgressBar progress, TextView tv_progress) {
		super();
		this.progress = progress;
		this.tv_progress = tv_progress;
		this.context = context;
	}



	@Override
	protected File doInBackground(String... params) {
		String url = params[0];
		return download(url);
	}

	public File download(String url){
		HttpURLConnection conn = null;
		InputStream is = null;
		FileOutputStream fos = null;
		File file = null;
		try{
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				maxLength = conn.getContentLength();
				progress.setMax(maxLength);
				is = conn.getInputStream();
				String path = Constants.ROOT_PATH +File.separator+ url.substring(url.lastIndexOf("/")+1);
				file = new File(path);
				fos = new FileOutputStream(file);
				byte []buffer = new byte[1024];
				int len;
				while((len=is.read(buffer))!=-1){
					fos.write(buffer, 0, len);
					publishProgress(len);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != conn){
				conn.disconnect();
			}
			if(null != fos){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != is){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	/**
	 * 打开APK
	 *@author 邹继明
	 *@date 2015-6-11上午12:45:28
	 *@param file
	 *@return void
	 */
	private void openFile(File file) {
        // TODO Auto-generated method stub
        Log.e("OpenFile", file.getName());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                        "application/vnd.android.package-archive");
        context.startActivity(intent);
}

	@Override
	protected void onPostExecute(File result) {
		if(null != result){
			openFile(result);
		}
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		int len = progress.getProgress()+values[0];
		progress.setProgress(len);
		String per = progress.getProgress()*100/progress.getMax() +"%";
		tv_progress.setText("下载进度："+per);
	}

	@Override
	protected void onCancelled(File result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}
	
	

}
