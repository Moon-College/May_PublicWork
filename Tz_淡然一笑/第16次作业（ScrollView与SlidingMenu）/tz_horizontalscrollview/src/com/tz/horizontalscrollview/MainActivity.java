package com.tz.horizontalscrollview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnTouchListener {
	
	private LinearLayout ll_one, ll_two, ll_three;
	private int width, height;  //屏幕宽和高
	private HorizontalScrollView hsv;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			int scrollDx = (int) msg.obj;
			hsv.smoothScrollTo(scrollDx, 0);
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//获取屏幕的宽和高
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		height = dm.heightPixels;
		
		initView();
	}

	private void initView() {
		ll_one = (LinearLayout) findViewById(R.id.ll_one);
		ll_two = (LinearLayout) findViewById(R.id.ll_two);
		ll_three = (LinearLayout) findViewById(R.id.ll_three);
		
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		hsv.setOnTouchListener(this);
		
//		ll_one.setMinimumWidth(300);  //设置最小宽度
		
		//ll_one设置0.8屏幕宽度
		ll_one.setMinimumWidth((int) (width*0.8f));
		//ll_two设置1.0屏幕宽度
		ll_two.setMinimumWidth(width);
		//ll_three设置1.0屏幕宽度
		ll_three.setMinimumWidth(width);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "up");
			//指定滑动到某个位置【记录】
			int scrollx = hsv.getScrollX(); 
			//x方向滑动的位移
			int scrollDx; 
			if(scrollx<=0.4*width){
				//滑到第一页 
				scrollDx = 0;
			}else if(scrollx>0.4*width && scrollx<=1.3*width){
				//滑到第二页
				scrollDx = (int) (0.8f*width);
			}else{
				//滑到第三页
				scrollDx = (int) (1.8f*width);
			}
			Message msg = handler.obtainMessage();
			msg.obj = scrollDx;
			msg.sendToTarget();
			
//			hsv.scrollTo(scrollDx, 0);  //立刻划过去
//			hsv.smoothScrollTo(scrollDx, 0); //频繁的滑，缓慢的滑动
			break;
		default:
			break;
		}
		return false;
	}
	
	
}
