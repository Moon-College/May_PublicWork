package com.tz.mylogrunntime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.tz.mylogrunntime.util.UtilLog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btn_log;
	private TextView tv_log;
	private static final String TAG = "wdj";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_log = (Button) findViewById(R.id.btn_log);
		tv_log = (TextView) findViewById(R.id.tv_log);
		btn_log.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				try {
					UtilLog.i(TAG, "you click button!");
					readLog();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	private void readLog() throws IOException {
		UtilLog.i(TAG, "readLog");
		StringBuffer sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");
		cmdLine.add("-s");
		cmdLine.add("wdj");
		
		Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		InputStream is = exec.getInputStream();
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;
		while((str = bufferedReader.readLine()) != null) {
			sb.append(str).append("\n");
		}
		tv_log.setText(sb.toString());
	}

}
