package com.weixin;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dragandball.R;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

public class WenXinActivity extends Activity implements OnTouchListener {

	private RelativeLayout mChat;
	private LinearLayout root;
	private float mCurrentY;
	private float mMoveDis;
	private float mNeedMoveDis;
	private float mUpY;
	private int limit = 250;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//消息列表布局
		root = (LinearLayout) findViewById(R.id.root);
		mChat = (RelativeLayout) findViewById(R.id.rl2);
		mChat.setOnTouchListener(this);
		
		root.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);
				valueAnimator.start();
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						float progress = Float.parseFloat(animation.getAnimatedValue().toString());
						float y = mChat.getHeight() - (progress * mChat.getHeight());
						mChat.setY(y);
					}
				});
			}
		});
	}

	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//滑动距离的限定范围
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mCurrentY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			mMoveDis = event.getY() - mCurrentY;
			if (mMoveDis <= 0) {
				break;
			}
			mNeedMoveDis = mMoveDis * 0.6f;
			ViewHelper.setTranslationY(mChat, mNeedMoveDis);
			break;
			
		case MotionEvent.ACTION_CANCEL:
			//松开手指
		case MotionEvent.ACTION_UP:
			mMoveDis = event.getY() - mCurrentY;
			mNeedMoveDis = mMoveDis * 0.6f;
			//松开手指时消息列表的Y坐标
			mUpY = mChat.getY();
			//mChat.getLocationOnScreen(location);
			
			if (mNeedMoveDis < limit) {
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);
				valueAnimator.start();
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator va) {
						float progress = Float.parseFloat(va.getAnimatedValue().toString());
						float y = mUpY - (progress * mNeedMoveDis);
						mChat.setY(y);
					}
				});
			} else {
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);
				valueAnimator.start();
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						float progress = Float.parseFloat(animation.getAnimatedValue().toString());
						float y = mUpY + (progress * (mChat.getHeight() - mUpY));
						mChat.setY(y);
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
