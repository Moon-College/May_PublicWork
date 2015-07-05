package com.decent.proportionlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.decent.decentutil.ReflictionUtil;
import com.decent.proportionlayout.layout.DecentProportionLayout;
import com.decent.proportionlayout.layout.DecentProportionLayout.LayoutParams;

public class DecentProportionLayoutActivity extends Activity implements
		OnClickListener {
	private TextView tv1;
	private Button btn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflictionUtil.InjectionView(
				DecentProportionLayoutActivity.class.getName(),
				R.class.getName(), this);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		/*
		 * 每次点击动态改变tv1的beasOnHalf属性，然后重新显示
		 */
		DecentProportionLayout.LayoutParams lp = (LayoutParams) tv1
				.getLayoutParams();
		lp.beasOnHalf = !lp.beasOnHalf;
		tv1.setLayoutParams(lp);
		tv1.invalidate();
	}
}