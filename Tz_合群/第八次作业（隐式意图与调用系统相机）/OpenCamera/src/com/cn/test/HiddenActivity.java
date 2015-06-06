package com.cn.test;

import java.net.DatagramPacket;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;

/**
 * Created on2015-6-4 ионГ8:58:02 HiddenActivity.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class HiddenActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secode);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		DatePicker data=(DatePicker) findViewById(R.id.datePicker);

	}

}
