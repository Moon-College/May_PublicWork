/**
 * Project Name:lsn13_animation
 * File Name:PropertyActivity.java
 * Package Name:com.zht.animation
 * Date:2015-6-23����5:41:11
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
 * Date: 2015-6-23 ����5:41:11 <br/>
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
		//��Ϣ�б���
		mChat = (RelativeLayout) findViewById(R.id.rl2);
		mChat.setOnTouchListener(this);
		
		ivEye = (ImageView) findViewById(R.id.iv_eye);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//����������޶���Χ
		int limit = ivEye.getHeight() + 100;
		switch (event.getAction()) {
		//��ȡ��ָ����ʱ������
		case MotionEvent.ACTION_DOWN:
			downY = event.getY();
			break;
		//��ָ�ƶ������У���Ϣ�б�����ƶ�
		case MotionEvent.ACTION_MOVE:
			//��ָ�����ľ���
			fingerRoll = event.getY() - downY;
			if (fingerRoll <= 0) {
				break;
			}
			//������Ϣ�б����ľ��루����Ƶ�ʣ�
			viewRoll = fingerRoll * 0.6f;
			ViewHelper.setTranslationY(mChat, viewRoll);
			
			break;
			
		case MotionEvent.ACTION_CANCEL:
			//�ɿ���ָ
		case MotionEvent.ACTION_UP:
			fingerRoll = event.getY() - downY;
			viewRoll = fingerRoll * 0.6f;
			//�ɿ���ָʱ��Ϣ�б��Y����
			mChatOriginY = mChat.getY();
			//mChat.getLocationOnScreen(location);
			
			//��Ϣ�б����ľ���С���޶�ֵ������ȥ
			if (viewRoll < limit) {
				//���Զ���
				//����500����ִ��һ��������ֵ0�����1
				//ֵ�ķ�Χ��ȡ������������ʲô
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);
				//��ʼ����
				valueAnimator.start();
				//�����仯����
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					//500�����У�onAnimationUpdate�����ᱻ�����ĵ���
					@Override
					public void onAnimationUpdate(ValueAnimator va) {
						//��ȡ����ִ�еĽ���
						float progress = Float.parseFloat(va.getAnimatedValue().toString());
						//��Ϣ�б��Y����
						//mChatOriginY �ɿ���ָʱ��Y����
						float y = mChatOriginY - (progress * viewRoll);
						mChat.setY(y);
						//�������
						/*if (progress == 1) {
							
						}*/
					}
				});
			}else{//����ֱ�ӻ������ײ�
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);
				valueAnimator.start();
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						//��ҵ
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
