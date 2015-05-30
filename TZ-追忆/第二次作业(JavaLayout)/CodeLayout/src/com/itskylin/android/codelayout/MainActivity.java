package com.itskylin.android.codelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);
		layout.setOrientation(LinearLayout.HORIZONTAL);

		EditText edit = new EditText(this);
		LayoutParams pe = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT,1);
		edit.setHint("请输入搜索内容.....");

		Button button = new Button(this);
		LayoutParams pb = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		button.setLayoutParams(pb);
		button.setText("Serach");

		layout.addView(edit,0);
		layout.addView(button,1);
		
		edit.setLayoutParams(pe);
		
		setContentView(layout);
	}
}