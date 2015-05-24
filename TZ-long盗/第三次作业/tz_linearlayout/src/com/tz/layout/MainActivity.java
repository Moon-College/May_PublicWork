package com.tz.layout;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    private static final int NUMBER_ONE = 1;
	private static final int NUMBER_TWO = 2;
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout llyout = new LinearLayout(this);
		llyout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		llyout.setBackgroundColor(color.white);
		llyout.setOrientation(LinearLayout.VERTICAL);
		setContentView(llyout);
		
		Button btn1 = new Button(this);
		btn1.setId(NUMBER_TWO);
		btn1.setText("作业一");
		btn1.setOnClickListener(new OnClickListener() {					
			@Override
			public void onClick(View v) {
				intent = new Intent(MainActivity.this,oneLayoutActivity.class);
				startActivity(intent);
				
			}
		});
		LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
		llyout.addView(btn1, params);
		
		Button btn2 = new Button(this);
		btn2.setId(NUMBER_TWO);
		btn2.setText("作业二");
		btn2.setOnClickListener(new OnClickListener() {					
			@Override
			public void onClick(View v) {
				intent = new Intent(MainActivity.this,twoLayoutActivity.class);
				startActivity(intent);
				
			}
		});
		LinearLayout.LayoutParams  btnParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);				
		llyout.addView(btn2, btnParams);
    }
}