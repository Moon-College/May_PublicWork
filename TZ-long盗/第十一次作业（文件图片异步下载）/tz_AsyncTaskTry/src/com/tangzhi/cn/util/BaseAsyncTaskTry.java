package com.tangzhi.cn.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BaseAsyncTaskTry extends AsyncTask<String, Integer, File> {
	private String savePath = Environment.getExternalStorageDirectory()
			.getAbsolutePath();
	public Context mContext;
	public ProgressBar progressBar;
	public TextView tv;

	public BaseAsyncTaskTry(Context context, ProgressBar progressBar,
			TextView tv) {
		this.mContext = context;
		this.progressBar = progressBar;
		this.tv = tv;
	}

	@Override
	protected File doInBackground(String... params) {
		File f = null;
		try {
			f = getFileForm(params[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	private File getFileForm(String string) throws Exception {
		URL url = new URL(string);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		if (conn.getResponseCode() == 200) {
			File file = new File(savePath + File.separator
					+ string.substring(string.lastIndexOf("/")));
			// 设置进度条
			if (progressBar != null) {
				progressBar.setMax(conn.getContentLength());
			}
			InputStream in = conn.getInputStream();
			FileOutputStream fo = new FileOutputStream(file);
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = in.read(buffer)) != -1) {
				fo.write(buffer, 0, len);
				if (progressBar != null) {
					publishProgress(len);
				}
				fo.flush();
			}
			in.close();
			fo.close();
			return file;
		} else {
			return null;
		}

	}
	@Override
	protected void onPreExecute() {
		
	}
	/**
	 * 子类可以重写此方法,来实现自己的特有操作
	 */
	@Override
	protected void onPostExecute(File result) {
	}
	@Override
	protected void onProgressUpdate(Integer... values) {
		if (progressBar!=null) {
			progressBar.setProgress(progressBar.getProgress()+values[0]);
			tv.setText(100*progressBar.getProgress()/progressBar.getMax()+"%");
		}
		super.onProgressUpdate(values);
	}

}
