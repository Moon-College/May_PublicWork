package com.zoujm.activity;

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
	
	private TextView tv_one,tv_two,tv_three,tv_four,tv_five;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ReflectionUtil.initView(this, R.id.class);
		
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(MainActivity.this, tv_one.getText(), 1000).show();
	}

	
}
