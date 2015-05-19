package com.example.lesson2.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.example.lesson2.R;
import com.example.lesson2.util.AppLog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/** 
 * @author Carlos
 * @version 1.0
 * @updateTime 2015年5月19日 上午1:52:35
 * Description: 
 */
public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AppLog.isDebug = true;
		AppLog.i(TAG, "onCreate() has been invoked!");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		doError();
	}
	
	private void doError(){
		AppLog.i(TAG, "make a wrong by called doError()");
		try {
			int b = 1/0;
		} catch (Exception e) {
			AppLog.e(TAG, Log.getStackTraceString(e));
		}
	}
	
	public void showErrorLog(View v){
		//doError();
		AppLog.i(TAG, "start collect the error log...");
		StringBuffer sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");
		cmdLine.add("*:W");
		try {
			Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
			BufferedReader reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
			String str =null;
			while((str =reader.readLine())!=null){
				sb.append(str);
				sb.append("\n");
			}
			Toast.makeText(this,sb.toString(),1000).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
