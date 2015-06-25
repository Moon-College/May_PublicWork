package com.decent.slidingmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

public class DecentSlidingBarView extends HorizontalScrollView {

	private static final String TAG = "DecentSlidingBarView";
	private int mWindowWidth;
	private int mWindowHeight;
	private WindowManager mWindowManager;
	private float mMenuWidth;
	private float mContentWidth;
	private ImageView mMenuView;
	private ImageView mContenView;
	private static final float MENU_FACTOR = 0.8f;
	
	public DecentSlidingBarView(Context context) {
		super(context);
		initView(context);				
		// TODO Auto-generated constructor stub
	}

	public DecentSlidingBarView(Context context, AttributeSet attrs) {

		super(context, attrs);
		initView(context);		
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取菜单的宽度
	 * @return 菜单的宽度
	 */
	public float getMenuWidth() {
		return mMenuWidth;
	}

	/**
	 * 获取content的宽度
	 * @return content的宽度
	 */
	public float getContentWidth() {
		return mContentWidth;
	}

	public DecentSlidingBarView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	private void initView(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		/*
		 * 获取窗口的宽度，计算mMenuWidth和mContentWidth的大小
		 */
		mWindowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		mWindowManager.getDefaultDisplay().getMetrics(metric);
		mWindowWidth = metric.widthPixels;
		mWindowHeight = metric.heightPixels;

		mMenuWidth = MENU_FACTOR * mWindowWidth;
		mContentWidth = mWindowWidth;
		Log.d(TAG, "mWindowWidth="+mWindowWidth+",mWindowHeight="+mWindowHeight);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(changed){
             smoothScrollTo((int)(mMenuWidth+mContentWidth), 0);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);			
		/*
		 * 获取第一child----DecentSlidingBarView下面的第一层的LinearLayout
		 * 然后通过这个linearLayout去找到那两个ImageView:mMenuView和mContenView
		 * 
		 */
		LinearLayout childView = (LinearLayout)getChildAt(0);
	    mMenuView = (ImageView) childView.getChildAt(0);
	    mContenView = (ImageView) childView.getChildAt(1);
		/*
		 * 设置两个 view的宽度
		 */
		mMenuView.getLayoutParams().width = (int) mMenuWidth;
		mMenuView.getLayoutParams().height = mWindowHeight;
		mContenView.getLayoutParams().width = (int) mContentWidth;
		mContenView.getLayoutParams().height = mWindowHeight;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		/*
		 * l表示现在水平方向滑动了多远，根据滑了多远，来调整animation的
		 * 透明度(alpha)，大小(scale)，偏移量(translation)
		 */
		Log.d(TAG,"into onScrollChanged "+",l="+l+",t="+t);
		/*
		 * 更新mMenuView的透明度(alpha)，大小(scale)，偏移量(translation)等
		 * mMenuView alpha 1~0.8
		 * mMenuView scale 1~0.8
		 * 
		 * 
		 * mMenuView translationX 0.2*l
		 * 如果是向左滑动，则稍微向右偏移，和content有一个重叠的感觉
		 * 如果是向右滑动，有稍微一点点加速向（但是加速度减少）的感觉
		 * 如果l==0则说明需要完全展示mMenuView，无偏移
		 */
		float factor = l/mMenuWidth;
		ViewHelper.setAlpha(mMenuView, (float)(1-0.2*factor));
		ViewHelper.setScaleX(mMenuView, (float)(1-0.2*factor));
		ViewHelper.setScaleY(mMenuView, (float)(1-0.2*factor));
		ViewHelper.setTranslationX(mMenuView, (float)(1-0.2*factor));
		//全部显示的时候l是0，全部关闭的时候l=mMenuWidth
		ViewHelper.setTranslationX(mMenuView, (float)(0.2*l));
		
		/*
		 * 更新mContenView的透明度(alpha)，大小(scale)，偏移量(translation)等
		 * mContenView alpha 0.8~1
		 * mContenView scale 0.9~1
		 * 
		 * mContenView translationX 0.08*(l-mMenuWidth) 
		 * 如果是向右滑动，对抗一下HorizontalSrollView的向右的滑动，并且最后和mMenuView有一点重叠效果
		 * 如果是向左滑动，则稍微加速一下向左的趋势（但是加速度减小）
		 * 当mMenuWidth==l的时候，说明需要完全展示mContenView，则不做偏移
		 */
		ViewHelper.setAlpha(mContenView, (float)(0.2*factor+0.8));
		ViewHelper.setScaleX(mContenView,  (float)(0.1*factor+0.9));
		ViewHelper.setScaleY(mContenView,  (float)(0.1*factor+0.9));
		ViewHelper.setTranslationX(mContenView, (float)(0.09*(l-mMenuWidth)));
	}

}
