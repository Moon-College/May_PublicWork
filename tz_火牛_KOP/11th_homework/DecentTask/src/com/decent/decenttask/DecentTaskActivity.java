package com.decent.decenttask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.decent.util.ReflictionUtil;

public class DecentTaskActivity extends Activity implements OnClickListener {

	/*
	 * 图片地址和临时图片地址
	 */
	private static final String IMG_PATH = "http://f.hiphotos.baidu.com/zhidao/pic/item/1b4c510fd9f9d72a357dd0b2d12a2834349bbb00.jpg";
	private static final String TEMP_IMG_PATH = Environment
			.getExternalStorageDirectory() + "/temp.jpg";
	/*
	 * apk地址和临时apk地址
	 */
	private static final String APK_PATH = "http://image.ssports.com/appdl/bplup.2.1.5.apk";
	private static final String TEMP_APK_PATH = Environment
			.getExternalStorageDirectory() + "/temp.apk";
	/*
	 * 执行完AsyncTask任务之后的操作
	 */
	// 打开apk
	private static final String OPEN_THEAPK = "com.decent.action.OPEN_THEAPK";
	// 显示img
	private static final String SHOW_THEIMG = "com.decent.action.SHOW_THEIMG";
	// 强制关闭
	private static final String FORCE_STOP = "com.decent.action.FORCE_STOP";
	/*
	 * back按钮被按到之后的handler的what对应 的flag
	 */
	private static final int BACK_CLICK = 1;
	/*
	 * back按钮是否被按到的标志位
	 */
	private boolean mIsBackClicked = false;
	/*
	 * 用于处理back按钮的handler
	 */
	private Handler mHandler;
	/*
	 * 各个控件
	 */
	private Button btn;
	private TextView tv;
	private ProgressBar pb;
	private ImageView iv;
	private Button apkBtn;
	private Button stopBtn;
	/*
	 * ayncTask的任务列表
	 */
	private List<AsyncDownloadTask> downTaskList = new ArrayList<DecentTaskActivity.AsyncDownloadTask>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflictionUtil.InjectionView(DecentTaskActivity.class.getName(),
				R.class.getName(), this);
		/*
		 * 设置各个按键的onclick事件
		 */
		btn.setOnClickListener(this);
		apkBtn.setOnClickListener(this);
		stopBtn.setOnClickListener(this);
		/*
		 * 初始化图片的背景
		 */
		iv.setImageBitmap(null);
		iv.setBackgroundColor(R.color.alpha);

