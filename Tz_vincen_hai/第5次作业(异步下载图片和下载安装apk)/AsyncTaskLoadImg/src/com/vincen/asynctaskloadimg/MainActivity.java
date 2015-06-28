package com.vincen.asynctaskloadimg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpClientConnection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private TextView tv_loading;
	private ProgressBar pb_load;
	private Button btn_load_img;
	private Button btn_load_apk;
	private ImageView img;

	private int maxLength;
	private String SD_ROOT = Environment.getExternalStorageDirectory()
			.getAbsolutePath();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

		btn_load_img.setOnClickListener(this);
		btn_load_apk.setOnClickListener(this);
	}

	private void initView() {
		tv_loading = (TextView) findViewById(R.id.tv_loading);
		pb_load = (ProgressBar) findViewById(R.id.pb_load);
		btn_load_img = (Button) findViewById(R.id.btn_load_img);
		img = (ImageView) findViewById(R.id.img);
		btn_load_apk = (Button) findViewById(R.id.btn_load_apk);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_load_img:   //下载 图片
			String url = "http://g.hiphotos.baidu.com/image/pic/item/3c6d55fbb2fb43166b84607422a4462309f7d320.jpg";
			MyAsyncTask task = new MyAsyncTask();
			task.execute(url);
			break;
		case R.id.btn_load_apk:  //下载apk
			String apkUrl = "http://gdown.baidu.com/data/wisegame/bf53f2c39e440d43/anzhuoshichang_91.apk";
			MyAsyncTaskLoadApk task1 = new MyAsyncTaskLoadApk();
			task1.execute(apkUrl);
			break;

		default:
			break;
		}
		
	}
	
	//通过异步任务下载apk并完成安装
	class MyAsyncTaskLoadApk extends AsyncTask<String, Integer, File>{

		@Override
		protected File doInBackground(String... params) {
			try {
				File file = downLoadApk(params[0]);
				return file ;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		//下载apk
		private File downLoadApk(String url) throws MalformedURLException {
			String fileName = url.substring(url.lastIndexOf("/"));
			File file = new File(SD_ROOT+"/"+fileName);
			URL u = new URL(url);
			
			try {
				HttpURLConnection conn = (HttpURLConnection) u.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5000);
				if(conn.getResponseCode() == 200){
					maxLength = conn.getContentLength();
					pb_load.setMax(maxLength);
					
					InputStream inputStream = conn.getInputStream();
					FileOutputStream out = new FileOutputStream(file);
					
					byte[] buf = new byte[1024];
					int len = 0;
					while((len = inputStream.read(buf)) != -1){
						out.write(buf, 0, len);
						this.publishProgress(len);
					}
					
					out.close();
					inputStream.close();
					
					return file ;
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}

		//预处理
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
		}
		
		//完成之后该做啥事
		@Override
		protected void onPostExecute(File file) {
			super.onPostExecute(file);
			 Intent intent = new Intent();
             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             intent.setAction(android.content.Intent.ACTION_VIEW);
             intent.setDataAndType(Uri.fromFile(file),
                             "application/vnd.android.package-archive");
             startActivity(intent);
		}
		
		//更新进度条
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			
			int len = values[0];
			pb_load.setProgress(pb_load.getProgress()+len);
			String text = 100*pb_load.getProgress()/maxLength +"%";
			tv_loading.setText(text);
			
		}
		
		
		
	}
	
	//通过异步任务下载img并显示
	class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

		// 执行任务
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			try {
				Bitmap bitmap = downLoadImage(url);
				return bitmap;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		private Bitmap downLoadImage(String url) throws IOException {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(5000); // 设置超时时间
			conn.setRequestMethod("GET"); // 设置请求方式

			if (conn.getResponseCode() == 200) {
				maxLength = conn.getContentLength(); // 获取内容的长度
				pb_load.setMax(maxLength); // 设置进度条的长度

				InputStream inputStream = conn.getInputStream();

				// 图片文件名
				String fileName = url.substring(url.lastIndexOf("/"));
				FileOutputStream out = new FileOutputStream(SD_ROOT + "/"
						+ fileName);
				byte[] buf = new byte[1024];
				int len = 0;
				while ((len = inputStream.read(buf)) != -1) {
					out.write(buf, 0, len);
					// 更新进度条
					this.publishProgress(len);
				}

				out.close();

				Bitmap bitmap = BitmapFactory.decodeFile(SD_ROOT + "/"
						+ fileName);
				return bitmap;
			}
			return null;
		}

		// 启动任务之前调用
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		// 任务完成之后调用
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);

			img.setImageBitmap(result);
		}

		// 更新进度条
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);

			int len = values[0];
			pb_load.setProgress(pb_load.getProgress() + len);
			String text = "下载进度：" + 100 * pb_load.getProgress() / maxLength;
			tv_loading.setText(text);
		}

	}
}
