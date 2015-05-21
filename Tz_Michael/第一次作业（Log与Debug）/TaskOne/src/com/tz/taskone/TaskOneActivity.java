package com.tz.taskone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.tz.utils.LogUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class TaskOneActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void getLog(View v){
    	LogUtils.isDebug=true;//开启log
    	LogUtils.w("warn", "this is an warnning info");
    	LogUtils.e("error", "this is an error info");
    	LogUtils.println(Log.WARN, "warn", "this is an warnning info by println");
    	LogUtils.println(Log.ERROR, "error", "this is an error info by println");
    	StringBuffer buffer=new StringBuffer();
    	buffer.append(getLogWarnOrError("WARN"));
    	buffer.append("\n");
    	buffer.append(getLogWarnOrError("ERROR"));
    	Toast.makeText(this, buffer.toString(), Toast.LENGTH_LONG).show();
    }

    /**
     * 获取系统log ，并通过toast显示
     */
	private String getLogWarnOrError(String selector) {
		StringBuffer buffer=new StringBuffer();
		List<String> cmdLine=new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");//只取一次log
		cmdLine.add("-s");//过滤
		cmdLine.add("*:"+selector);
		try {
			Process exec=Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
			InputStream in=exec.getInputStream();
			InputStreamReader reader=new InputStreamReader(in);
			BufferedReader bReader=new BufferedReader(reader);
			String temp=null;
			while((temp=bReader.readLine())!=null){
				buffer.append(temp);
				buffer.append("\n");
			}
			if(buffer.toString().trim().length()!=0){
				return buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
    
}