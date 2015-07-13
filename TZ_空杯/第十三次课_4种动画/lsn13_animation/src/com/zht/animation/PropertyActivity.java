/**
 * Project Name:lsn13_animation
 * File Name:PropertyActivity.java
 * Package Name:com.zht.animation
 * Date:2015-6-23下午5:41:11
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * ClassName:PropertyActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-23 下午5:41:11 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class PropertyActivity extends Activity implements OnTouchListener {
	private RelativeLayout mChat;
	private float downY;
	private float fingerRoll;
	private float viewRoll;
	private ImageView ivEye;
	private float mChatOriginY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.property);
		//消息列表布局
		mChat = (RelativeLayout) findViewById(R.id.rl2);
		mChat.setOnTouchListener(this);
		
		ivEye = (ImageView) findViewById(R.id.iv_eye);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//滑动距离的限定范围
		int limit = ivEye.getHeight() + 100;
		switch (event.getAction()) {
		//获取手指按下时的坐标
		case MotionEvent.ACTION_DOWN:
			downY = event.getY();
			break;
		//手指移动过程中，消息列表跟随移动
		case MotionEvent.ACTION_MOVE:
			//手指滑动的距离
			fingerRoll = event.getY() - downY;
			if (fingerRoll <= 0) {
				break;
			}
			//计算消息列表滑动的距离（减低频率）
			viewRoll = fingerRoll * 0.6f;
			ViewHelper.setTranslationY(mChat, viewRoll);
			
			break;
			
		case MotionEvent.ACTION_CANCEL:
			//松开手指
		case MotionEvent.ACTION_UP:
			fingerRoll = event.getY() - downY;
			viewRoll = fingerRoll * 0.6f;
			//松开手指时消息列表的Y坐标
			mChatOriginY = mChat.getY();
			//mChat.getLocationOnScreen(location);
			
			//消息列表滑动的距离小于限定值，滑回去
			if (viewRoll < limit) {
				//属性动画
				//花了500毫秒执行一个动画，值0变成了1
				//值的范围，取决于你用它做什么
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);
				//开始动画
				valueAnimator.start();
				//监听变化过程
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					//500毫秒中，onAnimationUpdate方法会被反复的调用
					@Override
					public void onAnimationUpdate(ValueAnimator va) {
						//获取动画执行的进度
						float progress = Float.parseFloat(va.getAnimatedValue().toString());
						//消息列表的Y坐标
						//mChatOriginY 松开手指时的Y坐标
						float y = mChatOriginY - (progress * viewRoll);
						mChat.setY(y);
						//动画完成
						/*if (progress == 1) {
							
						}*/
					}
				});
			}else{//否则，直接滑动到底部
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);
				valueAnimator.start();
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						//作业
						//float progress = Float.parseFloat(va.getAnimatedValue().toString());
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
