package com.rocy.tzclass17;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.Resources.Theme;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class RocyLayout extends ViewGroup{
    private int windowWidth ;
    private int windowHeight;
	 
	public RocyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		WindowManager manager =(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(outMetrics );
		windowWidth = outMetrics.widthPixels;
		windowHeight = outMetrics.heightPixels;
	}
	
	

	



	public RocyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}



	public RocyLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 测量
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// 、布局
		int count= this.getChildCount();
		for (int i = 0; i < count; i++) {
			View view = this.getChildAt(i);
			if(view.getVisibility() !=GONE){
			//获得 子控件上的参数
			LayoutParams params =	(LayoutParams) view.getLayoutParams();
				int childHeight = view.getMeasuredHeight();
				int childWidth = view.getMeasuredWidth();
				float x = params.x;
				float y = params.y;
				view.layout((int)(x*windowWidth), (int)(y*windowHeight)
						, (int)(x*windowWidth+childWidth), (int)(y*windowHeight+childHeight));
			}
		}
	}
	
	
	//回调layoutparams
	@Override
	protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
		// TODO Auto-generated method stub
		return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);
	}
	@Override
	public android.view.ViewGroup.LayoutParams generateLayoutParams(
			AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new LayoutParams(getContext(), attrs);
	}
	
	@Override
	protected android.view.ViewGroup.LayoutParams generateLayoutParams(
			android.view.ViewGroup.LayoutParams p) {
		// TODO Auto-generated method stub
		return new LayoutParams(p);
	}
	
	public  static class LayoutParams extends ViewGroup.LayoutParams {
		public float x;
		public float y;

		public LayoutParams(Context c, AttributeSet attrs) {
			super(c, attrs);
			// 通过布局xml获取
			TypedArray array = c.obtainStyledAttributes(attrs,R.styleable.rocyAttr);
		    x = array.getFloat(R.styleable.rocyAttr_x, 0);
		    y = array.getFloat(R.styleable.rocyAttr_y, 0);
		    //回收
		    array.recycle();
		}

		public LayoutParams(int width, int height ,float x , float y) {
			super(width, height);
			// 通过代码获得x,y
			this.x =x;
			this.y =y;
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams source) {
			super(source);
			// TODO Auto-generated constructor stub
		}
		
		
		
	}

}
