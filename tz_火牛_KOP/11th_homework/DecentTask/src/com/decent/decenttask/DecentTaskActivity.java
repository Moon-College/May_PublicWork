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
	 * ͼƬ��ַ����ʱͼƬ��ַ
	 */
	private static final String IMG_PATH = "http://f.hiphotos.baidu.com/zhidao/pic/item/1b4c510fd9f9d72a357dd0b2d12a2834349bbb00.jpg";
	private static final String TEMP_IMG_PATH = Environment
			.getExternalStorageDirectory() + "/temp.jpg";
	/*
	 * apk��ַ����ʱapk��ַ
	 */
	private static final String APK_PATH = "http://image.ssports.com/appdl/bplup.2.1.5.apk";
	private static final String TEMP_APK_PATH = Environment
			.getExternalStorageDirectory() + "/temp.apk";
	/*
	 * ִ����AsyncTask����֮��Ĳ���
	 */
	// ��apk
	private static final String OPEN_THEAPK = "com.decent.action.OPEN_THEAPK";
	// ��ʾimg
	private static final String SHOW_THEIMG = "com.decent.action.SHOW_THEIMG";
	// ǿ�ƹر�
	private static final String FORCE_STOP = "com.decent.action.FORCE_STOP";
	/*
	 * back��ť������֮���handler��what��Ӧ ��flag
	 */
	private static final int BACK_CLICK = 1;
	/*
	 * back��ť�Ƿ񱻰����ı�־λ
	 */
	private boolean mIsBackClicked = false;
	/*
	 * ���ڴ���back��ť��handler
	 */
	private Handler mHandler;
	/*
	 * �����ؼ�
	 */
	private Button btn;
	private TextView tv;
	private ProgressBar pb;
	private ImageView iv;
	private Button apkBtn;
	private Button stopBtn;
	/*
	 * ayncTask�������б�
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
		 * ���ø���������onclick�¼�
		 */
		btn.setOnClickListener(this);
		apkBtn.setOnClickListener(this);
		stopBtn.setOnClickListener(this);
		/*
		 * ��ʼ��ͼƬ�ı���
		 */
		iv.setImageBitmap(null);
		iv.setBackgroundColor(R.color.alpha);

		/*
		 * ��ʼ���ж�back��handler
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
			 * ����������ͼƬ·������ʱͼƬ·�����������֮��Ĳ���
			 */
			asyncDownloadTask.execute(IMG_PATH, TEMP_IMG_PATH, SHOW_THEIMG);
			break;
		case R.id.apkBtn:
			asyncDownloadTask = new AsyncDownloadTask();
			downTaskList.add(asyncDownloadTask);
			/*
			 * ����������ͼƬ·������ʱͼƬ·�����������֮��Ĳ���
			 */
			asyncDownloadTask.execute(APK_PATH, TEMP_APK_PATH, OPEN_THEAPK);
			break;
		case R.id.stopBtn:
			/*
			 * ֹͣ��ť��������������cancel
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
				 * ����û���2�����ڰ���back������cancel�������񣬲���finish
				 */
				for (AsyncDownloadTask task : downTaskList) {
					task.cancel(true);
				}
				finish();
			} else {
				/*
				 * ����û��ǵ�һ�ΰ�back����������������֮�ⰴback ���mIsBackClicked = true
				 * ������֮���mHandler������Ϣ������֮���mIsBackClicked��ֵfalse
				 */
				Toast.makeText(this, "�ٰ�һ�ξ��˳�������", Toast.LENGTH_SHORT).show();
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
				 * ��ʱ����
				 */
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setConnectTimeout(3000);
				InputStream inputStream = connection.getInputStream();
				/*
				 * ����ContentLength��ʼ��pb
				 */
				contentSize = connection.getContentLength();
				pb.setMax(contentSize);
				/*
				 * ����ʱ�ļ�
				 */
				File file = new File(temp_path);
				OutputStream outputStream = new FileOutputStream(file);

				/*
				 * �����ļ��Ĵ�С����ʼ��������
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
				 * ���ʱ����
				 */
				while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
					/*
					 * ���ʱ�Ĳ�����������isForceStop,�ڰ���stop��ť�����˳�֮��ֱ��������˳���Ȼ�󷵻�
					 * FORCE_STOP�����κβ���
					 */
					if (isForceStop) {
						Log.d("INFO", "force stopped");
						/*
						 * �ر����������
						 * ���½���
						 * ����FORCE_STOP
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
					 * ���½���
					 */
					publishProgress(currentSize);
				}
				/*
				 * ������ɸ��½���
				 */
				publishProgress(contentSize);
				/*
				 * �ر����������
				 */
				outputStream.close();
				inputStream.close();
				// bitmap = BitmapFactory.decodeFile(temp_path);
				/*
				 * ������ɲŸ�after_finish��ֵ
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
			// ��cancel��ʱ������isForceStopΪtrue
			isForceStop = true;
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(String result) {
			downTaskList.remove(this);
			/*
			 * ���ݺ��ڲ�����result�룬����Ӧ�Ĳ���
			 */
			// TODO Auto-generated method stub
			if (SHOW_THEIMG.equals(result)) {
				/*
				 * ����ͼƬ
				 */
				Bitmap bitmap = BitmapFactory.decodeFile(TEMP_IMG_PATH);
				iv.setImageBitmap(bitmap);
			} else if (OPEN_THEAPK.equals(result)) {
				/*
				 * ��apk
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
			 * �����ǰisForceStop��trueֱ�Ӹ�λ��������������ʾ
			 */
			if (isForceStop) {
				progress = 0;
				pb.setProgress(0);
				text = "/";
			}
			/*
			 * ���¿ؼ���ʾ
			 */
			pb.setProgress(progress);
			tv.setText(text);
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

	}
}