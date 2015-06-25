package com.decent.decentanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;

import com.decent.util.ReflictionUtil;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

public class DecentAnimationActivity extends Activity implements
		OnTouchListener {
	private RelativeLayout chartRl;
	/**
	 * 手指按下时候的y坐标点
	 */
	private float downY;
	/**
	 * 手指滑动的距离
	 */
	private float fingerRoll;
	/**
	 * chartRl下滑的距离, layoutRoll=FACTOR*fingerRoll
	 */
	private float layoutRoll;
	/**
	 * 手指抬起时，Y坐标的坐标点
	 */
	private float mChatOriginY;
	/**
	 * 这个FACTOR主要是为了有一种滑动过程中，图片在回弹的感觉
	 */
	private static final float FACTOR = 0.6f;
	private static float LIMIT;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflictionUtil.InjectionView(DecentAnimationActivity.class.getName(),
				R.class.getName(), this);
		chartRl.setOnTouchListener(this);
	}

	public boolean onTouch(View v, MotionEvent event) {
		// LIMIT设置为chartRl高度的1/3
		LIMIT = chartRl.getHeight() / 3;
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = ViewHelper.getY(chartRl);
			// downY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			fingerRoll = event.getY() - downY;

			if (fingerRoll < 0) {
				break;
			}
			layoutRoll = fingerRoll * FACTOR;
			ViewHelper.setTranslationY(chartRl, layoutRoll);
			break;
		case MotionEvent.ACTION_UP:
			fingerRoll = event.getY() - downY;
			layoutRoll = fingerRoll * FACTOR;
			mChatOriginY = ViewHelper.getY(chartRl);

			if (layoutRoll < LIMIT) {
				/*
				 * 小于limit滑动回去
				 */
				ValueAnimator valueAnimation = new ValueAnimator()
						.ofFloat(0, 1).setDuration(500);
				valueAnimation.start();
				valueAnimation
						.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

							public void onAnimationUpdate(ValueAnimator arg0) {

								float progress = Float.parseFloat(arg0
										.getAnimatedValue().toString());
								float y = mChatOriginY - progress * layoutRoll;
								ViewHelper.setY(chartRl, y);
							}
						});
			} else {
				/*
				 * >=limit则打开相机
				 */
				//Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				//startActivityForResult(intent, 1);
				Intent intent = new Intent();
				intent.setClass(this, GravityBallActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
		return true;
	}
}