package com.javaview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll=new LinearLayout(this);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		ll.setGravity(Gravity.CENTER_HORIZONTAL);
		
		EditText et=new EditText(this);
		LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams) new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT );
		lp.weight=1;
		et.setLayoutParams(lp);
		
		Button bt=new Button(this);
		bt.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		bt.setText("搜索");
		
		ll.addView(bt);
		ll.addView(et, 0);
		
		setContentView(ll);
	}
}
