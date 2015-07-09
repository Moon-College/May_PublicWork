package com.vincen.touch_event;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Point;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn1, btn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			Intent intent = new Intent(this, ImageTranslateActivity.class);
			startActivity(intent);
			break;
		case R.id.btn2:
			Intent intent2 = new Intent(this, ImageRorateActivity.class);
			startActivity(intent2);
			break;

		default:
			break;
		}
	}

}
