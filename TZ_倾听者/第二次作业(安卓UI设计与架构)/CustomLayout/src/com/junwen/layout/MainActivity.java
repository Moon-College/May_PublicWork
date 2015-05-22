package com.junwen.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//获取自定义布局
		LinearLayout customLayout = getCustomLayout();
		setContentView(customLayout);
	}
	/**
	 * 自定义布局1
	 * @author June
	 */
	private LinearLayout getCustomLayout() {
		
		//外部LInearLayout
		LinearLayout out_layout = new LinearLayout(this);
		out_layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		out_layout.setOrientation(LinearLayout.VERTICAL);
		
		//头部LinearLayout
		LinearLayout head_layout = new LinearLayout(this);
		head_layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		head_layout.setOrientation(LinearLayout.HORIZONTAL);
		head_layout.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
		
		//底部LinearLayout
		LinearLayout button_layout = new LinearLayout(this);
		button_layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
		button_layout.setOrientation(LinearLayout.HORIZONTAL);
		button_layout.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
		
		//ImageView图片
		LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		LayoutParams.gravity = Gravity.CENTER;
		ImageView img = new ImageView(this);
		img.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		img.setImageResource(R.drawable.img);
		img.setLayoutParams(LayoutParams);
		
		//EditText编辑框
		EditText et_text = new EditText(this);
		et_text.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
		et_text.setHint("请输入信息");
//		et_text.setBackgroundColor(android.R.drawable.editbox_background);
		
		//Button按钮
		Button btn_button = new Button(this);
		btn_button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btn_button.setText("搜索");
		
		//添加EditText进入头部LinearLaoyut
		head_layout.addView(et_text);
		//添加Button进入头部LinearLayout
		head_layout.addView(btn_button);
		//添加图片进入底部LinearLayout
		button_layout.addView(img);
		//添加头部LinearLayout进入外部LinearLayout
		out_layout.addView(head_layout);
		//添加底部LinearLayout进入外部LinearLayout
		out_layout.addView(button_layout);
		//返回布局
		return out_layout;
	}
}
