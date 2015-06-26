/**
 * Project Name:lsn16_fragment
 * File Name:MainActivity.java
 * Package Name:com.zht.fragment
 * Date:2015-6-19下午4:55:45
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-19 下午4:55:45 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends FragmentActivity implements MyNoticeListener {
	private Button btn;
	private FragmentManager fm;
	private boolean step = true;
	private LinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn = (Button) findViewById(R.id.btn);
		ll = (LinearLayout) findViewById(R.id.linearLayout);
		NextFragment nextFragment = new NextFragment();
		fm = this.getSupportFragmentManager();
		fm.addOnBackStackChangedListener(new MyBackStackListener());
		FragmentTransaction ft = fm.beginTransaction();
		ft.addToBackStack("next");
		ft.replace(R.id.layout, nextFragment);
		ft.commitAllowingStateLoss();

	}

	public void btn(View v) {
		if (step) {
			changeFragment();
		}
	}

	private void changeFragment() {
		RegisterFragment registerFragment = new RegisterFragment();
		FragmentTransaction ft = fm.beginTransaction();
		ft.addToBackStack("register");
		ft.replace(R.id.layout, registerFragment);
		ft.commitAllowingStateLoss();
	}

	class MyBackStackListener implements OnBackStackChangedListener {

		@Override
		public void onBackStackChanged() {
			int count = fm.getBackStackEntryCount();
			if (count == 0) {
				step = false;
				btn.setText("下一步");
			} else {
				step = true;
				btn.setText("注册");
			}
		}

	}

	@Override
	public void notice(boolean isNotice) {
		Log.i("INFO", isNotice+"");
		if(isNotice){
			Log.i("INFO", "-----------------------------------");
			TextView tv = new TextView(this);
			tv.setText("输入不合法");
			tv.setTextColor(Color.WHITE);
			tv.setTextSize(50.0f);
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			ll.addView(tv,2,lp);
		}
		
	}


}
