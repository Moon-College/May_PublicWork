package com.example.asynctask_download;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

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

	private TextView tv;
	private ProgressBar pb;
	private Button bt;
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv_1);

		pb = (ProgressBar) findViewById(R.id.pb);

		bt = (Button) findViewById(R.id.bt);

		iv = (ImageView) findViewById(R.id.iv);

		bt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		MyTask myTask = new MyTask();
		myTask.execute("http://h.hiphotos.baidu.com/image/pic/item/2934349b033b5bb54eb5df4833d3d539b600bc22.jpg");
	}

	private class MyTask extends AsyncTask<String, Integer, Bitmap> {

		private String fileName;
		private FileOutputStream os;
		private int count;

		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0];
			Bitmap bitmap = null;

			try {
				bitmap = downLoadBitmap(path);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return bitmap;
		}

		private Bitmap downLoadBitmap(String path) throws Exception {
			Bitmap bitmap = null;

			URL u = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) u
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);

			if (connection.getResponseCode() == 200) {
				DecimalFormat df = new DecimalFormat("0.00");
				count = connection.getContentLength();
				pb.setMax(count);
				
				InputStream inputStream = connection.getInputStream();
				// bitmap = BitmapFactory.decodeStream(inputStream, null, null);

				fileName = path.substring(path.lastIndexOf("/"));
				Log.i("sss", fileName);
				os = new FileOutputStream(Environment
						.getExternalStorageDirectory().getAbsolutePath()
						+ "/"
						+ fileName);

				int len = 0;
				byte[] buff = new byte[1024];

				while ((len = inputStream.read(buff)) != -1) {
					os.write(buff, 0, len);
					// 更新进度条
					this.publishProgress(len);
				}
				os.close();
				bitmap = BitmapFactory.decodeFile(Environment
						.getExternalStorageDirectory().getAbsolutePath()
						+ "/"
						+ fileName);
				return bitmap;

			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);

			iv.setImageBitmap(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);

			pb.setProgress(pb.getProgress() + values[0]);
			tv.setText("下载进度："+ 100*(pb.getProgress()+values[0]) /count +"%");
		}

		@Override
		protected void onCancelled(Bitmap result) {
			super.onCancelled(result);
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

	}

}
