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
	 * ��ȡ�˵��Ŀ��
	 * @return �˵��Ŀ��
	 */
	public float getMenuWidth() {
		return mMenuWidth;
	}

	/**
	 * ��ȡcontent�Ŀ��
	 * @return content�Ŀ��
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
		 * ��ȡ���ڵĿ�ȣ�����mMenuWidth��mContentWidth�Ĵ�С
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
		 * ��ȡ��һchild----DecentSlidingBarView����ĵ�һ���LinearLayout
		 * Ȼ��ͨ�����linearLayoutȥ�ҵ�������ImageView:mMenuView��mContenView
		 * 
		 */
		LinearLayout childView = (LinearLayout)getChildAt(0);
	    mMenuView = (ImageView) childView.getChildAt(0);
	    mContenView = (ImageView) childView.getChildAt(1);
		/*
		 * �������� view�Ŀ��
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
		 * l��ʾ����ˮƽ���򻬶��˶�Զ�����ݻ��˶�Զ��������animation��
		 * ͸����(alpha)����С(scale)��ƫ����(translation)
		 */
		Log.d(TAG,"into onScrollChanged "+",l="+l+",t="+t);
		/*
		 * ����mMenuView��͸����(alpha)����С(scale)��ƫ����(translation)��
		 * mMenuView alpha 1~0.8
		 * mMenuView scale 1~0.8
		 * 
		 * 
		 * mMenuView translationX 0.2*l
		 * ��������󻬶�������΢����ƫ�ƣ���content��һ���ص��ĸо�
		 * ��������һ���������΢һ�������򣨵��Ǽ��ٶȼ��٣��ĸо�
		 * ���l==0��˵����Ҫ��ȫչʾmMenuView����ƫ��
		 */
		float factor = l/mMenuWidth;
		ViewHelper.setAlpha(mMenuView, (float)(1-0.2*factor));
		ViewHelper.setScaleX(mMenuView, (float)(1-0.2*factor));
		ViewHelper.setScaleY(mMenuView, (float)(1-0.2*factor));
		ViewHelper.setTranslationX(mMenuView, (float)(1-0.2*factor));
		//ȫ����ʾ��ʱ��l��0��ȫ���رյ�ʱ��l=mMenuWidth
		ViewHelper.setTranslationX(mMenuView, (float)(0.2*l));
		
		/*
		 * ����mContenView��͸����(alpha)����С(scale)��ƫ����(translation)��
		 * mContenView alpha 0.8~1
		 * mContenView scale 0.9~1
		 * 
		 * mContenView translationX 0.08*(l-mMenuWidth) 
		 * ��������һ������Կ�һ��HorizontalSrollView�����ҵĻ�������������mMenuView��һ���ص�Ч��
		 * ��������󻬶�������΢����һ����������ƣ����Ǽ��ٶȼ�С��
		 * ��mMenuWidth==l��ʱ��˵����Ҫ��ȫչʾmContenView������ƫ��
		 */
		ViewHelper.setAlpha(mContenView, (float)(0.2*factor+0.8));
		ViewHelper.setScaleX(mContenView,  (float)(0.1*factor+0.9));
		ViewHelper.setScaleY(mContenView,  (float)(0.1*factor+0.9));
		ViewHelper.setTranslationX(mContenView, (float)(0.09*(l-mMenuWidth)));
	}

}
