package com.junwen.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iocdemo.R;
import com.junwen.annotation.IocAnnotation;
import com.junwen.util.IocForAnnotation;

public class MainActivity extends BaseActivity implements OnClickListener {
	@IocAnnotation(R.id.button)
	private Button btn;
	@IocAnnotation(R.id.text)
	private TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//先调用setContentView方法,不然出问题
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);
		initView();
	}
	private void initView() {
		btn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Toast.makeText(this, text.getText().toString(), 0).show();
	}
}
