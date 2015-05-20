package com.tz.zjm.hellologcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.tz.zjm.utils.LogUtil;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private TextView tv_log;
	private Button btn_warn_log, btn_error_log, btn_clear_log;
	private Timer mTimer;
	final ArrayList<String> cmdLine = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initView();
	}

	/**
	 * 初始化控件
	 * 
	 * @author Json
	 * */
	private void initView() {
		tv_log = (TextView) findViewById(R.id.tv_log);
		btn_warn_log = (Button) findViewById(R.id.btn_warn_log);
		btn_error_log = (Button) findViewById(R.id.btn_error_log);
		btn_clear_log = (Button) findViewById(R.id.btn_clear_log);
		btn_warn_log.setOnClickListener(this);
		btn_error_log.setOnClickListener(this);
		btn_clear_log.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View v) {
		Log.e("TZ", "搜集日志");
		cmdLine.clear();
		cmdLine.add("logcat");

		if(null != mTimer){
			mTimer.cancel();
		}	
		mTimer = new Timer();
		
		switch (v.getId()) {
		case R.id.btn_warn_log:
			cmdLine.add("-d");// 只搜集一次停止,输出日志到屏幕上
			cmdLine.add("*:v");
//			try {
//				readLog(cmdLine);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			mTimer.schedule(new TimerTask() {

				@Override
				public void run() {
					new CollectLog().execute(cmdLine);
				}
			}, 1000, 1000);
			break;
		case R.id.btn_error_log:
			cmdLine.add("-d");// 只搜集一次停止
			cmdLine.add("*:e");
			mTimer.schedule(new TimerTask() {

				@Override
				public void run() {
					new CollectLog().execute(cmdLine);
				}
			}, 1000, 1000);
			break;
		case R.id.btn_clear_log:
			cmdLine.add("-c");
			new CollectLog().execute(cmdLine);
			mTimer = null;
			break;
		default:
			break;
		}

	}
	
	@Override
	protected void onDestroy() {
		mTimer.cancel();
		super.onDestroy();
	}

	/**
	 * 搜集日志
	 * 
	 * @author Json
	 * @throws IOException
	 * */
	@SuppressWarnings("unused")
	private void readLog(ArrayList<String> cmdLine) throws IOException {
		StringBuffer sb = new StringBuffer();
		Process exec = Runtime.getRuntime().exec(
				cmdLine.toArray(new String[cmdLine.size()]));
		// 获取执行命令后的输入流
		InputStream inputStream = exec.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);// 装饰器模式
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);// 直接读字符串
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);// 每读一行拼接到sb里
			sb.append("\n");
		}
		tv_log.setText("");
		tv_log.setText(sb);
		// Toast.makeText(this, sb, Toast.LENGTH_LONG).show();

	}

	class CollectLog extends AsyncTask<ArrayList<String>, Void, String> {

		@Override
		protected String doInBackground(ArrayList<String>... params) {
		
			StringBuffer sb = new StringBuffer();
			ArrayList<String> cmdLine = params[0];
			ArrayList<String> clearLog=new ArrayList<String>();  //设置命令  logcat -c 清除日志
            clearLog.add("logcat");
            clearLog.add("-c");
			try {
				Process exec = Runtime.getRuntime().exec(
						cmdLine.toArray(new String[cmdLine.size()]));
				// 获取执行命令后的输入流
				InputStream inputStream = exec.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);// 装饰器模式
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);// 直接读字符串
				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
//					Runtime.getRuntime().exec(clearLog.toArray(new String[clearLog.size()]));  //清理日志....这里至关重要，不清理的话，任何操作都将产生新的日志，代码进入死循环，直到bufferreader满
					sb.append(str);// 每读一行拼接到sb里
					sb.append("\n");
				}
			} catch (IOException e) {

			}

			return sb.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			tv_log.setText("");
			tv_log.setText(result);
//			super.onPostExecute(result);
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

}
