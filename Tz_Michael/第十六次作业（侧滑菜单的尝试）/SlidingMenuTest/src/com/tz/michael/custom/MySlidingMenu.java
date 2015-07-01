package com.tz.michael.custom;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

public class MySlidingMenu extends HorizontalScrollView {

	/**屏幕的宽度*/
	private int widthScreen;
	/**三个孙子控件*/
	private ViewGroup vp0,vp1,vp2;
	private boolean isLeftOpen,isRightOpen;
	/**主页面是否可以缩放*/
	private boolean isContentCanScall=true;
	/**右侧菜单出现时主页面是否跟着移动*/
	private boolean isContentCanScroll=true;
	

	public boolean isContentCanScroll() {
		return isContentCanScroll;
	}

	public void setContentCanScroll(boolean isContentCanScroll) {
		this.isContentCanScroll = isContentCanScroll;
	}

	public boolean isContentCanScall() {
		return isContentCanScall;
	}

	public void setContentCanScall(boolean isContentCanScall) {
		this.isContentCanScall = isContentCanScall;
	}

	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			int x=(Integer) msg.obj;
			MySlidingMenu.this.smoothScrollTo(x, 0);
		};
	};
	
	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm=new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		widthScreen=dm.widthPixels;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		ViewGroup vp=(ViewGroup) this.getChildAt(0);
		vp0 = (ViewGroup) vp.getChildAt(0);
		vp0.getLayoutParams().width=(int) (widthScreen*0.8f);
		vp1=(ViewGroup) vp.getChildAt(1);
		vp1.getLayoutParams().width=(int) (widthScreen*1.0f);
		vp2=(ViewGroup) vp.getChildAt(2);
		vp2.getLayoutParams().width=(int) (widthScreen*0.8f);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(changed){
			this.scrollTo(vp0.getWidth(), 0);
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		boolean flag=false;
		float scall=0;
		if(l<vp0.getWidth()){
			scall=(float)l/vp0.getWidth();
			flag=false;
		}else{
			scall=(float)(l-vp0.getWidth())/(vp1.getWidth()-(widthScreen-vp2.getWidth()));
			flag=true;
		}
		if(!flag){
			//设置左menu的缩放
			float leftScall=1-0.3f*scall;
			ViewHelper.setScaleX(vp0, leftScall);
			ViewHelper.setScaleY(vp0, leftScall);
			ViewHelper.setTranslationX(vp0, l);
			ViewHelper.setAlpha(vp0, 0.8f+0.2f*scall);
			float contentScall= 0.9f + 0.1f * scall;
			if (isContentCanScall) {
				ViewHelper.setScaleX(vp1, contentScall);
				ViewHelper.setScaleY(vp1, contentScall);
			}
			ViewHelper.setAlpha(vp1, contentScall);
		}else{
			//设置右menu的缩放
			float rightScall=1-0.1f*scall;
			if (isContentCanScall) {
				ViewHelper.setScaleX(vp1, rightScall);
				ViewHelper.setScaleY(vp1, rightScall);
			}
			if (!isContentCanScroll) {
				ViewHelper.setTranslationX(vp1, l - vp0.getWidth());
			}
			ViewHelper.setAlpha(vp0, 0.8f+0.2f*scall);
			float rightMenuScall = 0.8f+0.2f*scall;
			ViewHelper.setScaleX(vp2, rightMenuScall);
			ViewHelper.setScaleY(vp2, rightMenuScall);
			ViewHelper.setAlpha(vp2, rightMenuScall);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			break;
		case MotionEvent.ACTION_MOVE:
			
			break;
		case MotionEvent.ACTION_UP:
			int scrollX=this.getScrollX();
			Message msg=handler.obtainMessage();
			if(scrollX<vp0.getWidth()-vp1.getWidth()/2){
				msg.obj=0;
				isLeftOpen=true;
				isRightOpen=false;
			}else
			if(scrollX<vp0.getWidth()+vp1.getWidth()/2){
				msg.obj=vp0.getWidth();
				isLeftOpen=false;
				isRightOpen=false;
			}else{
				msg.obj=vp0.getWidth()+vp1.getWidth();
				isLeftOpen=false;
				isRightOpen=true;
			}
			handler.sendMessage(msg);
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
	/**
	 * 打开左侧菜单
	 */
	public void useLeftMenuHandle(){
		if(!isLeftOpen){
			this.smoothScrollTo(0, 0);
			isLeftOpen=true;
		}else{
			this.smoothScrollTo(vp0.getWidth(), 0);
			isLeftOpen=false;
		}
	}
	
	/**
	 * 打开右侧菜单
	 */
	public void useRightMenuHandle(){
		if(!isRightOpen){
			this.smoothScrollTo(vp0.getWidth()+vp1.getWidth(), 0);
			isRightOpen=true;
		}else{
			this.smoothScrollTo(vp0.getWidth(), 0);
			isRightOpen=false;
		}
	}
	
}
