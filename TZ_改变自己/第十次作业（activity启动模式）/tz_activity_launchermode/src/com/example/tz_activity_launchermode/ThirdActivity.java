package com.example.tz_activity_launchermode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThirdActivity extends Activity{
	private Button button3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("wdj","onCreate instance" + ThirdActivity.this);
		setContentView(R.layout.third);
		button3 = (Button) findViewById(R.id.button3);
		
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ThirdActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
