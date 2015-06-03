package com.itskylin.android.class3;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private ScrollView scroll;
	private EditText et;
	private LinearLayout bottom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 主界面
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		RelativeLayout rl = new RelativeLayout(this);
		rl.setLayoutParams(rlp);

		// top
		LinearLayout ll = new LinearLayout(this);
		RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		llp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		llp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		//换一个特殊一点的ID 别搞这个0
		ll.setId(1);
		ll.setLayoutParams(llp);

		et = new EditText(this);
		LinearLayout.LayoutParams etp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et.setHint("请输入1-10的数字...");

		Button bt = new Button(this);
		bt.setText("Search");
		LayoutParams btp = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		bt.setLayoutParams(btp);

		ll.addView(et);
		ll.addView(bt);
		et.setLayoutParams(etp);
		bt.setOnClickListener(this);

		//main
		scroll = new ScrollView(this);
		scroll.setBackgroundColor(Color.rgb(200, 200, 0));
		scroll.setVerticalFadingEdgeEnabled(false);
		scroll.setVerticalScrollBarEnabled(false);
		 bottom = new LinearLayout(this);
		 bottom.setOrientation(LinearLayout.VERTICAL);
		 bottom.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		 LayoutParams.MATCH_PARENT));

		rl.addView(ll);
		RelativeLayout.LayoutParams scrollp = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		scrollp.addRule(RelativeLayout.BELOW, 1);

		 scroll.addView(bottom);
		rl.addView(scroll, scrollp);

		setContentView(rl);
	}

	@Override
	public void onClick(View v) {
		String numStr = et.getText().toString().trim();
		if (numStr == null | numStr.equals("")) {
			Toast.makeText(this, "不能为空！", Toast.LENGTH_SHORT).show();
			return;
		}
		int num = Integer.valueOf(numStr);
		if (num > 10 || num < 1) {
			Toast.makeText(this, "请输入1-10以内的数字...", Toast.LENGTH_SHORT).show();
		} else {
			bottom.removeAllViews();
			for (int i = 0; i < num; i++) {
				TextView tv = new TextView(this);
				tv.setText("第 " + (i + 1) + " 个TextView");
				tv.setTextSize(15);
				tv.setGravity(Gravity.CENTER);
				tv.setTextColor(Color.rgb(255, 0, 0));
				LayoutParams tvp = new LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.WRAP_CONTENT);
				tv.setLayoutParams(tvp);
				bottom.addView(tv, i);
			}
		}

	}
}
