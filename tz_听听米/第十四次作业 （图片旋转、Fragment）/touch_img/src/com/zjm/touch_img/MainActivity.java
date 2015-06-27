package com.zjm.touch_img;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.zjm.touch_img.listener.ImgTouchListener;

public class MainActivity extends Activity {

	ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iv = (ImageView) findViewById(R.id.iv);
		iv.setOnTouchListener(new ImgTouchListener(iv));
	}

	

}
