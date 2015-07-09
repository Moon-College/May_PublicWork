package com.vincen.layoutwork2;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends Activity {

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
		final EditText et_top = new EditText(this);
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
		final LinearLayout ll_bottom = new LinearLayout(this);
		LayoutParams params_rl = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		ll_bottom.setLayoutParams(params_rl);
		ll_bottom.setOrientation(LinearLayout.VERTICAL);
		
		//将上层容器和下层容器添加到最外层容器
		ll.addView(ll_top);
		ll.addView(ll_bottom);
		
		setContentView(ll);
		
		btn_top.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				String content = et_top.getText().toString().trim();
				if(content.isEmpty()){
					Toast.makeText(getApplication(), "不能为空", Toast.LENGTH_LONG).show();
				}
				TextView tv = new TextView(getApplicationContext());
				tv.setText(content);
				tv.setBackgroundColor(Color.CYAN);
				
				ll_bottom.addView(tv);
			}
		});
		
	}

	

}
