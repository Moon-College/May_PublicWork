package com.fancky.logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button startBtn;
	private Button closeBtn;
	private Button collectBtn;
	private static final String TAG = "INFO";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startBtn = (Button) findViewById(R.id.btn_start);
		closeBtn = (Button) findViewById(R.id.btn_close);
		collectBtn = (Button) findViewById(R.id.btn_collect);
		startBtn.setOnClickListener(this);
		closeBtn.setOnClickListener(this);
		collectBtn.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
			FanckyLog.isPrintLog = true;
			testLog();
			break;
			
		case R.id.btn_close:
			FanckyLog.isPrintLog = false;
			testLog();
			break;
			
		case R.id.btn_collect:
			FanckyLog.isPrintLog = true;
			try {
				collectLog();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}
	
	/**
	 * 测试方法
	 * @author Fancky
	 */
	private void testLog() {
		FanckyLog.v(TAG, "verbose");
		FanckyLog.d(TAG, "hello debug.");
		FanckyLog.i(TAG, "test print log.");
		FanckyLog.w(TAG, "this is a warn log");
		FanckyLog.e(TAG, "this is an error log");
	}

	/**
	 * 收集系统错误日志
	 * @author Fancky
	 * @throws IOException 
	 */
	private void collectLog() throws IOException {
		FanckyLog.w(TAG, "collect error info");
		StringBuffer out = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");//收集一次日志停止
		cmdLine.add("-s");//过滤
		cmdLine.add("*:W");
		Runtime runtime = Runtime.getRuntime();
		String[] ary = new String[cmdLine.size()];
		Process exec = runtime.exec(cmdLine.toArray(ary));
		//获取输入执行命令后的输入流
		InputStream is = exec.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);//装饰器模式
		BufferedReader br = new BufferedReader(isr);
		String str = null;
		while((str = br.readLine()) != null) {
			out.append(str);//没读一行就追加到out里面
			out.append("\n");//每一行一个换行符
		}
		Toast.makeText(this, out.toString(), Toast.LENGTH_LONG).show();
	}
	
	
}
