package com.zjm.key;

import com.zjm.key.view.MyLinearLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

@SuppressLint("ClickableViewAccessibility")
public class MainActivity extends Activity implements OnTouchListener {
	
	private MyLinearLayout layout;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	/**
	 * 初始化控件
	 *@author 邹继明
	 *@date 2015-6-16上午1:57:10
	 *@return void
	 */
	private void initView() {
		layout = (MyLinearLayout) findViewById(R.id.layout);
		tv = (TextView) findViewById(R.id.tv);
		tv.setOnTouchListener(this);
		layout.setOnTouchListener(new MyTouchListener());
	}
	
	
	private class MyTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d("TZ", "layout down");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d("TZ", "layout move");
				break;
			case MotionEvent.ACTION_UP:
				Log.d("TZ", "layout up");
				break;

			default:
				break;
			}
			return true;
		}
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d("TZ", "tv down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d("TZ", "tv move");
			break;
		case MotionEvent.ACTION_UP:
			Log.d("TZ", "tv up");
			break;

		default:
			break;
		}
		return true;
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
