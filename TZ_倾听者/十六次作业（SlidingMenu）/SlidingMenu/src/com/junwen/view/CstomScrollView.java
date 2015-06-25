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

	private int screenWidth; // 屏幕宽度
	private int mMenuWidth; // 菜单的宽度
	private int mMainWidhth; // 主页面的宽度
	private RelativeLayout mMenu; // 菜单
	private View mMain; // 主页面
	private Handler handler = new Handler() { // 声明Handel来执行回弹动画
		/**
		 * Handel处理消息
		 */
		public void handleMessage(android.os.Message msg) {

			int scroll = (Integer) msg.obj;
			// 执行动画
			CstomScrollView.this.smoothScrollTo(scroll, 0);
		};
	};

	public CstomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 获取屏幕宽度
		screenWidth = getScreenWidth(context);
	}

	/**
	 * 控制大小
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		// 获取菜单和主页面的宽度
		mMenuWidth = (int) (screenWidth * 0.7);
		mMainWidhth = screenWidth;

		// 获取菜单和主页面
		LinearLayout linear = (LinearLayout) getChildAt(0);
		mMenu = (RelativeLayout) linear.getChildAt(0);
		mMain = linear.getChildAt(1);

		// 设置菜单和主页面的宽
		mMenu.getLayoutParams().width = mMenuWidth;
		mMain.getLayoutParams().width = mMainWidhth;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	/**
	 * 处理控件的位置
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		// 设置开始的位置
		this.smoothScrollTo(mMenuWidth, 0);
	}

	/**
	 * 当ScrollView滑动变化的时候
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		
		// 进度
		float scale = (float) l / mMenuWidth;
		
		// 求出1-0的距离动画
		float leftTranslate = l * 0.8f;
		float leftApple = 1 - scale * 0.2f;
		float leftScale = 1 - scale * 0.4f;
		float rightScale = 0.8f + scale * 0.2f;
		
		// 平移
		ViewHelper.setTranslationX(mMenu, leftTranslate);
		
		// 菜单缩放
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		
		// 透明度
		ViewHelper.setAlpha(mMenu, leftApple);
		
		// 主页面缩放
		ViewHelper.setScaleX(mMain, rightScale);
		ViewHelper.setScaleY(mMain, rightScale);
		
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @param context
	 */
	private int getScreenWidth(Context context) {
		//获取屏幕宽度
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
			
			// 获取当前ScrollView滑动到的位置
			float scaleX = this.getScrollX();
			int scrollInt = 0;
			
			// 如果小于菜单的一半
			if (scaleX < mMainWidhth * 0.45) {
				scrollInt = 0;
				// 如果大于菜单的一半
			} else if (scaleX > mMainWidhth * 0.4) {
				scrollInt = mMenuWidth;
			}
			
			// 采用Handle发送消息，执行位置移动，因为ScrollView移动也是用Handle，所以这里用Handel会在ScrollView滑动之后，才执行
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
