package com.tz.two;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.R.integer;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 第四课：作业二
 * 
 * @author 罗健波 2015-5-22
 * 
 */
public class FourOperationTwo extends Activity {
	private int maxNum = 40;
	private int minNum = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 最外层
		final LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		// 顶部包裹
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		// 兄弟控件
		final LinearLayout tvLayout = new LinearLayout(this);
		tvLayout.setOrientation(LinearLayout.VERTICAL);
		// 包裹内子控件
		final EditText et = new EditText(this);
		Button btn = new Button(this);
		// 控件属性设置
		linearLayout.addView(layout, WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT);
		linearLayout.addView(tvLayout, WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT);
		et.setLayoutParams(new LayoutParams(0,
				WindowManager.LayoutParams.WRAP_CONTENT, 1.0f));
		et.setHint("请输入30以内的数字");
		btn.setText("生成文本");
		et.setInputType(InputType.TYPE_CLASS_NUMBER);
		layout.addView(et);
		//控制文本输入的值在 数字 30以内
		et.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (start >= 1) {
					int num2 = Integer.parseInt(s.toString());
					if (num2 >= 30) {
						s=29+"";
					}
					return;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s != null && !s.equals("")) {
					try {
					int num1 = Integer.parseInt(s.toString());
					if (num1 >= 30 ) {
						et.setText(29+"");
						return;
					}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		layout.addView(btn, WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT);
		btn.setOnClickListener(new OnClickListener() {
			//点击事件，避免重复输出文本
			@Override
			public void onClick(View v) {
				try {
					if (et.getText().length() != 0) {
						int etNum = Integer.parseInt(et.getText().toString());
						if (etNum != 0) {
							tvLayout.removeAllViews();
						}
						for (int i = 0; i < etNum; i++) {
							TextView tv = new TextView(FourOperationTwo.this);
							tv.setText(i + 1 + "\t\t"+getDateTime());
							tv.setBackgroundColor(Color.GREEN);
							tvLayout.addView(tv,
									WindowManager.LayoutParams.MATCH_PARENT,
									WindowManager.LayoutParams.WRAP_CONTENT);
						}
					}
				} catch (Exception e) {
				}
			}

			private String getDateTime() {
				String dateTime = null;
				SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateTime = sFormat.format(new Date(System.currentTimeMillis()));
				return dateTime;
			}
		});
		setContentView(linearLayout);
	}
	

}
