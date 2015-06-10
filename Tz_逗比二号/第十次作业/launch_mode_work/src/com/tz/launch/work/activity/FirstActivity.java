package com.tz.launch.work.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.launch_mode_work.R;

public class FirstActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_first_activity);
		
		findViewById(R.id.first_layout_btn).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(FirstActivity.this, SecondActivitySingle.class);
				startActivity(intent);
			}
		});
	}
}
