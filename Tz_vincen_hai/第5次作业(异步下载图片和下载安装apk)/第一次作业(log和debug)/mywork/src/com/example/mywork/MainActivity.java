package com.example.mywork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button btn_log;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		MyLog.isDebug = true;
		btn_log.setOnClickListener(this);
		
	}

	private void initView() {
		btn_log = (Button) findViewById(R.id.btn_log);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_log:
			try {
				readLog();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 读取日志
	 * @throws IOException 
	 */
	private void readLog() throws IOException {
		MyLog.i("INFO", "读取日志信息");
		
		StringBuffer sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");
		//cmdLine.add("-s");
		cmdLine.add("INFO");
		
		Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		
		InputStream inputStream = exec.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream);
		BufferedReader buf = new BufferedReader(reader);
		String str= null;
		while((str= buf.readLine())!=null){
			sb.append(str);
			sb.append("\n");
		}
		
		Toast.makeText(this, sb.toString(), 2000).show();
	}


}
