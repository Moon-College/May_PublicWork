package com.ccg.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.message.BufferedHeader;

import com.ccg.log.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LogActivity extends Activity implements OnClickListener {
	Button conLog;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		MyLog.isDebug=true;
		/*
		 * //debug模式开启 System.out.println("view loaded"); Log.i("INFO", "view loaded"); Log.println(Log.INFO, "INFO", "view loaded"); MyLog.i("INFO", "hello bebug");
		 */
		// 通过id找到按钮
		conLog = (Button) findViewById(R.id.conLog);
		conLog.setOnClickListener(this);
	}

	public void onClick(View v) {
		// 按钮被点击到了，收集手机日志
		try {
			readLog("W");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author ccgao
	 * @param
	 * @throws IOException
	 */
	private void readLog(String pro) throws IOException {
		MyLog.p(Log.WARN, "warn", "start connectLog");
		StringBuffer sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");
		//cmdLine.add("-s");
		cmdLine.add("*:"+pro); //根据传参类型输出收集日志	
		Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		//输出流
		InputStream inputStream = exec.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream); // 装饰器模式
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);// 每一行拼接到sb里去
			sb.append("\n");// 加一行设置一个换行符
		}

		Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
	}
};