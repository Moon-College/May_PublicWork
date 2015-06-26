package com.cn.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener {
	private TextView tv;
	private  MyLineLayout lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv.setOnTouchListener(this);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stubge
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("INFO", "lv down");

			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("INFO", "lv move");
		default:
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "lv up");
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("INFO", "tv down");

			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("INFO", "tv move");
		default:
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "tv up");
			break;
		}

		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		event.startTracking();
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		return super.onKeyLongPress(keyCode, event);
	}

}
