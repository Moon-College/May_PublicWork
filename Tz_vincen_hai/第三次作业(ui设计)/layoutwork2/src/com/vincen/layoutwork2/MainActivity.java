package com.vincen.layoutwork2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		//最外层容器
		LinearLayout ll = new LinearLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(params);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		//顶层容器
		LinearLayout ll_top = new LinearLayout(this);
		LayoutParams params_top= new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll_top.setLayoutParams(params_top);
		ll_top.setOrientation(LinearLayout.HORIZONTAL);
		
		//文本输入框
		EditText et_top = new EditText(this);
		LayoutParams et_params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et_top.setLayoutParams(et_params);
		
		//搜索按钮
		Button btn_top = new Button(this);
		LayoutParams btn_params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		btn_top.setLayoutParams(btn_params);
		btn_top.setText("搜索");
		
		//将文本输入框和搜索按钮添加到上层容器中
		ll_top.addView(et_top);
		ll_top.addView(btn_top);
		
		//下层容器
		RelativeLayout rl_bottom = new RelativeLayout(this);
		LayoutParams params_rl = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		params_rl.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
		rl_bottom.setLayoutParams(params_rl);
		
		
		//图片
		ImageView imageView = new ImageView(this);
		RelativeLayout.LayoutParams params_img = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//params_img.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
		imageView.setBackgroundResource(R.drawable.movie);
		params_img.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		imageView.setLayoutParams(params_img);
		
		//将图片添加到下层容器中
		rl_bottom.addView(imageView);
		
		//将上层容器和下层容器添加到最外层容器
		ll.addView(ll_top);
		ll.addView(rl_bottom);
		
		setContentView(ll);
		
		
	}

	

}
