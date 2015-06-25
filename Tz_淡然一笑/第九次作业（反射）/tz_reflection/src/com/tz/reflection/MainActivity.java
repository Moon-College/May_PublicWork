package com.tz.reflection;

import com.tz.reflection.utils.ReflectionUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private TextView tv_one,tv_two,tv_three,tv_four,tv_five,tv_six;
	private EditText et_input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ReflectionUtils.initViews(this);
	}
	
	public void show(View view){
//		Toast.makeText(this, tv_one.getText(), Toast.LENGTH_LONG).show();
		Toast.makeText(this, et_input.getText(), Toast.LENGTH_LONG).show();
	}
	
}
