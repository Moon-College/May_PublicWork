package com.xby.mylog;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.xby.mylog.util.MyLog;

public class MyLogActivity extends Activity implements OnClickListener {
	Button btn = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn = (Button) findViewById(R.id.mBtn);
		btn.setOnClickListener(this);
		MyLog.flag = true;
		MyLog.log(Log.WARN, "Warn", "load");
		MyLog.log(Log.ERROR, "error", "errorload");
	}

	public void onClick(View view) {
		try {
			Toast.makeText(this, readLog(), Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(this, "获取日志错误", Toast.LENGTH_SHORT).show();
		}
	}

	private String readLog() throws Exception {
		StringBuffer sb = new StringBuffer();
		List<String> list = new ArrayList<String>();
		list.add("logcat");
		list.add("-d");
		list.add("*:W");

		Process exec = Runtime.getRuntime().exec(
				list.toArray(new String[list.size()]));
		InputStream inputStream = exec.getInputStream();
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		String lineStr = null;
		while ((lineStr = br.readLine()) != null) {
			sb.append(lineStr);
			sb.append("\n");
		}
		return sb.toString();
	}
}