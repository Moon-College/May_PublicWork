package com.tz.michael.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends Activity {

	private final String TAG="ThirdActivity";
	private TextView tv_flag;
	private Button btn_count;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_ac);
		tv_flag=(TextView) findViewById(R.id.tv_flag);
		btn_count=(Button) findViewById(R.id.btn_count);
        tv_flag.setText(TAG);
        Log.i(TAG, "查看我打印了几遍");
	}
	
	public void myClick(View v){
    	switch (v.getId()) {
		case R.id.btn_count:
			startActivity(new Intent(this,ThirdActivity.class));
			break;
		case R.id.btn_jump:
			startActivity(new Intent(this,SecondActivity.class));
			break;
		default:
			break;
		}
    }
	
}
