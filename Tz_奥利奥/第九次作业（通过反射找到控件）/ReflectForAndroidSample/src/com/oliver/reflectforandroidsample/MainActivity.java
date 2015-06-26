package com.oliver.reflectforandroidsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.oliver.reflectforandroidsample.util.ReflectionUtil;

public class MainActivity extends Activity{
	/**
	 * ��������ļ�¼
	 */
	int i = 0;
	
	/**
	 * �ı��ؼ�
	 */
	private TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;
	
	/**
	 * �ı��ؼ�����
	 */
	TextView[] week;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//���÷��䷽���Կؼ����г�ʼ��
		ReflectionUtil.initView(this);
		week = new TextView[]{monday, tuesday, wednesday, thursday, friday, saturday, sunday};
	}
	
	public void onClick(View view){
		Toast.makeText(this, week[i%7].getText().toString(), Toast.LENGTH_LONG).show();
		i++;
		
	}
}
