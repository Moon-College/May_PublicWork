package com.jim.myactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ThirdlyActivity extends Activity implements OnClickListener {
	private static final int BTN_ID = 10000;
	private Button button;
	private LinearLayout layout;

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
		layout.setLayoutParams(params);

		button = new Button(this);
		LayoutParams btnParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		button.setId(BTN_ID);
		button.setText("跳转到第一界面");
		button.setLayoutParams(btnParams);
		layout.addView(button);
		button.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
