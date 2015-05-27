package com.tz.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.tz.hello.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
    Button conLog;
	/** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        conLog = (Button) findViewById(R.id.conLog);//通过id找到的按钮
        MyLog.isDebug = true;//debug模式开启
////        System.out.println("view loaded");
////        Log.i("INFO", "view loaded");
//        Log.println(Log.INFO, "INFO", "view loaded");
//        MyLog.i("INFO", "hello debug");
       conLog.setOnClickListener(this);
    }
	public void onClick(View v) {
		//按钮被点击到了，收集收集日志
		try {
			readLog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Danny QQ 858030348  
	 * @throws IOException 
	 * 
	 */
	private void readLog() throws IOException {
		MyLog.i("INFO", "start connectLog");
		StringBuffer sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");//收集一次日志停止
		cmdLine.add("-s");//过滤
		cmdLine.add("INFO");
      //  cmdLine.add("ERROR") //收集错误信息
	  // cmdLine.add("DEBUG")  //收集debug信息
	//	cmdLine.add("WARN")   //收集警告信息
		Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		//获取执行命令后的输入流
		InputStream inputStream = exec.getInputStream();
		InputStreamReader buInputStreamReader = new InputStreamReader(inputStream);//装饰器模式
		BufferedReader bufferedReader = new BufferedReader(buInputStreamReader);//直接读字符串
		String str = null;
		while((str = bufferedReader.readLine())!=null){
			sb.append(str);//每读一行拼接到sb里面去
			sb.append("\n");//每一行一个换行符
		}
		//吐司
		Toast.makeText(this, sb.toString(), 1000).show();
	}
}