package com.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private LinearLayout button_layout;
	private EditText et_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ScrollView scroll = initView();
		setContentView(scroll);
	}

	private ScrollView initView() {

		// 最外部
		ScrollView scroll = new ScrollView(this);
		scroll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		// 外部LinearLayout
		LinearLayout out_layout = new LinearLayout(this);
		out_layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		out_layout.setOrientation(LinearLayout.VERTICAL);

		// 头部LinearLayout
		LinearLayout head_layout = new LinearLayout(this);
		head_layout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0));
		head_layout.setOrientation(LinearLayout.HORIZONTAL);
		head_layout.setBackgroundColor(getResources().getColor(
				android.R.color.holo_green_light));

		// EditText文本框
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et_text = new EditText(this);
		et_text.setLayoutParams(params);
		et_text.setHint("输入要生成的数量");

		// Button按钮
		Button btn = new Button(this);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		btn.setText("生成");
		btn.setOnClickListener(this);

		// 底部LinearLayout
		button_layout = new LinearLayout(this);
		button_layout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
		button_layout.setOrientation(LinearLayout.VERTICAL);
		button_layout.setBackgroundColor(getResources().getColor(
				android.R.color.holo_orange_dark));

		// 把文本框和按钮添加到头部LineraLayout里
		head_layout.addView(et_text);
		head_layout.addView(btn);

		// 把头部LineraLayout和底部LineraLayout添加到外部LinearLayout里
		out_layout.addView(head_layout);
		out_layout.addView(button_layout);

		// 把外部LinearLayout添加到ScrollView里
		scroll.addView(out_layout);

		return scroll;
	}

	/**
	 * 当点击生成按钮后
	 */
	@Override
	public void onClick(View v) {
		// 判断底部LinearLayout是否有控件
		if (button_layout.getChildCount() != 0) {
			button_layout.removeAllViews();
		}
		String num = et_text.getText().toString().trim();
		// 判断是否有输入数量
		if (!num.equals("")) {
			int code = Integer.parseInt(num);
			// 循环生成TextView
			for (int i = 1; i <= code; i++) {
				TextView text = new TextView(this);
				text.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
				text.setText("序号： " + i);
				text.setTextSize(18);
				// 添加控件进入底部LinearLaout
				button_layout.addView(text);
			}
		} else {
			Toast.makeText(this, "文本框不可为空", 0).show();
		}

	}

}
