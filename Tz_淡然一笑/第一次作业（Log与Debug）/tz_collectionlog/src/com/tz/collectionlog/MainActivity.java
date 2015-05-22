package com.tz.collectionlog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.tz.collectionlog.utils.MyLog;
import com.tz.logcollection.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	//收集日志按钮
	private Button btn_log;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化控件
		btn_log = (Button) findViewById(R.id.btn_collectionlog);
		//开启日志模式
		MyLog.ISDUG = true;
//		Log.w("WARN", "view loaded");  //直接采用日志某种等级
//		Log.println(Log.WARN, "WARN", "view loaded"); //通过参数传递日志等级
		//事件监听
		btn_log.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//点击收集日志按钮时，开始收集日志
		try {
			readLog("W");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 收集日志
	 * @throws IOException 
	 */
	private void readLog(String pro) throws IOException {
//		Log.i("INFO", "start collection log");
		Log.w("WARN", "start collection log");
		StringBuffer sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		//logcat -d -s INFO 收集INFO日志
		//logcat -d *:W     收集WARN系统错误日志
		cmdLine.add("logcat");
		cmdLine.add("-d"); //收集一次日志停止
		cmdLine.add("*:"+pro);
//		cmdLine.add("-s"); //过滤
//		cmdLine.add("WARN");
		//收集系统错误日志
		Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		
		//获取输入流
		InputStream is = exec.getInputStream();
		//装饰器模式
		InputStreamReader reader = new InputStreamReader(is);
		//直接读字符串
		BufferedReader br = new BufferedReader(reader);
		String str = null;
		while((str = br.readLine()) != null){
			//每读一行拼接到sb中
			sb.append(str);
			//换行
			sb.append("\n");
		}
		Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
	}
}
