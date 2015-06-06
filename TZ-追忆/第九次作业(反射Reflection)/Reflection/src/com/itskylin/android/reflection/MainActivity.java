package com.itskylin.android.reflection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itskylin.android.reflection.utils.ReflectionUtil;

public class MainActivity extends Activity {

	private TextView tv_1, tv_2, tv_3, tv_4, tv_5;
	private Button bt_1;
	private String sfaw, bgaelkbne;
	private int aowengw, fwagw, wegwegw;
	private boolean fd, sfew, gwe, htehe, e;
	private long gagw, gweg, wegwe, rgtrh, weg, w;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ReflectionUtil.initView(this);
	}

	public void click(View v) {
		Toast.makeText(this, bt_1.getText(), Toast.LENGTH_SHORT).show();
	}
}