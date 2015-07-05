package cn.ysh.view;

import cn.ysh.parent_layout.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyAbsolutelayout extends ViewGroup{

	public MyAbsolutelayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyAbsolutelayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyAbsolutelayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//������֧��wrap_content,ֻ��Ҫ�����ӿؼ��Ŀ�߾Ϳ���
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childCount = this.getChildCount();
		for(int i = 0; i < childCount; i++){
			View child = this.getChildAt(i);
			if(child.getVisibility() != View.GONE){
				MyLayoutparams p = (MyLayoutparams) child.getLayoutParams();
				//��ȡ�ӿؼ���px��py
				int childLeft = (int) (p.px * this.getMeasuredWidth() + this.getPaddingLeft());
				int childTop = (int) (p.py * this.getMeasuredHeight() + this.getPaddingTop());
				int childRight = childLeft + child.getMeasuredWidth();
				int childBottom = childTop + child.getMeasuredHeight();
				child.layout(childLeft, childTop, childRight, childBottom);
			}
		}
	}
	
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new MyAbsolutelayout.MyLayoutparams(getContext(), attrs);
	}
	
	//��̬java����û������layoutparams��ʱ������
	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		// TODO Auto-generated method stub
		return new MyAbsolutelayout.MyLayoutparams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);
	}
	
	@Override
	protected LayoutParams generateLayoutParams(LayoutParams p) {
		// TODO Auto-generated method stub
		return new MyAbsolutelayout.MyLayoutparams(p);
	}
	
	public static class MyLayoutparams extends ViewGroup.LayoutParams{
		
		float px , py;

		public MyLayoutparams(Context arg0, AttributeSet arg1) {
			super(arg0, arg1);
			//ͨ��TypedArray����xml������ԣ����Զ���������ļ���
			TypedArray array = arg0.obtainStyledAttributes(arg1, R.styleable.ysh);
			//����px��py
			this.px = array.getFloat(R.styleable.ysh_px, 0);
			this.py = array.getFloat(R.styleable.ysh_py, 0);
			//�ͷ���Դ
			array.recycle();
		}

		public MyLayoutparams(int arg0, int arg1 , float px , float py) {
			super(arg0, arg1);
			this.px = px;
			this.py = py;
		}

		public MyLayoutparams(LayoutParams arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}
		
	}

}
