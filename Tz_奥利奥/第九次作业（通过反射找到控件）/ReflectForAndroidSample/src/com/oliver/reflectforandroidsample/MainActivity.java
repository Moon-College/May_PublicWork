package com.oliver.reflectforandroidsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.oliver.reflectforandroidsample.util.ReflectionUtil;

public class MainActivity extends Activity{
	/**
	 * 点击次数的记录
	 */
	int i = 0;
	
	/**
	 * 文本控件
	 */
	private TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;
	
	/**
	 * 文本控件集合
	 */
	TextView[] week;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//调用反射方法对控件进行初始化
		ReflectionUtil.initView(this);
		week = new TextView[]{monday, tuesday, wednesday, thursday, friday, saturday, sunday};
	}
	
	public void onClick(View view){
		Toast.makeText(this, week[i%7].getText().toString(), Toast.LENGTH_LONG).show();
		i++;
		
	}
}
