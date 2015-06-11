package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncDownBitmap extends AsyncTask<String, Integer, Bitmap> {
	
	public static final String TAG = AsyncDownBitmap.class.getSimpleName().toString();

	private int maxLength;
	private ImageView iv;
	private ProgressBar progress;
	private TextView tv_progress;
	private boolean cancel;
	
	public AsyncDownBitmap(){
		
	}
	public AsyncDownBitmap(ImageView iv,ProgressBar progress,TextView tv_progress){
		this.iv = iv;
		this.progress = progress;
		this.tv_progress = tv_progress;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		String url = params[0];
		
		return download(url);
	}

	public Bitmap download(String url) {
		URL  u = null;
		HttpURLConnection conn  = null;
		InputStream is = null;
		FileOutputStream fos = null;
		Bitmap bitmap = null;
		try{
			u= new URL(url);
			conn = (HttpURLConnection) u.openConnection(); 
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				is = conn.getInputStream();
				maxLength = conn.getContentLength();
				progress.setMax(maxLength);
				String fileName = "test.JPG";
				String path = Constants.ROOT_PATH + File.separator +fileName;
				fos = new FileOutputStream(path);
				byte[] buffer = new byte[10];
				int len = 0;
				while((len = is.read(buffer))!=-1){
					fos.write(buffer, 0, len);
					publishProgress(len);
				}
				bitmap = BitmapFactory.decodeFile(path);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			Log.d(TAG, e.getMessage());
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return bitmap;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		iv.setImageBitmap(result);
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
	protected void onCancelled(Bitmap result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}
	
	

}
