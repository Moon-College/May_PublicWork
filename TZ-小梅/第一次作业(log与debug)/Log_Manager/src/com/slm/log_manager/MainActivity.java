package com.slm.log_manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.slm.log_manager.util.MyLog;

public class MainActivity extends Activity {
	private TextView logInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		logInfo = (TextView) findViewById(R.id.logInfo);
		MyLog.ISDEBUG = true;// 开启debug模式
		MyLog.i("INFO", "今天天气真好");
	}

	public void log(View view) {
		// 收集日志
		try {
			redLog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void redLog() throws IOException {
		MyLog.i("INFO", "开始连接日志");
		ArrayList<String> canline = new ArrayList<String>();
		canline.add("logcat");
		canline.add("-d");
		//canline.add("-s");
		canline.add("*:W");
		// Process 拿到运行时结果
		Process exec = Runtime.getRuntime().exec(
				canline.toArray(new String[canline.size()]));
		StringBuffer sb = new StringBuffer();
		// 获取执行命令后的输入里
		InputStream inputStream = exec.getInputStream();
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(isr);
		String str = null;
		while ((str = reader.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		logInfo.setText(sb.toString());
		// 吐司
		Toast.makeText(this, sb.toString(), 1000).show();
	}
}
