package com.binbin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 
 * @author wangbin
 * 
 */
public class DynamicAddingActivity extends Activity implements OnClickListener {

	// 主容器
	private LinearLayout mainLL;
	// 顶部容器
	private LinearLayout topLayout;
	// 内容容器
	private LinearLayout contentLayout;
	EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mainLL = new LinearLayout(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mainLL.setLayoutParams(lp);
		mainLL.setOrientation(LinearLayout.VERTICAL);

		topLayout = new LinearLayout(this);

		contentLayout = new LinearLayout(this);
		contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        


		topLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		et = new EditText(this);
		Button btn = new Button(this);
		LinearLayout.LayoutParams llp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et.setLayoutParams(llp);
		et.setHint("请输入数字");

		LinearLayout.LayoutParams llp2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0);
		btn.setLayoutParams(llp2);
		btn.setText("创建");

		topLayout.addView(et, 0);
		topLayout.addView(btn, 1);

		mainLL.addView(topLayout, 0);
		mainLL.addView(contentLayout, 1);

		setContentView(mainLL);
		btn.setOnClickListener(this);

	}

	public void onClick(View v) {
		try {
			String numStr = et.getText().toString();
			if (null != numStr && numStr != "") {
				contentLayout.removeAllViews();
				int num = Integer.valueOf(numStr.trim());
				for (int i = 0; i < num; i++) {
					TextView t = new TextView(DynamicAddingActivity.this);
					t.setGravity(Gravity.CENTER);
					t.setText(i + "");
					contentLayout.addView(t);
				}
			}
		} catch (Exception ex) {

		}
	}
}
