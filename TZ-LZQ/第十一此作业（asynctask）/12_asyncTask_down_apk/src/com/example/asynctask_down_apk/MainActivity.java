package com.example.asynctask_down_apk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private int maxLength = 0;
	private TextView tv;
	private ProgressBar pb;
	private String apk_sd_path = Environment.getExternalStorageDirectory().getAbsolutePath();
	
	private String maxText = "";
	private String currentText = "";
	private DecimalFormat df;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv);

		pb = (ProgressBar) findViewById(R.id.pb);

		Button bt = (Button) findViewById(R.id.bt);

		bt.setOnClickListener(this);
		
		df = new DecimalFormat("0.00");
	}

	@Override
	public void onClick(View v) {
		String path = "http://www.zijiacn.com/app";
		MyTask myTask = new MyTask();
		myTask.execute(path);
	}

	private class MyTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			String path = params[0];
			String success_path = null;
			try {
				success_path = downLoadApk(path);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return success_path;
		}

		private String downLoadApk(String path) throws Exception {
			String success_path = null;

			URL u = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Encoding", "identity");

			if (conn.getResponseCode() == 200) {
				
				success_path = apk_sd_path+"/"+path.substring(path.lastIndexOf("/"));

				maxLength = conn.getContentLength();
				pb.setMax(maxLength);
				
				maxText = df.format((float) maxLength / 1024 / 1024)
						+ "MB";
				InputStream inputStream = conn.getInputStream();

				FileOutputStream out = new FileOutputStream(success_path);

				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = inputStream.read(buffer)) != -1) {
					out.write(buffer, 0, len);
					this.publishProgress(len);
				}

				out.close();
			}

			return success_path;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (result != null) {
				Toast.makeText(MainActivity.this, "下载成功！", 0).show();
				File apkfile = new File(result);
				if (!apkfile.exists()) {
					return;
				}
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
						"application/vnd.android.package-archive");
				startActivity(i);
				
				
			} else {
				Toast.makeText(MainActivity.this, "下载失败！", 0).show();
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			
			int currentLength = pb.getProgress() + values[0];
			
			pb.setProgress(currentLength);
			
			currentText = df.format((float) currentLength / 1024 / 1024)
					+ "MB";
			tv.setText(currentText+"/"+maxText);
		}

		@Override
		protected void onCancelled(String result) {
			super.onCancelled(result);
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

	}
}
