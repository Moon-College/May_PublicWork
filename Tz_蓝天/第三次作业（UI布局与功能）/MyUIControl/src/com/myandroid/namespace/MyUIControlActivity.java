package com.myandroid.namespace;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyUIControlActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	Button ll_bnt;
	EditText ll_et;
	LinearLayout ll_center;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		createUI();

	}

	/**
	 * 创建UI界面，实现功能
	 */
	private void createUI() {
		// 创建最外面的LinearLayout布局
		LinearLayout linearlayout = new LinearLayout(this);
		// 设置LinearLayout的宽和高
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		linearlayout.setLayoutParams(lp);
		linearlayout.setOrientation(LinearLayout.VERTICAL);

		// 创建上方的 LinearLayout 布局
		LinearLayout ll = new LinearLayout(MyUIControlActivity.this);
		// 设置LinearLayout的宽和高
		LayoutParams ll_lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(ll_lp);
		// ll.setOrientation(LinearLayout.HORIZONTAL);

		// 创建 EditText
		ll_et = new EditText(MyUIControlActivity.this);
		// 设置EditText的宽和高，weight值为1
		LinearLayout.LayoutParams et_llp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		ll_et.setLayoutParams(et_llp);
		// 设置EditText的 id
		ll_et.setId(1);
		// 把EditText添加到线性布局容器中
		ll.addView(ll_et, 0);

		//创建按钮
		ll_bnt = new Button(MyUIControlActivity.this);
		//设置按钮宽和高
		LayoutParams bnt_lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		ll_bnt.setLayoutParams(bnt_lp);
		//设置按钮的文字
		ll_bnt.setText("搜索");
		//设置按钮的Id
		ll_bnt.setId(2);
		// 把按钮添加到线性布局容器中
		ll.addView(ll_bnt, 1);

		//创建中间部分的LinearLayout 布局
		ll_center = new LinearLayout(MyUIControlActivity.this);
		//设置LinearLayout的宽和高
		LayoutParams ll_center_lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		ll_center.setLayoutParams(ll_center_lp);
		//设置方向为垂直方向
		ll_center.setOrientation(LinearLayout.VERTICAL);
		//设置水平居中
		ll_center.setGravity(Gravity.CENTER_HORIZONTAL);
		//设置背景为蓝色
		ll_center.setBackgroundColor(Color.BLUE);

		linearlayout.addView(ll);
		linearlayout.addView(ll_center);
		setContentView(linearlayout);

		ll_bnt.setOnClickListener(this);

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case 2:
			//循环前先清除中间的LinearLayout布局里的控件
			ll_center.removeAllViews();
			
			String text=ll_et.getText().toString().trim();
			int count =Integer.valueOf(text);
			
			for (int i = 0; i < count; i++) {
				//创建textview
				TextView tv=new TextView(MyUIControlActivity.this);
				LayoutParams tv_lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				tv.setLayoutParams(tv_lp);
				
				Log.i("info", "textview"+i);
				
				tv.setText("textview"+i);
				ll_center.addView(tv);
			}
			break;

		default:
			break;
		}
	}
}