		/*
		 * 初始化判断back的handler
		 */
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {

				if (msg.what == BACK_CLICK) {
					mIsBackClicked = false;
				}
				// TODO Auto-generated method stub
				super.handleMessage(msg);
			}

		};
	}

	public void onClick(View v) {
		AsyncDownloadTask asyncDownloadTask = null;
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn:
			iv.setImageBitmap(null);
			iv.setBackgroundColor(R.color.alpha);
			asyncDownloadTask = new AsyncDownloadTask();
			downTaskList.add(asyncDownloadTask);
			/*
			 * 三个参数：图片路径，临时图片路径，下载完成之后的操作
			 */
			asyncDownloadTask.execute(IMG_PATH, TEMP_IMG_PATH, SHOW_THEIMG);
			break;
		case R.id.apkBtn:
			asyncDownloadTask = new AsyncDownloadTask();
			downTaskList.add(asyncDownloadTask);
			/*
			 * 三个参数：图片路径，临时图片路径，下载完成之后的操作
			 */
			asyncDownloadTask.execute(APK_PATH, TEMP_APK_PATH, OPEN_THEAPK);
			break;
		case R.id.stopBtn:
			/*
			 * 停止按钮，给各个任务发送cancel
			 */
			for (AsyncDownloadTask task : downTaskList) {
				task.cancel(true);
			}
			break;
		default:
			break;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mIsBackClicked) {
				/*
				 * 如果用户在2秒以内按了back键，则cancel各个任务，并且finish
				 */
				for (AsyncDownloadTask task : downTaskList) {
					task.cancel(true);
				}
				finish();
			} else {
				/*
				 * 如果用户是第一次按back，或则两次在两秒之外按back 则给mIsBackClicked = true
				 * 给两秒之后的mHandler发送消息，两秒之后给mIsBackClicked赋值false
				 */
				Toast.makeText(this, "再按一次就退出！！！", Toast.LENGTH_SHORT).show();
				Message msg = mHandler.obtainMessage();
				msg.what = BACK_CLICK;
				mIsBackClicked = true;
				mHandler.sendMessageDelayed(msg, 2000);
			}
			return false;
		}
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	private class AsyncDownloadTask extends AsyncTask<String, Integer, String> {

		private int contentSize;
		private int currentSize;
		boolean isForceStop = false;

		@Override
		protected String doInBackground(String... params) {
			String http_path = params[0];
			String temp_path = params[1];
			String after_finish = null;

			try {
				URL url = new URL(http_path);
				/*
				 * 耗时操作
				 */
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setConnectTimeout(3000);
				InputStream inputStream = connection.getInputStream();
				/*
				 * 根据ContentLength初始化pb
				 */
				contentSize = connection.getContentLength();
				pb.setMax(contentSize);
				/*
				 * 打开临时文件
				 */
				File file = new File(temp_path);
				OutputStream outputStream = new FileOutputStream(file);

				/*
				 * 根据文件的大小来初始化缓冲区
				 */
				byte buffer[];
				if(contentSize>10*1024*1024)
				{
					buffer = new byte[4096*10];
				}
				else
				{
     				buffer = new byte[4096];
				}
				int len = 0;
				currentSize = 0;
				/*
				 * 最耗时操作
				 */
				while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
					/*
					 * 最耗时的操作这里增加isForceStop,在按了stop按钮或者退出之后，直接让这个退出，然后返回
					 * FORCE_STOP不做任何操作
					 */
					if (isForceStop) {
						Log.d("INFO", "force stopped");
						/*
						 * 关闭输入输出流
						 * 更新进度
						 * 返回FORCE_STOP
						 */
						outputStream.close();
						inputStream.close();
						publishProgress(0);
						return FORCE_STOP;
					}
					outputStream.write(buffer, 0, len);
					Log.d("INFO", "len=" + len + ",currentSize=" + currentSize);
					currentSize += len;
					// pb.setProgress(pb.getProgress()+len);
					/*
					 * 更新进度
					 */
					publishProgress(currentSize);
				}
				/*
				 * 下载完成更新进度
				 */
				publishProgress(contentSize);
				/*
				 * 关闭输入输出流
				 */
				outputStream.close();
				inputStream.close();
				// bitmap = BitmapFactory.decodeFile(temp_path);
				/*
				 * 下载完成才给after_finish赋值
				 */
				after_finish = params[2];
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// TODO Auto-generated method stub
			return after_finish;
		}

		@Override
		protected void onCancelled() {
			// 在cancel的时候设置isForceStop为true
			isForceStop = true;
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(String result) {
			downTaskList.remove(this);
			/*
			 * 根据后期操作的result码，做相应的操作
			 */
			// TODO Auto-generated method stub
			if (SHOW_THEIMG.equals(result)) {
				/*
				 * 更新图片
				 */
				Bitmap bitmap = BitmapFactory.decodeFile(TEMP_IMG_PATH);
				iv.setImageBitmap(bitmap);
			} else if (OPEN_THEAPK.equals(result)) {
				/*
				 * 打开apk
				 */
				Intent intent = new Intent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(new File(TEMP_APK_PATH)),
						"application/vnd.android.package-archive");
				startActivity(intent);
			}

			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			Log.d("INFO", "into onProgressUpdate");
			int progress = values[0];
			String text = values[0] + "/" + contentSize;
			/*
			 * 如果当前isForceStop是true直接复位进度条和文字显示
			 */
			if (isForceStop) {
				progress = 0;
				pb.setProgress(0);
				text = "/";
			}
			/*
			 * 更新控件显示
			 */
			pb.setProgress(progress);
			tv.setText(text);
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

	}
}