package com.qfx.reflectionfindview;

import com.qfx.reflectionfindview.util.ReflectionUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private TextView tv_one, tv_two, tv_three, tv_for, tv_five;
	private Button btn_one;
	private EditText et_one;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflectionUtil.initViews(this);
		btn_one.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		String text = et_one.getText().toString();
		Toast.makeText(this, "通过反射成功findViewById:  " + text, 1000).show();
	}
}
