package com.tz.michael.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LaucherModeTestActivity extends Activity {
	
	private final String TAG="LaucherModeTestActivity";
	private TextView tv_flag;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv_flag=(TextView) findViewById(R.id.tv_flag);
        tv_flag.setText(TAG);
    }
    
    public void myClick(View v){
    	startActivity(new Intent(this,SecondActivity.class));
    }
}