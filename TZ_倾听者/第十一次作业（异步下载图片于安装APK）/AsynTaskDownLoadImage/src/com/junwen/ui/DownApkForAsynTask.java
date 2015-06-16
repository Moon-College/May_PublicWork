package com.junwen.ui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asyntaskdownloadimage.R;
import com.junwen.adapter.AppAdapter;
import com.junwen.bean.App;
import com.junwen.constant.Constant;
import com.junwen.util.InstallApk;

public class DownApkForAsynTask extends Activity implements OnClickListener, OnItemClickListener {

	private ProgressBar bar; // 进度条
	private Button down; // 下载按钮
	private TextView text; // 更新文本
	private Button unInstall; // 卸载
	private ListView lv;
	private List<App> data;
	private AppAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.downapk_layout);
		initView();
		initDate();
	}
	/**
	 * 获取所有应用
	 */
	private void initDate() {
		//获取所有应用程序信息
		data = InstallApk.selectApplication(DownApkForAsynTask.this);
		adapter = new AppAdapter(DownApkForAsynTask.this,data);
		lv.setAdapter(adapter);
	}

	/**
	 * 初始化
	 */
	private void initView() {
		bar = (ProgressBar) findViewById(R.id.apk_progress);
		down = (Button) findViewById(R.id.down);
		text = (TextView) findViewById(R.id.apk_text);
		unInstall = (Button) findViewById(R.id.uninstall);
		lv = (ListView) findViewById(R.id.listview);
		lv.setOnItemClickListener(this);
		data = new ArrayList<App>();
		down.setOnClickListener(this);
		unInstall.setOnClickListener(this);
	}

	/**
	 * 当点击下载QQ时
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.down:
			//下载图片
			DownQQForAsynTask task = new DownQQForAsynTask(); // 创建异步
			task.execute(Constant.APK_PAHT); // 启动异步
			break;
		case R.id.uninstall:
			//卸载图片
			InstallApk.uninstall(Constant.APK_PACKAGE, DownApkForAsynTask.this);
			break;

		default:
			break;
		}

	}
	

	/**
	 * 执行下载APK
	 * 
	 * @author admi
	 * 
	 */
	private class DownQQForAsynTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {

			InputStream is = null;

			FileOutputStream fos = null;
			BufferedInputStream bis = null;

			BufferedOutputStream bos = null;

			String root = params[0]; // apk路径
			try {
				URL url = new URL(root); // 创建HttpClient
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection(); // 创建连接
				conn.setReadTimeout(5000);
				conn.setRequestMethod("GET");
				int code = conn.getResponseCode();
				if (code == 200) {
					// 如果连接成功!

					bar.setMax(conn.getContentLength()); // 设置进度条最大值
					System.out.println(conn.getContentLength() + "");

					String path = Environment.getExternalStorageDirectory()
							.getAbsolutePath().toString().trim()
							+ "/" + root.lastIndexOf("="); // 获取文件存储绝对路径
					System.out.println("dddd");
					is = conn.getInputStream(); // 创建输入流

					fos = new FileOutputStream(path); // 创建文件输出流

					bis = new BufferedInputStream(is); // 创建缓冲输入流

					bos = new BufferedOutputStream(fos); // 创建缓冲输出流

					int len = 0;
					byte[] buffer = new byte[1024];
					while ((len = bis.read(buffer)) != -1) {
						bos.write(buffer, 0, len); // 写入文件
						this.publishProgress(len); // 更新进度条
					}
					// 关闭流
					bos.close();
					bis.close();
					fos.close();
					is.close();
					return path;
				} else {
					Toast.makeText(DownApkForAsynTask.this, "连接服务器失败!", 0)
							.show();
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 安装软件
		 */
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// 安装APP
			InstallApk.installApplication(result, DownApkForAsynTask.this);
			text.setText("下载完成！");
		}

		/**
		 * 进度条更新
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			int index = values[0]; // 获取写入字节数

			bar.setProgress(bar.getProgress() + index); // 设置进度

			int name = 100 * bar.getProgress() / bar.getMax(); // 获得下载的百分比

			text.setText("当前下载进度：" + name + "%"); // 设置进度文本

		}
	}


	/**
	 * 当点击条目的时候进行卸载
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//对点击的条目进行卸载
		App app = data.get(arg2);
		InstallApk.uninstall(app.getPackageName(), DownApkForAsynTask.this);
		System.out.println(app.getPackageName());
	}
	/**
	 * 当卸载完，界面重新可见时，重新获取
	 */
	@Override
	protected void onRestart() {
		super.onRestart();
		if(adapter != null) {
			initDate();
		}
	}
}
