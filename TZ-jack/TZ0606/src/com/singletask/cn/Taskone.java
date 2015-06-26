package com.singletask.cn;

import com.singletop.cn.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Taskone extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViewById(R.id.singtopbutton0ne).setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, TaskTwo.class));
		
	}

	
	
}
