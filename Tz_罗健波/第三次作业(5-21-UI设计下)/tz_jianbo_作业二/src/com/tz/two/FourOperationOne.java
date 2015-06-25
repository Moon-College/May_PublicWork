package com.tz.two;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 第四节课：作业一
 * 
 * @author 罗健波 2015-5-22
 * 
 */
public class FourOperationOne extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 最外层布局
		RelativeLayout relaLayout = new RelativeLayout(this);
		// 顶部嵌套布局
		LinearLayout linearLayout = new LinearLayout(this);
		// 兄弟布局
		ImageView img = new ImageView(this);
		// 设置居中属性
		RelativeLayout.LayoutParams reParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		reParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		// 子控件
		EditText etText = new EditText(this);
		Button btn = new Button(this);
		// 属性设置
		img.setImageResource(R.drawable.movie);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		btn.setText("搜索");
		etText.setHint("请输入网址");
		relaLayout.setBackgroundColor(Color.GRAY);
		etText.setLayoutParams(new LinearLayout.LayoutParams(0,
				WindowManager.LayoutParams.WRAP_CONTENT, 1.0f));
		relaLayout.addView(linearLayout,
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT);
		relaLayout.addView(img, WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT);
		linearLayout.addView(etText);
		linearLayout.addView(btn, WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT);
		img.setLayoutParams(reParams);
		this.setContentView(relaLayout);
	}

}
