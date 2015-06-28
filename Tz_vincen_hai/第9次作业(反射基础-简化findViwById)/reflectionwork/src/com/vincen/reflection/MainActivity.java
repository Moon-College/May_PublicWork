package com.vincen.reflection;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	private TextView tv1,tv2,tv3;
	private Button btn1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ReflectionUtil.initView(this);
		
		btn1.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		Toast.makeText(this, tv1.getText(), Toast.LENGTH_LONG).show();
	}

}
