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
 * Created on2015-6-19 ����9:23:54 WeixinActivity.java<br/>
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
		// ��ָ�ƶ��Ĺ����У���Ϣ�б�����ƶ�
		case MotionEvent.ACTION_MOVE:
			// ��Ϣ�б��ƶ��ľ���=��ָ�ƶ�����*0.6
			fingerRoll = event.getY() - downY;
			// ��ָ���ϻ���������û�л�������
			if (fingerRoll <= 0) {
				break;
			}
			viewRoll = fingerRoll * 0.6f;
			// ���Զ���
			// ���û����������
			ViewHelper.setTranslationY(re, viewRoll);

			break;
		// �ɿ���ָ
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			fingerRoll = event.getY() - downY;
			viewRoll = fingerRoll * 0.6f;
			// ��¼����ָ�ɿ�ʱ����Ϣ�б��Y����
			mChatOriginY = re.getY();
			if (viewRoll < limit) {
				// ��Ϣ�б����ľ���С���޶�ֵ������ȥ
				// ����ֱ�ӻ����ײ�����ҵ��
				// ����500��������ˣ�0��500�Ĺ���
				// ��ֵΪ250��������ɵĽ���ʱ��0.5
				// ����ִ�еĽ��ȣ�����Ϣ�б����Ľ��ȱ���һ��
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1)
						.setDuration(500);
				// ��ʼ����
				valueAnimator.start();
				// �������Ҫ��ɣ���Ϣ�б�������ʼλ���������
				valueAnimator
						.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

							@Override
							public void onAnimationUpdate(ValueAnimator va) {
								// ִ�н���
								float rate = Float.parseFloat(va
										.getAnimatedValue().toString());
								// ��Ϣ�б��y����=�ɿ���ָʱ��Ϣ�б��Y����+(��������*��Ϣ�б������ܾ���)
								// 2.2 ���setY
								// ���ϻ�����Y�����С
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
