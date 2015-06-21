package com.example.tz_key;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener {

	private TextView tv;
	
	private Button btn_two;
	
	private LinearLayout ll;
	
	private MyTouchEvent event;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		ll = (LinearLayout) findViewById(R.id.ll);
		btn_two = (Button) findViewById(R.id.two);
		event = new MyTouchEvent();
		tv.setOnTouchListener(this);
		ll.setOnTouchListener(event);
		btn_two.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,TwoActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private class MyTouchEvent implements OnTouchListener  {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int pointerCount = event.getPointerCount();
			Log.e("wdj","MyTouchEvent onTouch pointerCount = " + pointerCount);
			switch (event.getAction() & event.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
				Log.e("wdj","MyTouchEvent ACTION_DOWN fingerX = " + fingerX + ",fingerY = " + fingerY);
				break ;
			case MotionEvent.ACTION_POINTER_DOWN:
				Log.e("wdj","MyTouchEvent 其他手指陆续按下来");
				float x3 = event.getX(pointerCount - 1);
				float y3 = event.getY(pointerCount - 1);
				break;
			case MotionEvent.ACTION_MOVE:
				fingerX = event.getX();
				fingerY = event.getY(); //相对控件的坐标
				float rawX = event.getRawX();
				float rawY = event.getRawY(); //相对于屏幕的坐标 
				Log.e("wdj","MyTouchEvent ACTION_MOVE fingerX = " + fingerX + ",fingerY = " + fingerY);
				Log.e("wdj","MyTouchEvent ACTION_MOVE rawX = " + rawX + ",rawY = " + rawY);
				break;
			case MotionEvent.ACTION_UP:
				Log.e("wdj","MyTouchEvent ACTION_UP");
				break;
			default:
				break;
			}
			return true;
		}
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.e("wdj","onKeyDown keyCode = " + keyCode);
		return super.onKeyDown(keyCode, event);
		
	}
	 
	//按键弹起来
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		Log.e("wdj","onKeyUp keyCode = " + keyCode);
		return super.onKeyUp(keyCode, event);
	}
	
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		Log.e("wdj","onKeyLongPress keyCode = " + keyCode);
		return super.onKeyLongPress(keyCode, event);
	}

	private float fingerX;
	private float fingerY;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		int pointerCount = event.getPointerCount();
		Log.e("wdj","onTouch pointerCount = " + pointerCount);
		switch (event.getAction() & event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			Log.e("wdj","ACTION_DOWN fingerX = " + fingerX + ",fingerY = " + fingerY);
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			Log.e("wdj","其他手指陆续按下来");
			float x3 = event.getX(pointerCount - 1);
			float y3 = event.getY(pointerCount - 1);
			break;
		case MotionEvent.ACTION_MOVE:
			fingerX = event.getX();
			fingerY = event.getY(); //相对控件的坐标
			float rawX = event.getRawX();
			float rawY = event.getRawY(); //相对于屏幕的坐标 
			Log.e("wdj","ACTION_MOVE fingerX = " + fingerX + ",fingerY = " + fingerY);
			Log.e("wdj","ACTION_MOVE rawX = " + rawX + ",rawY = " + rawY);
			break;
		case MotionEvent.ACTION_UP:
			Log.e("wdj","ACTION_UP");
			break;
		default:
			break;
		}
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.e("wdj","onTouchEvent");
		return true;
	}
	
	
	
	

}
