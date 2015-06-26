package com.singletop.cn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TwoActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two);
		findViewById(R.id.singtoptwo).setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,TZ0606Activity.class));
	}
	

	
}
