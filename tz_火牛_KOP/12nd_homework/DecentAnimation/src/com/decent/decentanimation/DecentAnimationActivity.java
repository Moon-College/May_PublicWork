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
	 * ��ָ����ʱ���y�����
	 */
	private float downY;
	/**
	 * ��ָ�����ľ���
	 */
	private float fingerRoll;
	/**
	 * chartRl�»��ľ���, layoutRoll=FACTOR*fingerRoll
	 */
	private float layoutRoll;
	/**
	 * ��ָ̧��ʱ��Y����������
	 */
	private float mChatOriginY;
	/**
	 * ���FACTOR��Ҫ��Ϊ����һ�ֻ��������У�ͼƬ�ڻص��ĸо�
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
		// LIMIT����ΪchartRl�߶ȵ�1/3
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
				 * С��limit������ȥ
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
				 * >=limit������
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