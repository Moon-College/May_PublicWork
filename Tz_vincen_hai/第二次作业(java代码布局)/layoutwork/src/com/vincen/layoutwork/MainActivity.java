package com.vincen.layoutwork;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		LinearLayout ll = new LinearLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setLayoutParams(params);
		
		EditText et = new EditText(this);
		LayoutParams etParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et.setLayoutParams(etParams);

		Button btn = new Button(this);
		btn.setText("ËÑË÷");
		btn.setHeight(40);
		btn.setWidth(LayoutParams.WRAP_CONTENT);
		
		ll.addView(et);
		ll.addView(btn);
		
		setContentView(ll);
		
	}

}
