package com.zoujm.activity;

import com.zoujm.inject.InjectView;
import com.zoujm.utils.AutoInjectUtil;
import com.zoujm.utils.ReflectionUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	@InjectView(id=R.id.tv_one)
	private TextView tv_one;
	@InjectView(id=R.id.tv_two)
	private TextView tv_two;
	@InjectView(id=R.id.tv_three)
	private TextView tv_three;
	@InjectView(id=R.id.tv_four)
	private TextView tv_four;
	@InjectView(id=R.id.tv_five)
	private TextView tv_five;
	@InjectView(id=R.id.btn)
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		ReflectionUtil.initView(this, R.id.class);
		try {
			AutoInjectUtil.autoInjectAllView(this);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(MainActivity.this, tv_one.getText(), 1000).show();
	}

	
}
