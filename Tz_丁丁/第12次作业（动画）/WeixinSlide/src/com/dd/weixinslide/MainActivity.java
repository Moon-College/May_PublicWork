package com.dd.weixinslide;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnTouchListener {
	private RelativeLayout mChat;
	private ImageView ivEye;
	private float downY;
	private float fingerRoll;
	private float viewRoll;
	private float mChatOriginY;
	private int screenHeight;
//	private ;	
//	private ;	
//	private ;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 消息列表布局
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();
		mChat = (RelativeLayout) findViewById(R.id.rl2);
		mChat.setOnTouchListener(this);

		ivEye = (ImageView) findViewById(R.id.iv_eye);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int limit = ivEye.getHeight() + 100;
		switch (event.getAction()) {
		//获取手指按下时的坐标
		case MotionEvent.ACTION_DOWN:
			downY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			fingerRoll = event.getY() - downY;
			if (fingerRoll <= 0 ) {
				break;
			}
			viewRoll = fingerRoll * 0.6f;
			ViewHelper.setTranslationY(mChat, viewRoll);
			break;
		case MotionEvent.ACTION_UP:
			fingerRoll = event.getY() - downY;
			viewRoll = fingerRoll * 0.6f;
			mChatOriginY = mChat.getY();
			
			Log.v("home", screenHeight+"屏幕高");
			if (viewRoll < limit){
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);
				valueAnimator.start();
				
				valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator va) {
						float progress = Float.parseFloat(va.getAnimatedValue().toString());
						float y = mChatOriginY - (progress*viewRoll);
						mChat.setY(y);
					}
				});
			} else {
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(5000);
				valueAnimator.start();
				valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator va) {
						float progress = Float.parseFloat(va.getAnimatedValue().toString());
						int[] location = new  int[2] ;
						mChat.getLocationOnScreen(location);
						//屏幕高度-消息列表绝对y坐标
						
//						float y1 =location[1]-mChatOriginY*progress;
						float y2 =screenHeight-location[1]*(1-progress);
//						float y = mChatOriginY - (progress*viewRoll);
						mChat.setY(y2);
					}
				});
			}
			break;
		default:
			break;
		}
		
		return true;
	}

}
