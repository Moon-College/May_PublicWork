package com.example.tz_activity_launchermode;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button singleTopBtn;
	
	private Button jump;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.e("wdj","onCreate instance" + MainActivity.this);
		singleTopBtn = (Button) findViewById(R.id.button);
		jump = (Button) findViewById(R.id.jump);
		singleTopBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
		
		jump.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,TwoActivity.class);
				startActivity(intent);
			}
		});
	}

	

}
