package com.dd.launchmodehomework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class FourthActivitySingle extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("home", "four----"+this);
		setContentView(R.layout.layout_fourth_activity);

		findViewById(R.id.first_layout_btn).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(FourthActivitySingle.this,
								FifthActivity.class);
						startActivity(intent);
					}
				});
	}
	@Override
	protected void onResume() {
		Log.v("home", "r--444---"+this);
		super.onResume();
	}
}
