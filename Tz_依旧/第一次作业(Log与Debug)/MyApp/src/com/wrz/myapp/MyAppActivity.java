package com.wrz.myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.wrz.util.MyLog;

public class MyAppActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MyLog.isDebug = true;
        MyLog.i("INFO", "myApp loaded");
        MyLog.println(Log.INFO, "INFO", "myApp loaded");
        // 通过id获取按钮
        Button logBtn = (Button)findViewById(R.id.logBtn);
        logBtn.setOnClickListener(this);
    }

    /***
     * 按钮点击后响应事件
     * @author Wangrz
     */
	public void onClick(View v) {
		try {
			readLog();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 收集系统日志
	 * @throws IOException 
	 */
	private void readLog() throws IOException {
		MyLog.i("INFO", "start connectlog");
		//MyLog.w("WARN", "warn日志测试");
		//MyLog.e("ERROR", "error日志测试");
		StringBuffer sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");// 收集一次日志停止
		cmdLine.add("-s");// 过滤
		//cmdLine.add("INFO");// info级日志
		//cmdLine.add("w");// warn级日志
		cmdLine.add("*:E");// error级日志
		// 执行cmd命令
		Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		// 执行完命令后的输入流
		InputStream inputStream = exec.getInputStream();
		InputStreamReader buInputStreamReader = new InputStreamReader(inputStream);// 装饰器模式
		BufferedReader bufferReader = new BufferedReader(buInputStreamReader, 1024);
		String str = null;
		while((str = bufferReader.readLine()) != null){
			sb.append(str);// 每读取一行拼接到sb里面
			sb.append("\n");
		}
		//吐司
		Toast.makeText(this, sb.toString(), 1000).show();
	}
}