package com.tz_layout;

import android.R.color;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private static final int NUMBER_ONE = 1;
	private static final int NUMBER_TWO = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		LinearLayout llyout = new LinearLayout(this);
		llyout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		llyout.setBackgroundColor(color.white);
		llyout.setOrientation(LinearLayout.HORIZONTAL);
		setContentView(llyout);
		
		EditText et = new EditText(this);
		et.setId(NUMBER_ONE);
		et.setHint("«Î ‰»Î√‹¬Î");
		LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);		
		llyout.addView(et, params);
		
		Button btn = new Button(this);
		btn.setId(NUMBER_TWO);
		btn.setText("»∑∂®");

		LinearLayout.LayoutParams  btnParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);				
		llyout.addView(btn, btnParams);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
