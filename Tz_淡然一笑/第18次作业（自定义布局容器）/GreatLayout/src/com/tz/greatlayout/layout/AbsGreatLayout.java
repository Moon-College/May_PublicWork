package com.tz.greatlayout.layout;

import com.tz.greatlayout.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class AbsGreatLayout extends ViewGroup{

	public AbsGreatLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public AbsGreatLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AbsGreatLayout(Context context) {
		super(context);
	}

	//�����Լ����
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//��֧��wrap_content
		//�����ӿؼ����
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	//�����ӿؼ���λ��
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		//�ӿؼ�����
		int count = this.getChildCount();
		for(int i = 0;i<count;i++){
			View child = this.getChildAt(i);
			if(child.getVisibility() != GONE){
				AbsGreatLayout.LayoutParams agl = (LayoutParams) child.getLayoutParams();
				//��ȡ�ӿؼ���px,py
				//����px = 0.5ʱ,���� �ؼ�x�������ĵ������Ϊ��Ļx��������ĵ�
				int childLeft = (int) (agl.px*this.getMeasuredWidth()-child.getMeasuredWidth()/2+this.getPaddingLeft());
				int childTop = (int) (agl.py*this.getMeasuredHeight()+this.getPaddingTop());
				int childRight = childLeft + child.getMeasuredWidth();
				int childBottom = childTop + child.getMeasuredHeight();
				child.layout(childLeft, childTop, childRight, childBottom);
			}
		}
	}
	
	@Override
	public android.view.ViewGroup.LayoutParams generateLayoutParams(
			AttributeSet attrs) {
		return new AbsGreatLayout.LayoutParams(getContext(), attrs);
	}
	
	@Override
	protected android.view.ViewGroup.LayoutParams generateLayoutParams(
			android.view.ViewGroup.LayoutParams p) {
		return new AbsGreatLayout.LayoutParams(p);
	}
	
	//��̬java����,Ĭ������px,py
	@Override
	protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
		return new AbsGreatLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);
	}
	
	public static class LayoutParams extends  ViewGroup.LayoutParams{
		
		public float px,py;
		
		public LayoutParams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);
			//ͨ��TypedArray����xml������ԣ����Զ���������ļ���
			TypedArray ta = arg0.obtainStyledAttributes(arg1, R.styleable.absgreatlayout);
			//����px,py
			this.px = ta.getFloat(R.styleable.absgreatlayout_px, 0);
			this.py = ta.getFloat(R.styleable.absgreatlayout_py, 0);
			//�ͷ���Դ
			ta.recycle();
		}

		public LayoutParams(int arg0, int arg1,float px,float py) {
			super(arg0, arg1);
			this.px = px;
			this.py = py;
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams arg0) {
			super(arg0);
		}
		
	}

}
