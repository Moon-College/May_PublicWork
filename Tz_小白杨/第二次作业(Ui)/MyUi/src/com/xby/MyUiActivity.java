package com.xby;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MyUiActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(setLayout());
	}

	private LinearLayout setLayout() {
		// 线性布局
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		ll.setLayoutParams(lp);
		// 文本框
		EditText et = new EditText(this);
		LinearLayout.LayoutParams etlp = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et.setLayoutParams(etlp);
		// 按钮
		Button bt = new Button(this);
		bt.setText("搜索");
		LinearLayout.LayoutParams btlp = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		bt.setLayoutParams(btlp);
		ll.addView(et);
		ll.addView(bt);
		return ll;
	}
}