package com.tz.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;

public class NoramlActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.normal);
	}
}
