package com.tz.layout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		LinearLayout ll = new LinearLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		ll.setLayoutParams(params);
		
		EditText edit = new EditText(this);
		LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1);
		edit.setLayoutParams(params2);
		ll.addView(edit,0);
		
		Button btn = new Button(this);
		LayoutParams params3 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0);
		btn.setLayoutParams(params3);
		btn.setText("ËÑË÷");
		ll.addView(btn,1);
		setContentView(ll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
