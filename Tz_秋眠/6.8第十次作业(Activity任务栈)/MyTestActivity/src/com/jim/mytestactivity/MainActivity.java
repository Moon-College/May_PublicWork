package com.jim.mytestactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity implements OnClickListener {
	
	private static final int BTN_ID = 10000;
	private Button button;
	private LinearLayout layout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
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
		button.setText("跳转到第二界面");
		button.setLayoutParams(btnParams);
		layout.addView(button);
		button.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent("com.jim.action.SECOND_ACTIVITY");
		startActivity(intent);
	}

}