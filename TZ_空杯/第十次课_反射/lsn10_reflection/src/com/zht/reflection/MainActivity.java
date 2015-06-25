/**
 * Project Name:lsn10_reflection
 * File Name:MainActivity.java
 * Package Name:com.zht.reflection
 * Date:2015-6-24обнГ3:57:38
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.reflection;

import com.zht.reflection.util.ReflectionUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-24 обнГ3:57:38 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity {
	private TextView tv_one, tv_two, tv_three, tv_four, tv_five, tv_six,
			tv_seven, tv_eight, tv_nine, tv_ten;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflectionUtils.initViews(this);
	}

	public void show(View v) { 
		Toast.makeText(this, tv_one.getText(), Toast.LENGTH_LONG).show();
	}
}
