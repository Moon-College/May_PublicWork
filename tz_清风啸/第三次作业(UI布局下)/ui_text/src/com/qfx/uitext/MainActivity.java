package com.qfx.uitext;


import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private EditText et;
	private LinearLayout contentLL;
	private int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout rl = new RelativeLayout(this);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, 
				ViewGroup.LayoutParams.FILL_PARENT);
		rl.setLayoutParams(params);
		//顶部线性布局
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		llParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		ll.setLayoutParams(llParams);
		et = new EditText(this);
		LinearLayout.LayoutParams etParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT,
				1);
		etParams.gravity = Gravity.CENTER_VERTICAL;
		et.setLayoutParams(etParams);
		et.setHint(R.string.et_hint_text);
		et.setInputType(InputType.TYPE_CLASS_NUMBER);
		//顶部的按钮
		Button btn = new Button(this);
		LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT);
		btn.setLayoutParams(btnParams);
		btn.setText(R.string.add);
		btn.setPadding(20, 20, 20, 20);
		btn.setTextSize(18);
		contentLL = new LinearLayout(this);
		contentLL.setOrientation(LinearLayout.VERTICAL);
		RelativeLayout.LayoutParams conParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		conParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		conParams.setMargins(20, 80, 20, 20);
		contentLL.setLayoutParams(conParams);
		ll.addView(et, 0);
		ll.addView(btn, 1);
		rl.addView(ll, 0);
		rl.addView(contentLL, 1);
		setContentView(rl);
		btn.setOnClickListener(this);
		
	}

	

	@Override
	public void onClick(View v) {
		String str = et.getText().toString();
		if (!isEmpty(str)) {
			int num = Integer.parseInt(str);
			if (num > 0) {
				for (int i = 0; i < num; i++) {
					TextView tv = new TextView(this);
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.FILL_PARENT, 
							LinearLayout.LayoutParams.WRAP_CONTENT);
					
					tv.setLayoutParams(params);
					tv.setPadding(20, 10, 20, 10);
//					tv.setBackgroundColor(color)
					tv.setText(String.valueOf(count));
					contentLL.addView(tv, count);
					count++;
				}
			}
		}
	}
	

	/**
	 * 判断字符串或对象是否为空
	 * @param str
	 * @return  是空返回true,否则返回false
	 */
	public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}
