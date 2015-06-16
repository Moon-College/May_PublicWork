package com.tz.michael.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {

	private final String TAG="SecondActivity";
	private TextView tv_flag;
	private Button btn_count;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv_flag=(TextView) findViewById(R.id.tv_flag);
		btn_count=(Button) findViewById(R.id.btn_count);
        tv_flag.setText(TAG);
	}
	
	 public void myClick(View v){
	    	startActivity(new Intent(this,ThirdActivity.class));
	    }
	
}
