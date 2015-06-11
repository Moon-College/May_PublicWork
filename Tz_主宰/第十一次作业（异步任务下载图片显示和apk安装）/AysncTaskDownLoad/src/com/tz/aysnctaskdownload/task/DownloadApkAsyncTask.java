package com.tz.aysnctaskdownload.task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.tz.aysnctaskdownload.MainActivity;
import com.tz.aysnctaskdownload.task.listener.DownloadListener;

public class DownloadApkAsyncTask extends AsyncTask<String, Integer, String> {
	private final int MSG_WHAT_MAXSIZE = 1;
	private DownloadListener<String> listener;
	private int currDownloadSize;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == MSG_WHAT_MAXSIZE) {
				if (listener != null) {
					listener.startLoading((Integer) msg.obj);
				}
			}
		};
	};

	public DownloadApkAsyncTask(DownloadListener<String> listener) {
		super();
		this.listener = listener;
	}

	@Override
	protected String doInBackground(String... params) {
		// ��ȡ����url
		String apkUrl = params[0];
		String result = downloadApkFromNet(apkUrl);
		return result;
	}

	// �������϶�ȡapk
	private String downloadApkFromNet(String pictureUrl) {
		Bitmap bitmap = null;
		try {
			URL imageUrl = new URL(pictureUrl);
			HttpURLConnection httpGet = (HttpURLConnection) imageUrl
					.openConnection();
			httpGet.setConnectTimeout(60 * 1000);
			httpGet.setRequestMethod("GET");
			if (httpGet.getResponseCode() == HttpURLConnection.HTTP_OK) {

				// ��ȡ�ļ���С
				int maxSize = httpGet.getContentLength();
				Message msg = handler.obtainMessage(MSG_WHAT_MAXSIZE, maxSize);
				msg.sendToTarget();

				String filePath = MainActivity.ROOT_PATH + "/APK_" + System.currentTimeMillis() + ".apk";
				Log.i("FILE PATH", filePath);
				FileOutputStream fos = new FileOutputStream(filePath);
				InputStream in = httpGet.getInputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					fos.write(buffer);
					publishProgress(len);
				}
				fos.close();
				return filePath;
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
		currDownloadSize += values[0];
		if (listener != null) {
			listener.loading(currDownloadSize);
		}
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(String result) {
		if (listener != null) {
			listener.success(result);
		}
		super.onPostExecute(result);
	}

	@Override
	protected void onCancelled() {
		if (listener != null) {
			listener.cancel();
		}
		super.onCancelled();
	}
}
