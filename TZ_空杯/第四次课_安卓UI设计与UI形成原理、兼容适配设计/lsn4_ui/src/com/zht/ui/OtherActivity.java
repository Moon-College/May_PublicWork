/**
 * Project Name:lsn4_ui
 * File Name:otherActivity.java
 * Package Name:com.zht.ui
 * Date:2015-6-4下午7:33:01
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ClassName:otherActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-4 下午7:33:01 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class OtherActivity extends Activity {
	
	public static final int ID_BTN = 1;
	public static int number = 1;
	private LinearLayout ll;
	private EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//总的父容器
		RelativeLayout rl = new RelativeLayout(this);
		rl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		//按钮控件
		Button btn = new Button(this);
		btn.setText("添加");
		btn.setId(ID_BTN);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int num = Integer.parseInt(et.getText().toString());
				
				for (int i = 1; i <= num; i++) {
			    	TextView tv = new TextView(OtherActivity.this);
			    	tv.setText(number+"");
			    	ll.addView(tv);
			    	number++;
				}
			}
		});
		
		RelativeLayout.LayoutParams btnParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		btnParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rl.addView(btn, btnParams);
		
		et = new EditText(this);
		RelativeLayout.LayoutParams etParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etParams.addRule(RelativeLayout.LEFT_OF, ID_BTN);
		rl.addView(et, etParams);
		
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		llParams.addRule(RelativeLayout.BELOW,ID_BTN);
		rl.addView(ll, llParams);
		
		setContentView(rl);
	}
}
