package com.tz.qqslidingmenu;

import com.tz.qqslidingmenu.view.MySlidingMenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnTouchListener {

	private MySlidingMenu mySlidingMenu;
	private int screenWidth; // 屏幕宽度
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			int scrollDx = (int) msg.obj;
			mySlidingMenu.smoothScrollTo(scrollDx, 0);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {
		// 获取屏幕的宽和高
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		
		mySlidingMenu = (MySlidingMenu) findViewById(R.id.menu);
		mySlidingMenu.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			break;
		case MotionEvent.ACTION_MOVE:

			break;
		case MotionEvent.ACTION_UP:
			//指定滑动到某个位置【记录】
			int scrollx = mySlidingMenu.getScrollX();
			//x方向滑动的位移
			int scrollDx; 
			if(scrollx<=0.4*screenWidth){
				//滑动menu页面
				scrollDx = 0;
			}
			else{
				//滑动content页面
				scrollDx = (int) (0.8f*screenWidth);
			}
			
			Message msg = handler.obtainMessage();
			msg.obj = scrollDx;
			msg.sendToTarget();
			break;

		default:
			break;
		}
		return false;
	}
}
