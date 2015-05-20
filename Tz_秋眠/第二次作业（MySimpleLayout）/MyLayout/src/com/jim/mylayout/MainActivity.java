package com.jim.mylayout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(layoutParams);
		
		EditText editText = new EditText(this);
		LayoutParams etLayoutParams = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT, 2);
		editText.setLayoutParams(etLayoutParams);
		editText.setHint("ËÑË÷");
		layout.addView(editText, 0);
		
		Button button = new Button(this);
		LayoutParams btnLayoutParams = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT, 1);
		button.setLayoutParams(btnLayoutParams);
		button.setText("ËÑË÷");
		layout.addView(button, 1);
		
		setContentView(layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
