package com.rocy.loaddown;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private final static String TAG = "Rocy";
	private static String imageUrl = "http://preview.quanjing.com/rob_her005/rob-712-1954.jpg";
	private static String apkUrl = "http://gdown.baidu.com/data/wisegame/b4ec9a6566176110/QQ_256.apk";
	private TextView tvProgressbar;
	private ProgressBar progressBar;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private ImageView ivImage;
	private File environment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvProgressbar = (TextView) findViewById(R.id.tv_progressbar);
		progressBar = (ProgressBar) findViewById(R.id.pb);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		ivImage = (ImageView) findViewById(R.id.iv_image);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			environment = Environment.getExternalStorageDirectory();

		} else {
			environment = Environment.getRootDirectory();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn1:
			MyTask task = new MyTask();
			task.execute(imageUrl);
			break;
		case R.id.btn2:
			MyTask task2 = new MyTask();
			task2.execute(apkUrl);
			break;
		case R.id.btn3:
			break;

		default:
			break;
		}
	}

	class MyTask extends AsyncTask<String, Integer, Object> {
		private int max;
		private boolean isDownLoad = true;

		@Override
		protected void onPreExecute() {
			Log.i(TAG, "预加载了。。。。" + isCancelled());
			super.onPreExecute();
		}

		@Override
		protected Object doInBackground(String... params) {
			String url = params[0];
			File file = null;
			try {
				file = findFile(url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.i(TAG, "加载中。。。。");
			return file;
		}

		private File findFile(String url2) throws Exception {
			// TODO Auto-generated method stub
			URL url = new URL(url2);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			max = connection.getContentLength();
			progressBar.setMax(max);
			File file = new File(environment.getAbsolutePath()
					+ url2.substring(url2.lastIndexOf("/")));
			InputStream inputStream = connection.getInputStream();
			FileOutputStream outputStream = new FileOutputStream(file);
			int len = 0;
			byte bt[] = new byte[1024];
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				while ( (len = inputStream.read(bt)) > -1) {
					this.publishProgress(len);
					outputStream.write(bt, 0, len);
				}
			}
			outputStream.close();
			inputStream.close();
			return file;
		}

		@Override
		protected void onPostExecute(Object result) {
			Log.i(TAG, "加载完成。。。。");
			super.onPostExecute(result);
			final File file = (File) result;
			String path = file.getAbsolutePath().toLowerCase();
			if (path.endsWith(".jpg") || path.endsWith(".png")) {
				BitmapFactory.Options option =new Options();
				option.inSampleSize=8;
				Bitmap bitmap = BitmapFactory
						.decodeFile(file.getAbsolutePath(),option);
				ivImage.setImageBitmap(bitmap);
			} else if (path.endsWith(".apk")) {
				// 弹出dialog
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("安装应用");
				builder.setMessage("是否安装此应用");
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@SuppressLint("InlinedApi")
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent intent = new Intent();
								intent.setAction(Intent.ACTION_VIEW);
								intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive" );
								MainActivity.this.startActivity(intent);
							}
						});
			}
			
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			Log.i(TAG, "进度加载了。。。。" + values[0]);
			super.onProgressUpdate(values);
			int pro = values[0];
			progressBar.setProgress(pro + progressBar.getProgress());
			tvProgressbar.setText("正在下载中" + ( progressBar.getProgress()
					/ max) * 100 + "%");
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			isDownLoad = false;
			Toast.makeText(MainActivity.this, "终止下载了", 0).show();
		}

	}

}
