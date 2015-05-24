package com.tz.layout;

import android.R.color;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class twoLayoutActivity extends Activity implements OnClickListener{
	private static final int NUMBER_ONE = 1;
	private static final int NUMBER_TWO = 2;
	private static final int NUMBER_THERE = 3;
	private static final int NUMBER_FOUR = 4;
	private static final int NUMBER_FIVE = 5;
	private RelativeLayout rl;
	private EditText et;
	private LinearLayout num_ll;
	private LinearLayout tlyout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//父类相对布局rl
		rl = new RelativeLayout(this);
		rl.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		rl.setBackgroundColor(color.white);
		setContentView(rl);
		rl.setBackgroundColor(Color.WHITE);
		//第一子布局---线性布局llyout
		LinearLayout llyout = new LinearLayout(this);
		llyout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));		
		llyout.setOrientation(LinearLayout.HORIZONTAL);
		llyout.setId(NUMBER_FOUR);	
		
		et = new EditText(this);
		et.setId(NUMBER_ONE);
		et.setHint("请输入数字");
		LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);		
		llyout.addView(et, params);
		
		Button btn = new Button(this);
		btn.setId(NUMBER_TWO);
		btn.setText("确定");
		btn.setOnClickListener(this);
		LinearLayout.LayoutParams  btnParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);				
		llyout.addView(btn, btnParams);
		
		
		RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
	    lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);       
	     // llyout 位于父 View 的顶部
	    rl.addView(llyout, lp1); 
		
	    //第二子布局
	    tlyout = new LinearLayout(this);
		tlyout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));		
		tlyout.setOrientation(LinearLayout.VERTICAL);
		tlyout.setBackgroundColor(Color.YELLOW);
		tlyout.setId(NUMBER_FIVE);
		
		RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);  
	    lp2.addRule(RelativeLayout.BELOW,NUMBER_FOUR);  
	    lp2.addRule(RelativeLayout.CENTER_VERTICAL);
	    lp2.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    
		// llyout 位于子view llyout下面 的水平居中
		rl.addView(tlyout, lp2); 
	    

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case NUMBER_TWO://按钮点击事件
			String contextStr = et.getText().toString();//得到输入的内容
			//第二子布局---线性布局llyout
			
			TextView tv = new TextView(this);
			LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);	
			tv.setText(contextStr);
			tv.setTextSize(26);
			tv.setTextColor(Color.RED);
			tlyout.addView(tv, tvParams);
			break;

		default:
			break;
		}
		
	}

}
