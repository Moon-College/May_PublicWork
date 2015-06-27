package com.vincen.event_delivery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener,OnClickListener {
	private LinearLayout layout;
	private TextView tv;
	private Button btn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		layout = (LinearLayout) findViewById(R.id.layout);
		tv = (TextView) findViewById(R.id.tv);
		btn = (Button) findViewById(R.id.btn);
		layout.setOnTouchListener(new MyTouchListener());
		tv.setOnTouchListener(this);
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.v("tv", "down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.v("tv", "move");
			break;
		case MotionEvent.ACTION_UP:
			Log.v("tv", "up");
			break;
		default:
			break;
		}
		return true;
	}
	
	class MyTouchListener implements OnTouchListener{
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.v("tv", "layout down");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.v("tv", "layout move");
				break;
			case MotionEvent.ACTION_UP:
				Log.v("tv", "layout up");
				break;
			default:
				break;
			}
			return false;
		}
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, TouchEventActivity.class);
		startActivity(intent);
	}

}
