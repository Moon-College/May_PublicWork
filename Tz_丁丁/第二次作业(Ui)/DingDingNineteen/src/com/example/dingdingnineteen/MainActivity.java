package com.example.dingdingnineteen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams llp = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		EditText et = new EditText(this);
		LinearLayout.LayoutParams llp1 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		et.setLayoutParams(llp1);
		ll.addView(et, 0);
		Button bt = new Button(this);
		bt.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
		bt.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
		bt.setText("ËÑË÷");
		ll.addView(bt, 1);
		setContentView(ll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
