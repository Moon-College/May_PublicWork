package com.tz.zjm.hellologcat;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DynamicUIActivity extends Activity implements OnClickListener {

	private LinearLayout ll_bottom;
	private LinearLayout ll_all;
	private LinearLayout ll_top;
	private Button btn;
	private EditText et;
	private ScrollView scroll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		ll_all = new LinearLayout(this);
		// 设置宽高
		ll_all.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		ll_all.setOrientation(LinearLayout.VERTICAL);

		ll_top = new LinearLayout(this);
		ll_top.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0));
		ll_top.setOrientation(LinearLayout.HORIZONTAL);
		btn = new Button(this);
		btn.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0));
		btn.setText("添加");
		btn.setOnClickListener(this);

		et = new EditText(this);
		et.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
		et.setHint("请输入5以内的数字");
		et.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
		ll_top.addView(et);
		ll_top.addView(btn);

		scroll = new ScrollView(this);
		scroll.setBackgroundColor(Color.YELLOW);
		//去掉滚动条
		scroll.setVerticalScrollBarEnabled(false);
		scroll.setVerticalFadingEdgeEnabled(false);
		scroll.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));

		ll_bottom = new LinearLayout(this);
		ll_bottom.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		ll_bottom.setOrientation(LinearLayout.VERTICAL);

		scroll.addView(ll_bottom);

		ll_all.addView(ll_top);
		ll_all.addView(scroll);
		setContentView(ll_all);
	}
	
	public int [] color = new int[]{
			Color.RED,
			Color.BLUE,
			Color.GRAY,
			Color.GREEN,
			Color.YELLOW
	};

	@Override
	public void onClick(View v) {
		String numStr = et.getText().toString();
		if(null!=numStr && !numStr.equals("")){
			int num = Integer.parseInt(numStr);
			if(num>5){
				Toast.makeText(DynamicUIActivity.this, "请不要输入超过5的数字", Toast.LENGTH_SHORT).show();
			}else{
//				Toast.makeText(DynamicUIActivity.this, numStr, Toast.LENGTH_SHORT).show();
				for(int i=0;i<num;i++){
					TextView tv = new TextView(DynamicUIActivity.this);
					tv.setBackgroundColor(color[i%color.length]);
					tv.setText("输入的数字是："+num);
					tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
					ll_bottom.addView(tv);
				}
			}
		}
		

	}

}
