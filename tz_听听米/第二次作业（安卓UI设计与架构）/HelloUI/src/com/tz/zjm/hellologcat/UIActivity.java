package com.tz.zjm.hellologcat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UIActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		//最外层的线性容器
		LinearLayout ll_all = new LinearLayout(this);
		//设置宽高
		ll_all.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		ll_all.setOrientation(LinearLayout.VERTICAL);
		
		//顶层的线性容器
		LinearLayout ll_top = new LinearLayout(this);
		ll_top.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,0));
		ll_top.setOrientation(LinearLayout.HORIZONTAL);
		//按钮
		Button btn = new Button(this);
		btn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0));
		btn.setText("搜索");
		
		EditText et = new EditText(this);
		et.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1));
		ll_top.addView(et);
		ll_top.addView(btn);
		
		//底层的线性容器
		LinearLayout ll_bottom = new LinearLayout(this);
		ll_bottom.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,1));
		ll_bottom.setOrientation(LinearLayout.VERTICAL);
		
		ImageView iv = new ImageView(this);
		//将控件填充父容器，控件内的内容居中显示
		LinearLayout.LayoutParams iv_lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		iv_lp.gravity = Gravity.CENTER;
		iv.setLayoutParams(iv_lp);
		iv.setImageResource(R.drawable.ic_launcher);
		ll_bottom.addView(iv);
		
		ll_all.addView(ll_top);
		ll_all.addView(ll_bottom);
		setContentView(ll_all);
		
	}

}
