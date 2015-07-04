/**
 * Project Name:lsn19_custom_container
 * File Name:CustomLayout.java
 * Package Name:com.zht.customcontainer
 * Date:2015-6-30����6:38:13
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.customlayout;

import com.zht.customlayout.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * ClassName:CustomLayout <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-30 ����6:38:13 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class CustomLayout extends ViewGroup {

	public CustomLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CustomLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	// �����Լ�
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// ��֧��wrap_content
		measureChildren(widthMeasureSpec, heightMeasureSpec);// �����ӿؼ����
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	// �����ӿؼ���λ��
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// �õ��ӿؼ���һ����
		for (int i = 0; i < this.getChildCount(); i++) {
			View child = this.getChildAt(i);
			if (child.getVisibility() != GONE) {
				CustomLayout.LayoutParams clp = (LayoutParams) child.getLayoutParams();
				//ȷ��ÿ���ӿؼ�����������
				int left = (int) (clp.px*this.getMeasuredWidth()+this.getPaddingLeft());
				int top = (int) (clp.py*this.getMeasuredHeight()+this.getPaddingTop());
				int right = left + child.getMeasuredWidth();
				int bottom = top+child.getMeasuredHeight();
				child.layout(left, top, right, bottom);
			}
		}

	}
	//�������������Ǹ�����������LayoutParams��
	@Override
	public android.view.ViewGroup.LayoutParams generateLayoutParams(
			AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new CustomLayout.LayoutParams(getContext(), attrs);
	}
	
	@Override
	protected android.view.ViewGroup.LayoutParams generateLayoutParams(
			android.view.ViewGroup.LayoutParams p) {
		// TODO Auto-generated method stub
		return new CustomLayout.LayoutParams(p);
	}
	//��̬java����
	@Override
	protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
		// TODO Auto-generated method stub
		return new CustomLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);
	}
	
    //����ڸ�����������ͨ��LayoutParams������
	public static class LayoutParams extends ViewGroup.LayoutParams {
		float px, py;

		public LayoutParams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);
			// ͨ��TypedArray����xml������ԣ����Զ���������ļ���
			TypedArray a = arg0.obtainStyledAttributes(arg1,
					R.styleable.customlayout);
			// ����px py
			this.px = a.getFloat(R.styleable.customlayout_px, 0);
			this.py = a.getFloat(R.styleable.customlayout_py, 0);
			a.recycle();
		}

		public LayoutParams(int arg0, int arg1, float px, float py) {
			super(arg0, arg1);
			this.px = px;
			this.py = py;
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}

	}

}
