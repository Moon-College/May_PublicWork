package com.example.tz_frame;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		image = (ImageView) findViewById(R.id.image);
		AnimationDrawable animationDrawable = (AnimationDrawable) image.getDrawable();
		animationDrawable.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
