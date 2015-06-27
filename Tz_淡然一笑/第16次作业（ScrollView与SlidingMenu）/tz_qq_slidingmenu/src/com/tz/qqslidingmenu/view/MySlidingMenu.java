package com.tz.qqslidingmenu.view;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MySlidingMenu extends HorizontalScrollView {
	
	private int screenWidth; //屏幕宽度
	private int menuWidth; //菜单宽度
	private int contentWidth; //内容宽度
	private ViewGroup vg_menu;
	private ViewGroup vg_content;
	private final float vg_menu_out = 0.2f;

	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		//获取屏幕的宽度
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE); 
		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//ll指的activity_main.xml最外层的LinearLayout
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		vg_menu = (ViewGroup) ll.getChildAt(0); //通过下标获取对应的视图
		vg_content = (ViewGroup) ll.getChildAt(1);
		
		menuWidth = (int) (0.8*screenWidth); //设置menu的宽度
		vg_menu.getLayoutParams().width = (int) (0.8*screenWidth);  //动态改变宽度值，不用new
		vg_content.getLayoutParams().width = screenWidth;

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);   //先放置，在改变位置
		if(changed){
			this.scrollTo(menuWidth, 0);
			android.util.Log.i("INFO", "menuWidth"+menuWidth);
		}
//		super.onLayout(changed, l, t, r, b);  //重新恢复到原来的位置，放在前面有问题，注意放置的位置
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		//l就是ScrollView往左边滑动的距离，l的变化0~1【menuWidth】
		float scale = (float)l/menuWidth;
		
		//leftScale的变化1~0.7【缩放】
		float leftScale = 1-scale*0.3f;
		ViewHelper.setScaleX(vg_menu, leftScale);
		ViewHelper.setScaleY(vg_menu, leftScale);
		
		//透明度1~0.3
		float leftAlpha = 1-scale*0.7f;
		ViewHelper.setAlpha(vg_menu, leftAlpha);
		
		//位移变化，scale的变化0~1  0.7向左移动可以看出点效果   1.7f会往右移动
		ViewHelper.setTranslationX(vg_menu, l*0.7f);  //左右移动，相互抵消，这里要去理解
		
		//vg_content变化0.8~1【缩放】
		float rightScale = 0.8f+scale*0.2f;
		ViewHelper.setScaleX(vg_content, rightScale);
		ViewHelper.setScaleY(vg_content, rightScale);
		
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
}
