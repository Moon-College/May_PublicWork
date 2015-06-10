package com.singletask.cn;

import com.singletop.cn.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TaskTwo extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two);
		findViewById(R.id.singtoptwo).setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, Taskone.class));
	}
	

	
}

