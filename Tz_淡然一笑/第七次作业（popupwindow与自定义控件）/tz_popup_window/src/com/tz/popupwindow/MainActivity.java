package com.tz.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button btn_popup;
	private LayoutInflater inflater;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			btn_popup = (Button) findViewById(R.id.popup);
			btn_popup.setOnClickListener(this);
			
			inflater = this.getLayoutInflater();
	//		inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
	
		@Override
	public void onClick(View v) {
		// 初始化popupWindow对象
		PopupWindow popupWindow = new PopupWindow(this);
		// 解析xml,加载popupWindow视图
		View view = inflater.inflate(R.layout.popup_window, null);
		// 设置视图
		popupWindow.setContentView(view);
		// 获取屏幕的宽高
		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();   // 屏幕宽（像素，如：480px）  
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800px）  
		Log.i("INFO", "screenWidth:"+screenWidth);
		Log.i("INFO", "screenHeight:"+screenHeight);
		// 设置宽高
		popupWindow.setWidth(screenWidth/2);
		popupWindow.setHeight(screenHeight/2);
		// 设置背景图片
		popupWindow.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.local_popup_bg));
		// 设置焦点
		popupWindow.setFocusable(true);
		// 设置坐标
		int[] location = new int[2];
		// 获取btn_popup按钮的坐标
		btn_popup.getLocationInWindow(location);
		Log.i("INFO", "y:"+location[1]);
		btn_popup.getLocationOnScreen(location);
		Log.i("INFO", "y:"+location[1]);
		int location_y = location[1]+btn_popup.getHeight();
		
		// 传btn_popup按钮控件，找到它所在的窗口，位置参照物【源码AttachInfo.WindowTaken窗口唯一标识】
//		popupWindow.showAtLocation(btn_popup, Gravity.LEFT|Gravity.TOP, 100, location_y);
		// 默认显示在btn_popup按钮的左下方
		popupWindow.showAsDropDown(btn_popup);
		// 默认显示在btn_popup下方，并指定坐标x,y
		// 当控件btn_popup按钮在窗口的下方时，popupWindow则显示在btn_popup按钮的上方【popupWindow注释】
//		popupWindow.showAsDropDown(btn_popup, 200, 0);
	}
}
