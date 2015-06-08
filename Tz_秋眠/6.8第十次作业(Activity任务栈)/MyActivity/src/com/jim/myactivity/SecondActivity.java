package com.jim.myactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class SecondActivity extends Activity implements OnClickListener {
	private LinearLayout layout;
	private Button button;
	private Button button2;
	private static final int BTN_ID = 10000;
	private static final int BUTTON_ID = 10002;
	private static int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
		setContentView(layout);
	}

	private void initView() {
		layout = new LinearLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(params);

		button = new Button(this);
		LayoutParams btnParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		button.setId(BTN_ID);
		button.setText("跳转到第三界面");
		button.setLayoutParams(btnParams);

		button2 = new Button(this);
		LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		button2.setId(BUTTON_ID);
		button2.setLayoutParams(buttonParams);
		button2.setText("点击我计数");
		layout.addView(button,0);
		layout.addView(button2,1);
		button.setOnClickListener(this);
		button2.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case BTN_ID:
			Intent intent = new Intent(this, ThirdlyActivity.class);
			startActivity(intent);
			break;
		case BUTTON_ID:
			index++;
			button2.setText(String.valueOf(index));
			break;
		default:
			break;
		}
	}
}
