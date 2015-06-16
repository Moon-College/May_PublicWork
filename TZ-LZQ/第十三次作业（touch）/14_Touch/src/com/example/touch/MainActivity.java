package com.example.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener {

	private MyLinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setOnTouchListener(this);
		ll = (MyLinearLayout) findViewById(R.id.ll);
		ll.setOnTouchListener(new MyTouchEvent());
	}

	public class MyTouchEvent implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int action = event.getAction();
			
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				Log.i("sss", "layout down");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i("sss", "layout move");
				break;
			case MotionEvent.ACTION_UP:
				Log.i("sss", "layout up");
				break;

			default:
				break;
			}
			return true;
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.i("sss", "down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("sss", "move");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("sss", "up");
			break;

		default:
			break;
		}

		return true;
//		 return super.onTouchEvent(event);
	}

}
