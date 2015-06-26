package com.cn.test;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.cn.test.utils.Reflection;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created on2015-6-19 上午9:23:54 WeixinActivity.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class WeixinActivity extends Activity implements OnTouchListener,
		OnClickListener {
	private LinearLayout ly;
	private RelativeLayout re;
	private float downY;
	private float fingerRoll;
	private float viewRoll;
	private float mChatOriginY;
	private int limit = 230;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weixi_main);
		Reflection.initView(this);
		re.setOnTouchListener(this);
		ly.setOnClickListener(this);

	}

	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = event.getY();
			break;
		// 手指移动的过程中，消息列表跟随移动
		case MotionEvent.ACTION_MOVE:
			// 消息列表移动的距离=手指移动距离*0.6
			fingerRoll = event.getY() - downY;
			// 手指网上滑动，或者没有滑动距离
			if (fingerRoll <= 0) {
				break;
			}
			viewRoll = fingerRoll * 0.6f;
			// 属性动画
			// 跟用户操作相关联
			ViewHelper.setTranslationY(re, viewRoll);

			break;
		// 松开手指
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			fingerRoll = event.getY() - downY;
			viewRoll = fingerRoll * 0.6f;
			// 记录下手指松开时，消息列表的Y坐标
			mChatOriginY = re.getY();
			if (viewRoll < limit) {
				// 消息列表滑动的距离小于限定值，滑回去
				// 否则，直接滑到底部（作业）
				// 花了500毫秒完成了，0到500的过程
				// 当值为250，动画完成的进度时，0.5
				// 动画执行的进度，跟消息列表滑动的进度保持一致
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1)
						.setDuration(500);
				// 开始动画
				valueAnimator.start();
				// 这个动画要完成，消息列表滑动到初始位置这个过程
				valueAnimator
						.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

							@Override
							public void onAnimationUpdate(ValueAnimator va) {
								// 执行进度
								float rate = Float.parseFloat(va
										.getAnimatedValue().toString());
								// 消息列表的y坐标=松开手指时消息列表的Y坐标+(动画进度*消息列表滑动的总距离)
								// 2.2 解决setY
								// 往上滑动，Y坐标变小
								float y = mChatOriginY - (rate * viewRoll);
								re.setY(y);

							}
						});
			} else {
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1)
						.setDuration(500);
				valueAnimator.start();
				valueAnimator
						.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

							public void onAnimationUpdate(
									ValueAnimator animation) {
								float progress = Float.parseFloat(animation
										.getAnimatedValue().toString());
								float y = mChatOriginY
										+ (progress * (re.getHeight() - mChatOriginY));
								re.setY(y);
							}
						});

			}
		}	
		return false;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1).setDuration(
				500);
		valueAnimator.start();
		valueAnimator
				.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

					public void onAnimationUpdate(ValueAnimator animation) {
						// TODO Auto-generated method stub
						float rate = Float.parseFloat(animation
								.getAnimatedValue().toString());
						float y = re.getHeight() - (rate * re.getHeight());
						re.setY(y);
					}
				});
	}

}
