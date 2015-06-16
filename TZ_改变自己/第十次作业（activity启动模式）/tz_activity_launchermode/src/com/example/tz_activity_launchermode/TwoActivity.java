package com.example.tz_activity_launchermode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TwoActivity extends Activity{
	private Button button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two);
		Log.e("wdj","onCreate instance" + TwoActivity.this);
		button2 = (Button) findViewById(R.id.button2);
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TwoActivity.this,ThirdActivity.class);
				startActivity(intent);
			}
		});
	}
}
