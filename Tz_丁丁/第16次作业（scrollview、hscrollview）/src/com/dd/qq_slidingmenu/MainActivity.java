package com.dd.qq_slidingmenu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements OnTouchListener{
	private MySlidingMenu msm;
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int i = (Integer) msg.obj;
			msm.smoothScrollTo(i, 0);
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		msm =(MySlidingMenu) findViewById(R.id.slidingmenu);
		msm.setOnTouchListener(this);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		msm.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			int nowScrollX = msm.getScrollX();
			int i=0;
			if (msm.getmMenuWidth() > nowScrollX
					&& nowScrollX > msm.getmMenuWidth() / 2) {
				i=msm.getmMenuWidth();
			}
			Message obtainMessage = handler.obtainMessage(0, i);
			handler.sendMessage(obtainMessage);
			break;
		default:
			break;
		}
		return true;
	}
}
