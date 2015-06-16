package com.dd.launchmodehomework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class FifthActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("home", "fifth----"+this);
		setContentView(R.layout.layout_fifth_activity);
		findViewById(R.id.first_layout_btn).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(FifthActivity.this,
								SecondActivitySingle.class);
						startActivity(intent);
					}
				});
	}
	@Override
	protected void onResume() {
		Log.v("home", "r--5555---"+this);
		super.onResume();
	}
}
