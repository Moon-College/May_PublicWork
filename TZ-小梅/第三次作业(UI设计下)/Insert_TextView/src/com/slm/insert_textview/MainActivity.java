package com.slm.insert_textview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		final LinearLayout ll= new LinearLayout(this);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		ll.setOrientation(LinearLayout.VERTICAL);
		

		LinearLayout ll_top = new LinearLayout(this);
		ll_top.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams lp_top = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		ll_top.setLayoutParams(lp_top);

		final EditText et_content = new EditText(this);
		et_content.setHint("请输入数字");
		LinearLayout.LayoutParams llp_content = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et_content.setLayoutParams(llp_content);

		Button bt_search = new Button(this);
		bt_search.setText("添加");
		LinearLayout.LayoutParams llp_search = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		bt_search.setLayoutParams(llp_search);
		// bt_search.callOnClick();
		ll_top.addView(et_content);
		ll_top.addView(bt_search);

		

		
		final LinearLayout ll_bottom = new LinearLayout(this);
		LayoutParams lp_bottom = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		ll_bottom.setLayoutParams(lp_bottom);
		ll_bottom.setOrientation(LinearLayout.VERTICAL);
		
		ll.addView(ll_top);
		ll.addView(ll_bottom);
		
		setContentView(ll);
		bt_search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 得到et_content的输入的内容
				int count = Integer.parseInt(et_content.getText().toString().trim());
				//String count = et_content.getText().toString().trim();
				
				if (TextUtils.isEmpty(et_content.getText().toString().trim())) {
					Toast.makeText(MainActivity.this, "不能为空",
							Toast.LENGTH_SHORT).show();
				}
				
				for (int i = 0; i < count; i++) {
					TextView tv= new TextView(getApplicationContext());
					LinearLayout.LayoutParams llp_tv = new LinearLayout.LayoutParams(
							LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					tv.setLayoutParams(llp_tv);
					tv.setText((i+1)+"XXXXXX");
					//tv.setText(count);
					tv.setBackgroundColor(Color.YELLOW);
					ll_bottom.addView(tv);
				}

			}
		});

		// llp_max.addView(et_textView,1);
	}
}
