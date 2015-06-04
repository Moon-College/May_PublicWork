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
		// ��ʼ��popupWindow����
		PopupWindow popupWindow = new PopupWindow(this);
		// ����xml,����popupWindow��ͼ
		View view = inflater.inflate(R.layout.popup_window, null);
		// ������ͼ
		popupWindow.setContentView(view);
		// ��ȡ��Ļ�Ŀ��
		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();   // ��Ļ�����أ��磺480px��  
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // ��Ļ�ߣ����أ��磺800px��  
		Log.i("INFO", "screenWidth:"+screenWidth);
		Log.i("INFO", "screenHeight:"+screenHeight);
		// ���ÿ��
		popupWindow.setWidth(screenWidth/2);
		popupWindow.setHeight(screenHeight/2);
		// ���ñ���ͼƬ
		popupWindow.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.local_popup_bg));
		// ���ý���
		popupWindow.setFocusable(true);
		// ��������
		int[] location = new int[2];
		// ��ȡbtn_popup��ť������
		btn_popup.getLocationInWindow(location);
		Log.i("INFO", "y:"+location[1]);
		btn_popup.getLocationOnScreen(location);
		Log.i("INFO", "y:"+location[1]);
		int location_y = location[1]+btn_popup.getHeight();
		
		// ��btn_popup��ť�ؼ����ҵ������ڵĴ��ڣ�λ�ò����Դ��AttachInfo.WindowTaken����Ψһ��ʶ��
//		popupWindow.showAtLocation(btn_popup, Gravity.LEFT|Gravity.TOP, 100, location_y);
		// Ĭ����ʾ��btn_popup��ť�����·�
		popupWindow.showAsDropDown(btn_popup);
		// Ĭ����ʾ��btn_popup�·�����ָ������x,y
		// ���ؼ�btn_popup��ť�ڴ��ڵ��·�ʱ��popupWindow����ʾ��btn_popup��ť���Ϸ���popupWindowע�͡�
//		popupWindow.showAsDropDown(btn_popup, 200, 0);
	}
}
