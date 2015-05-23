package com.javaview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class SecondActivity extends Activity{
	private  LinearLayout ll,ll_parent,num_ll;
	int count=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ll_parent=GetView.getParent(this);
		
		ll=GetView.getTop(this,"添加", new OnClickListener() {
			@Override
			public void onClick(View v) {
				ll_parent.removeView(num_ll);
				EditText et=(EditText) ll.getChildAt(0);
				String str=et.getText().toString();
				/**
				 * 默认五个
				 */
				try {
					count = Integer.parseInt(str);
				} catch (Exception e) {
					count=5;
				}
				init_num();
				ll_parent.addView(num_ll);
			}
		});
		ll_parent.addView(ll);
		setContentView(ll_parent);
	}
	private void init_num() {
		num_ll=new LinearLayout(this);
		num_ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		num_ll.setOrientation(LinearLayout.VERTICAL);
		for(int i=0;i<count;i++)
		{
			TextView tv=new TextView(this);
			LinearLayout.LayoutParams iv_lp=(LinearLayout.LayoutParams) new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
			tv.setGravity(Gravity.CENTER_HORIZONTAL);
			tv.setLayoutParams(iv_lp);
			tv.setText("hahaha "+(i+1));
			num_ll.addView(tv);	
		}
		
	}
}
