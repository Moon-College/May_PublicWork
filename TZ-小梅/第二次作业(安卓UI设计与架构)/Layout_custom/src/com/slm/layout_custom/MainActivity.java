package com.slm.layout_custom;

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
		LinearLayout ll = new LinearLayout(this);
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		ll.setLayoutParams(lp);
		EditText et_content = new EditText(this);
		et_content.setHint("请输入要搜索的内容");
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT,1);
		et_content.setLayoutParams(llp);
		Button bt_search = new Button(this);
		bt_search.setWidth(LayoutParams.WRAP_CONTENT);
		bt_search.setHeight(LayoutParams.WRAP_CONTENT);
		bt_search.setText("搜索");
		ll.addView(et_content,0);
		ll.addView(bt_search,1);
		setContentView(ll);
		//setContentView(R.layout.activity_main);
	}
}
