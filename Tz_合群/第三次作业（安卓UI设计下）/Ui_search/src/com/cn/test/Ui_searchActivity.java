package com.cn.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Ui_searchActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	/**
	 * @author Hequn
	 * 
	 * 初始化布局
	 * 
	 ***/

	private void initView() {
		// 最外层RelativeLayout
		RelativeLayout rl_all = new RelativeLayout(this);
		rl_all.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		// 头部LinearLayout
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		LinearLayout li_top = new LinearLayout(this);
		li_top.setOrientation(LinearLayout.HORIZONTAL);
		li_top.setLayoutParams(params);

		// 输入框
		EditText et_input = new EditText(this);
		et_input.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
		et_input.setHint("请输入网址");

		// 搜索按钮
		Button bt_search = new Button(this);
		bt_search.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		bt_search.setText("搜索");
		bt_search.setTextSize(20);
		bt_search.setPadding(0, 10, 0, 10);

		// 图片
		RelativeLayout.LayoutParams img_params = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		img_params.addRule(RelativeLayout.CENTER_IN_PARENT);
		ImageView img = new ImageView(this);
		img.setImageResource(R.drawable.movie);
		img.setLayoutParams(img_params);

		// 把文本框和按钮放入布局
		li_top.addView(et_input, 0);
		li_top.addView(bt_search, 1);

		// 把图片和头部LinearLayout放入布局
		rl_all.addView(li_top, 0);
		rl_all.addView(img, 1);

		// 设置布局
		setContentView(rl_all);

	}
}