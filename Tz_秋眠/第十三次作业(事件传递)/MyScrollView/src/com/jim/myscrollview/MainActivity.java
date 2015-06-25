package com.jim.myscrollview;

import com.jim.myscrollview.view.MyLinearLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener, OnClickListener {
	/** Called when the activity is first created. */
	private TextView tv;
	private MyLinearLayout layout;
	private Button button;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		tv = (TextView) findViewById(R.id.tvMain);
		tv.setOnTouchListener(this);
		button = (Button) findViewById(R.id.btnMain);
		button.setOnClickListener(this);
		layout = (MyLinearLayout) findViewById(R.id.myLayout);
		layout.setOnTouchListener(new MyTouchListener());
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("INFO", "tv==>>down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("INFO", "tv==>>move");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "tv==>>up");
			break;
		default:
			break;
		}
		return true;
	}

	private class MyTouchListener implements OnTouchListener {

		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.i("INFO", "layout==>>down");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i("INFO", "layout==>>move");
				break;
			case MotionEvent.ACTION_UP:
				Log.i("INFO", "layout==>>up");
				break;
			default:
				break;
			}
			return true;
		}
	}

	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.i("INFO", "btton==>>onClick");
		Intent intent = new Intent(this, MytouchActivity.class);
		startActivity(intent);
	}
}