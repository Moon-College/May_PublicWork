package com.tz.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.tz.log.utils.MyLog;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	Button conLog;
	/** Called when the activity is first create. **/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyLog.isDebug = true;//debug模式开启
//		Log.println(Log.DEBUG, "INFO", "view loaded");
//		MyLog.i("INFO","view loaded");
		//View: 父类,Button:子类
		conLog = (Button) findViewById(R.id.collog);//通过id找到的按钮
		conLog.setOnClickListener(this);//注册监听
	}
	@Override
	public void onClick(View v) {
		//按钮被点击到了,收集手机日志
		try {
			readLog();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 收集自己手机中的日志
	 * @author Wp QQ 458246570 
	 * @throws IOException 
	 * @记得加权限
	 */
	private void readLog() throws IOException {
		MyLog.i("INFO", "start connectLog");
		StringBuffer sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");//收集一次日志停止
		cmdLine.add("-s");//过滤
		cmdLine.add("INFO");
		Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		//获取执行命令后的输入流
		InputStream inputStream = exec.getInputStream();
		InputStreamReader buInputStreamReader = new InputStreamReader(inputStream);//装饰者模式
		BufferedReader bufferedReader = new BufferedReader(buInputStreamReader);//读取字符窜
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);//每读一行拼接到sb里面去
			sb.append("\n");//每一行一个换行符
		}
		//吐司
		Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
	}
}
