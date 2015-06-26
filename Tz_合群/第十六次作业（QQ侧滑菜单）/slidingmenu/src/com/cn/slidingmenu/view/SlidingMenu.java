package com.cn.slidingmenu.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

public class SlidingMenu extends HorizontalScrollView {
	
	private int windWidth;
	private ViewGroup mMenu;
	private ViewGroup mMain;
	private int mMenuRightOffset=100;
	private int mMenuWidth;
	private VelocityTracker vt=null;
	private float criticalSpeed=1200;
	private float xSpeed;

	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			int srcollX=(Integer) msg.obj;
			SlidingMenu.this.smoothScrollTo(srcollX, 0);
		};
	};
	
	
	public SlidingMenu(Context context) {
		super(context);
	}
	
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}


	private void init(Context context) {
		WindowManager winManager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics=new DisplayMetrics();
		winManager.getDefaultDisplay().getMetrics(displayMetrics);
		windWidth=displayMetrics.widthPixels;
		mMenuRightOffset=windWidth/4;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		LinearLayout wrapper=(LinearLayout) this.getChildAt(0);
		mMenu=(ViewGroup) wrapper.getChildAt(0);
		mMain=(ViewGroup) wrapper.getChildAt(1);
		mMenuWidth=windWidth-mMenuRightOffset;
		mMenu.getLayoutParams().width=mMenuWidth;
		mMain.getLayoutParams().width=windWidth;
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(changed){
			this.scrollTo(mMenuWidth, 0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action=ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if(vt==null){
				vt=VelocityTracker.obtain();
			}else{
				vt.clear();
			}
			vt.addMovement(ev);
			break;
		case MotionEvent.ACTION_MOVE:
			vt.addMovement(ev);
			vt.computeCurrentVelocity(1000);
			xSpeed=vt.getXVelocity();
			break;
		case MotionEvent.ACTION_UP:
			int scrollX=this.getScrollX();
			int span=mMenuWidth-windWidth/2;
			Message msg=handler.obtainMessage();
			//System.out.println("move�?+xSpeed);
			
			if(xSpeed>0){//手指向右滑动
				if(xSpeed>criticalSpeed||scrollX<span){
					msg.obj=0;
				}else{
					msg.obj=mMenuWidth;
				}
			}else{//手指向左滑动
				if(scrollX>=span||xSpeed<-criticalSpeed){
					msg.obj=mMenuWidth;
				}else{
					msg.obj=0;
				}
			}
			//发�?消息给handler
			msg.sendToTarget();
			break;
		case MotionEvent.ACTION_CANCEL:
			vt.recycle();
			System.out.println("ACTION_CANCEL");
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		//l表示向左滚动出屏幕的距离
		//设置动画 假设向左移动
		//获取缩放变化比例
		float scale=l/(mMenuWidth*1f);//�?变化�? 注意这里要用float类型不能用int类型
		//System.out.println("scale"+scale);
		float leftScale=1-0.3f*scale;//�?变化�?.7
		//缩放动画
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		//透明度改�?
		ViewHelper.setAlpha(mMenu,1-0.7f*scale);//透明度从1变化�?.3
		//平移动画 做抗拒运�?translationX为正增大表示向右平移
		ViewHelper.setTranslationX(mMenu,l*0.7f);//不完全强烈抗�?
		
		float rightScale=0.8f+0.2f*scale; //�?.8变化�?
		//缩放动画
		ViewHelper.setScaleX(mMain, rightScale);
		ViewHelper.setScaleY(mMain, rightScale);
		
		super.onScrollChanged(l, t, oldl, oldt);
	}

}
