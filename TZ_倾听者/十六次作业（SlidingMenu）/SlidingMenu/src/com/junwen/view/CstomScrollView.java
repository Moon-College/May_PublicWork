package com.junwen.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;

public class CstomScrollView extends HorizontalScrollView {

	private int screenWidth; // ��Ļ���
	private int mMenuWidth; // �˵��Ŀ��
	private int mMainWidhth; // ��ҳ��Ŀ��
	private RelativeLayout mMenu; // �˵�
	private View mMain; // ��ҳ��
	private Handler handler = new Handler() { // ����Handel��ִ�лص�����
		/**
		 * Handel������Ϣ
		 */
		public void handleMessage(android.os.Message msg) {

			int scroll = (Integer) msg.obj;
			// ִ�ж���
			CstomScrollView.this.smoothScrollTo(scroll, 0);
		};
	};

	public CstomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// ��ȡ��Ļ���
		screenWidth = getScreenWidth(context);
	}

	/**
	 * ���ƴ�С
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		// ��ȡ�˵�����ҳ��Ŀ��
		mMenuWidth = (int) (screenWidth * 0.7);
		mMainWidhth = screenWidth;

		// ��ȡ�˵�����ҳ��
		LinearLayout linear = (LinearLayout) getChildAt(0);
		mMenu = (RelativeLayout) linear.getChildAt(0);
		mMain = linear.getChildAt(1);

		// ���ò˵�����ҳ��Ŀ�
		mMenu.getLayoutParams().width = mMenuWidth;
		mMain.getLayoutParams().width = mMainWidhth;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	/**
	 * ����ؼ���λ��
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		// ���ÿ�ʼ��λ��
		this.smoothScrollTo(mMenuWidth, 0);
	}

	/**
	 * ��ScrollView�����仯��ʱ��
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		
		// ����
		float scale = (float) l / mMenuWidth;
		
		// ���1-0�ľ��붯��
		float leftTranslate = l * 0.8f;
		float leftApple = 1 - scale * 0.2f;
		float leftScale = 1 - scale * 0.4f;
		float rightScale = 0.8f + scale * 0.2f;
		
		// ƽ��
		ViewHelper.setTranslationX(mMenu, leftTranslate);
		
		// �˵�����
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		
		// ͸����
		ViewHelper.setAlpha(mMenu, leftApple);
		
		// ��ҳ������
		ViewHelper.setScaleX(mMain, rightScale);
		ViewHelper.setScaleY(mMain, rightScale);
		
	}

	/**
	 * ��ȡ��Ļ���
	 * 
	 * @param context
	 */
	private int getScreenWidth(Context context) {
		//��ȡ��Ļ���
		WindowManager window = (WindowManager) context
		.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		window.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
		
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
			
			// ��ȡ��ǰScrollView��������λ��
			float scaleX = this.getScrollX();
			int scrollInt = 0;
			
			// ���С�ڲ˵���һ��
			if (scaleX < mMainWidhth * 0.45) {
				scrollInt = 0;
				// ������ڲ˵���һ��
			} else if (scaleX > mMainWidhth * 0.4) {
				scrollInt = mMenuWidth;
			}
			
			// ����Handle������Ϣ��ִ��λ���ƶ�����ΪScrollView�ƶ�Ҳ����Handle������������Handel����ScrollView����֮�󣬲�ִ��
			Message msg = handler.obtainMessage();
			msg.obj = scrollInt;
			handler.sendMessage(msg);
			
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

}
