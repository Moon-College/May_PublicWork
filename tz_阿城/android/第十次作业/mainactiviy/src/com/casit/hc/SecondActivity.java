package com.casit.hc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class SecondActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("INFO", "ACTIVITY2");
		LinearLayout linearLayout = new LinearLayout(this);
		LayoutParams layoutParams = new LayoutParams(
				LayoutParams.FILL_PARENT, 
				LayoutParams.FILL_PARENT);
		linearLayout.setLayoutParams(layoutParams);
		Button button = new Button(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		button.setLayoutParams(params);
		button.setText("跳到第三个界面");
		button.setOnClickListener(this);
		linearLayout.addView(button);
		setContentView(linearLayout);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, ThirdActivity.class);
		startActivity(intent);
		
	}
	
}
