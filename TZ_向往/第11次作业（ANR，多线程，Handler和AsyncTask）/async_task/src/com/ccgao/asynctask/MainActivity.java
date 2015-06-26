package com.ccgao.asynctask;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class MainActivity extends Activity implements OnClickListener {
	private Button btn;
	private ProgressBar prb;
	private ImageView img;
	private TextView tv;
	private final String SDK_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		btn = (Button) findViewById(R.id.button1);
		prb = (ProgressBar) findViewById(R.id.progressBar1);
		img = (ImageView) findViewById(R.id.img);
		tv = (TextView) findViewById(R.id.tv);
		btn.setOnClickListener(this);
	}

	public void onClick(View v) {
		// 后台线程去下载图片
		String img_url = "http://h.hiphotos.baidu.com/image/pic/item/34fae6cd7b899e51809a5b0b40a7d933c8950d9c.jpg";
		// String img_url = "http://www.baidu.com";
		MyAsyncTask myAsyncTask = new MyAsyncTask();
		myAsyncTask.execute(img_url);
	}

	private class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {
		private int max;

		// 后台加载，实现原理是Thread
		@Override
		protected Bitmap doInBackground(String... params) {
			// 利用url下载图片，边下载边更新进度条
			String url = params[0];
			Bitmap bitmap = null;
			Log.i("INFO", url);
			try {
				bitmap = DownloadBitmap(url);
				if (bitmap == null) {
					Log.i("INFO", "doInBackground bitmap is null");
				}
				;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.i("INFO", "ERROR:" + e.getMessage());
				e.printStackTrace();
			}
			return bitmap;
		}

		/**
		 * 从网络上下载图片
		 * 
		 * @param url
		 *            图片地址
		 * @throws IOException
		 */
		private Bitmap DownloadBitmap(String url) throws IOException {
			URL url2 = new URL(url);
			Log.i("INFO", "DownloadBitmapURL:" + url);
			Bitmap bitmap = null;
			HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection(); // 开远程连接
			urlConnection.setRequestMethod("GET"); // 设置方式
			urlConnection.setConnectTimeout(5000); // 设置5秒超时
			// 文件分割的形式
			// urlConnection.setRequestProperty("Content-Length", "2048");
			// RandomAccessFile();
			// urlConnection.connect();
			if (urlConnection.getResponseCode() == 200) {
				max = urlConnection.getContentLength();
				// 设置进度条
				prb.setMax(max);
				// 请求成功
				InputStream inputStream = urlConnection.getInputStream();
				// bitmap = BitmapFactory.decodeStream(inputStream); //此种方式不知道下载进度
				// 边下载边下载到SDK卡里
				// 先截取网络图片文件名
				String filename = url.substring(url.lastIndexOf("/"));
				FileOutputStream fos = new FileOutputStream(SDK_ROOT + "/" + filename);
				int Len = 0;
				byte[] buffer = new byte[1024];
				while ((Len = inputStream.read(buffer)) != -1) {
					// 每次读1024字节写入文件
					fos.write(buffer, 0, Len);
					// 更新进度条
					this.publishProgress(Len);
				}
				;
				// 文件下载完载入bitmap，并关闭释放fos文件流
				fos.close();
				bitmap = BitmapFactory.decodeFile(SDK_ROOT + "/" + filename);

				return bitmap;
			}
			return null;
		}

		// 预加载
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		// 加载完回调该函数，实现原理是Handler
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			// 更新图片UI
			img.setImageBitmap(result);
		}

		// 更新进度条UI，实现原理是Handler
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			prb.setProgress(prb.getProgress() + values[0]);
			String text = "下载进度：" + 100 * prb.getProgress() / max+"%";
			tv.setText(text);
		}

		// 取消任务
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

	}
}