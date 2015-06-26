package com.zht.ui;
import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Project Name:lsn3_ui_1
 * File Name:MainActivity.java
 * Package Name:
 * Date:2015-6-4ÏÂÎç2:29:10
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-4 ÏÂÎç2:29:10 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);

		EditText et = new EditText(this);
		LinearLayout.LayoutParams etParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et.setLayoutParams(etParams);

		Button btn = new Button(this);
		btn.setText("ËÑË÷");
		LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0);
		btn.setLayoutParams(btnParams);

		ll.addView(et, 0);
		ll.addView(btn, 1);

		setContentView(ll);
	}
}
