package com.tz.launch.work.activity;

import com.example.launch_mode_work.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class FifthActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_fifth_activity);
		findViewById(R.id.first_layout_btn).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(FifthActivity.this, SecondActivitySingle.class);
				startActivity(intent);
			}
		});
	}
}
