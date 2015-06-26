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
	
	private int screenWidth; //��Ļ���
	private int menuWidth; //�˵����
	private int contentWidth; //���ݿ��
	private ViewGroup vg_menu;
	private ViewGroup vg_content;
	private final float vg_menu_out = 0.2f;

	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		//��ȡ��Ļ�Ŀ��
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE); 
		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//llָ��activity_main.xml������LinearLayout
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		vg_menu = (ViewGroup) ll.getChildAt(0); //ͨ���±��ȡ��Ӧ����ͼ
		vg_content = (ViewGroup) ll.getChildAt(1);
		
		menuWidth = (int) (0.8*screenWidth); //����menu�Ŀ��
		vg_menu.getLayoutParams().width = (int) (0.8*screenWidth);  //��̬�ı���ֵ������new
		vg_content.getLayoutParams().width = screenWidth;

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);   //�ȷ��ã��ڸı�λ��
		if(changed){
			this.scrollTo(menuWidth, 0);
			android.util.Log.i("INFO", "menuWidth"+menuWidth);
		}
//		super.onLayout(changed, l, t, r, b);  //���»ָ���ԭ����λ�ã�����ǰ�������⣬ע����õ�λ��
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		//l����ScrollView����߻����ľ��룬l�ı仯0~1��menuWidth��
		float scale = (float)l/menuWidth;
		
		//leftScale�ı仯1~0.7�����š�
		float leftScale = 1-scale*0.3f;
		ViewHelper.setScaleX(vg_menu, leftScale);
		ViewHelper.setScaleY(vg_menu, leftScale);
		
		//͸����1~0.3
		float leftAlpha = 1-scale*0.7f;
		ViewHelper.setAlpha(vg_menu, leftAlpha);
		
		//λ�Ʊ仯��scale�ı仯0~1  0.7�����ƶ����Կ�����Ч��   1.7f�������ƶ�
		ViewHelper.setTranslationX(vg_menu, l*0.7f);  //�����ƶ����໥����������Ҫȥ���
		
		//vg_content�仯0.8~1�����š�
		float rightScale = 0.8f+scale*0.2f;
		ViewHelper.setScaleX(vg_content, rightScale);
		ViewHelper.setScaleY(vg_content, rightScale);
		
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
}
