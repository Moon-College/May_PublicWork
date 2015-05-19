package com.myandroid.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.myandroid.hello.util.MyLog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private Button getLogInfo;
	private TextView textview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		MyLog.isDebug = true;
		getLogInfo = (Button) findViewById(R.id.getLogInfo);
		textview = (TextView) findViewById(R.id.textview);
		getLogInfo.setOnClickListener(this);
		// Log.println(priority, tag, msg);

	}

	public void onClick(View v) {
		 MyLog.println(Log.WARN, "WARN", "------------- 输出信息");
		// textview.setText("我也是醉了");
		try {
			readLogInfo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readLogInfo() throws IOException{
		StringBuffer sb = new StringBuffer();
		ArrayList<String> logList = new ArrayList<String>();
			logList.add("logcat");
			logList.add("-d"); // 收集一次日志停止
			logList.add("-s"); // 过滤
//			logList.add("-t");
			logList.add("WARN"); // 打印警告以上的Log信息
			Process exec = Runtime.getRuntime().exec(
					logList.toArray(new String[logList.size()]));
			InputStream inputStream = exec.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream); // 装饰器
			BufferedReader readLog = new BufferedReader(isr);
			String str = null;
			while ((str = readLog.readLine()) != null) {			
				sb.append(str);
				sb.append("\n");
			}
			textview.setText(sb.toString());
		Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
	}
}