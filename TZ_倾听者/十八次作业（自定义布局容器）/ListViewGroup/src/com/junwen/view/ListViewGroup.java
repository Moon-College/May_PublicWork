package com.junwen.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ListViewGroup extends ViewGroup{

	public ListViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ListViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListViewGroup(Context context) {
		super(context);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	public android.view.ViewGroup.LayoutParams generateLayoutParams(
			AttributeSet attrs) {
		return new ListViewGroup.LayoutParams(getContext(), attrs);
	}
	
	public static class LayoutParams extends ViewGroup.MarginLayoutParams{

		public LayoutParams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);
		}

		public LayoutParams(int arg0, int arg1) {
			super(arg0, arg1);
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams arg0) {
			super(arg0);
		}

		public LayoutParams(MarginLayoutParams arg0) {
			super(arg0);
		}
		
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int left = l;
		int top = t;
		System.out.println(left+"left");
		int groupWidht = this.getMeasuredWidth();
		int childCount = this.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childAt = this.getChildAt(i);
			if(childAt.getVisibility() != GONE){
				int width = childAt.getMeasuredWidth();
				int height = childAt.getMeasuredHeight();
				ListViewGroup.LayoutParams margins = (LayoutParams) childAt.getLayoutParams();
				if(left + width +margins.rightMargin +margins.leftMargin>groupWidht ){
					left = l;
					top += height + margins.topMargin + margins.bottomMargin;
				}
				childAt.layout(left + margins.leftMargin, top + margins.topMargin, left + margins.leftMargin + width, top + margins.topMargin + height);
				left += width + margins.leftMargin + margins.rightMargin;
			}
		}
		
		
		
		
		
//		int mViewGroupWidth  = getMeasuredWidth();  //当前ViewGroup的总宽度       
//
//	    int mPainterPosX = l;  //当前绘图光标横坐标位置
//	    System.out.println("mPainterPosX"+mPainterPosX);
//	    int mPainterPosY = t;  //当前绘图光标纵坐标位置  
//	    
//	    int childCount = getChildCount();	
//	    for ( int i = 0; i < childCount; i++ ) {
//	  
//	  View childView = getChildAt(i);
//
//	  int width  = childView.getMeasuredWidth();
//	  int height = childView.getMeasuredHeight();	     
//	        
//	  //如果剩余的空间不够，则移到下一行开始位置
//	  if( mPainterPosX + width > mViewGroupWidth ) {	      
//	      mPainterPosX = l; 
//	      System.out.println("l"+l);
//	      mPainterPosY += height;
//	  }		    
//	  
//	  //执行ChildView的绘制
//	  childView.layout(mPainterPosX,mPainterPosY,mPainterPosX+width, mPainterPosY+height);
//	  System.out.println(mPainterPosX+"||"+mPainterPosX+"||"+mPainterPosX+width+"||"+mPainterPosY+height);
//	  //记录当前已经绘制到的横坐标位置 
//	  mPainterPosX += width;
//	}

}
}
