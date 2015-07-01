package com.vincen.qq_slidingmenu.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MySlidingMenu extends HorizontalScrollView{
	
	private int widthPixls;  //��Ļ�Ŀ��
	private int mMenuWidth;  //�˵��Ŀ��
	//private int mContentWidth ;  //���ݵĿ��
	private ViewGroup mMenu;
	private ViewGroup mContent;
	
	private Handler handler = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			float scrollX = (Float) msg.obj; 
			if(scrollX >= 0.4*widthPixls){
				smoothScrollTo(widthPixls, 0);
			}else{
				
				smoothScrollTo(0, 0);
			}
		}
	};
	
	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		//������Ļ�Ŀ��
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(metrics);
		widthPixls = metrics.widthPixels;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//���ò˵������ݵĿ��
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		mMenu = (ViewGroup) ll.getChildAt(0);
		mContent = (ViewGroup) ll.getChildAt(1);
		
		//������ò˵��������ʾ���
		mMenuWidth =  (int) (0.8*widthPixls);
		mMenu.getLayoutParams().width = (int)(0.8*widthPixls) ;
		mContent.getLayoutParams().width = widthPixls;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(changed){
			Log.i("INFO", mMenuWidth+"");
			this.scrollTo(mMenuWidth, 0);
		}
	}
	
	@SuppressLint("NewApi")
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		
		float scale = (float)l/mMenuWidth;
		//�����ƶ�ʱ������ 1~0.7
		float leftScale = 1-scale*0.3f;
		//���ò˵��������ŵı���
		mMenu.setScaleX(leftScale);
		mMenu.setScaleY(leftScale);
		
		//͸���ȱ仯1~0.3
		float leftAlpha = 1 - scale*0.7f;
		mMenu.setAlpha(leftAlpha);
		
		//���ò˵����󻬶���λ�Ʊ仯
		mMenu.setTranslationX(1*0.7f);
		
		//�������ݵı仯mContent�仯 0.8~1.0
		float rightScale = 0.8f+0.2f*scale;
		mContent.setScaleX(rightScale);
		mContent.setScaleY(rightScale);
		
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		
		
		case MotionEvent.ACTION_UP:
			
			//��ָ�ɿ���Ļ�����ľ���
			float scrollX = this.getScrollX();
			Message message =handler.obtainMessage();
			message.obj = scrollX;
			message.sendToTarget();
			
			break;

		default:
			break;
		};
		return super.onTouchEvent(ev);
	}

}
