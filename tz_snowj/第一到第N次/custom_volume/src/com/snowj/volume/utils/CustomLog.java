package com.snowj.volume.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class CustomLog {

	
	/**
	 * 是否正常打印日志的标记
	 */
	private static boolean logFLag=true;;
	
	/**
	 * 自定义LOG
	 * @param priority 优先级 Log.Debug,Log.info....
	 * @param tag 标签
	 * @param msg 打印信息
	 * @return
	 */
	public static int println(int priority, String tag, String msg){
		if(logFLag){
			return Log.println(priority, tag, msg);
		}
		return 0;
	}
	
	public void readLogFromRunntime(Context context) throws IOException{
		println(Log.DEBUG, "test", "start read log:");
		ArrayList<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		list.add("logcat");
 		list.add("-d");
		list.add("-s");
		list.add("test");
		
		Process process = Runtime.getRuntime().exec(list.toArray(new String[list.size()]));
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String str = null;
		while((str = br.readLine())!=null){
			//拼接
			sb.append(str);
			//换行
			sb.append("\n");
		}
		Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
		sb=null;
	}
	
}
