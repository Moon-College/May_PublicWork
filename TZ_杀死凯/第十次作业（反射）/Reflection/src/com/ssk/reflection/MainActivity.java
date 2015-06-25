package com.ssk.reflection;

import com.ssk.reflection.utils.ReflectionUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ReflectionUtils.initViews(this);
		tv_1.setText("反射成功666");
	}

}
