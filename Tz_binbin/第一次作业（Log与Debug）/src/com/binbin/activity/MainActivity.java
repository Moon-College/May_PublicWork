package com.binbin.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.binbin.R;
import com.binbin.utils.IoUtils;
import com.binbin.utils.LogUtils;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	
	private List<String> cmd=new ArrayList<String>(0);
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//设置打开LOG信息
		LogUtils.setLogSwitch(true);
		initView();
	}
	
	private void initView(){
		tv=(TextView) findViewById(R.id.tvInfo);
	}
	public void clearInfo(View v) {
		tv.setText(getString(R.string.noInfo));
	}

	public void getInfo(View v) {
		tv.setText("");
		cmd.clear();
		cmd.add("logcat");
		cmd.add("-d");
		cmd.add("*:w");
		Process exec = null;
		String infoStr="";
		try {
			exec = Runtime.getRuntime().exec(cmd.toArray(new String[cmd.size()]));
		} catch (IOException e) {
			LogUtils.printLog(Log.ERROR, TAG, e.toString());
		}
		if(null==exec){
			infoStr="获取失败";
		}else{
			infoStr=IoUtils.read(exec.getInputStream());
		}
		tv.setText(infoStr);
	}

}
