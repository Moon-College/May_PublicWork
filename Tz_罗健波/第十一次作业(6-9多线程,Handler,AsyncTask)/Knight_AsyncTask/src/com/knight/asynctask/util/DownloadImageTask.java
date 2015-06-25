package com.knight.asynctask.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownloadImageTask extends AsyncTask<String, Integer, Bitmap>{

	private String sdKeyRoot = Environment.getExternalStorageDirectory().getAbsolutePath()+"/image";
	private TextView tView ;
	private ProgressBar progressImg;
	private Context context;
	private ImageView imageView;
	private int maxLength;
	int count = 0 ;
	public DownloadImageTask(Context context,TextView tView,ProgressBar progressBar,ImageView imageView){
		this.context = context;
		this.tView = tView;
		this.progressImg = progressBar;
		this.imageView = imageView;
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		String imgUrl = params[0];
		
		return loadImage(imgUrl);
	}

	private Bitmap loadImage(String imgUrl) {
		Bitmap bitmap = null;
		URL url = null;
		HttpURLConnection connection = null;
		InputStream inStream = null;
		FileOutputStream os = null;
		try {
			//耗时网络连接
			url = new URL(imgUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Charset", "UTF-8");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				maxLength = connection.getContentLength();
				progressImg.setMax(maxLength);
				inStream = connection.getInputStream();
				String path = sdKeyRoot+File.separator+imgUrl.substring(imgUrl.lastIndexOf("/"));
				File file = new File(sdKeyRoot);
				if (!file.exists()) {
					file.mkdirs();
				}
				os = new FileOutputStream(path);
				int len = 0;
				byte[] buffer = new byte[1024];
				//耗时流操作
				while ((len = inStream.read(buffer)) != -1) {
					os.write(buffer, 0, len);
					count += len;
					publishProgress(count);
				}
				return BitmapFactory.decodeFile(path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (connection != null) {
				try {
					connection.connect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bitmap;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		imageView.setImageBitmap(result);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		int count = values[0];
		progressImg.setProgress(count);
		tView.setText("当前进度:"+(100*progressImg.getProgress()/maxLength)+"%");
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

}
