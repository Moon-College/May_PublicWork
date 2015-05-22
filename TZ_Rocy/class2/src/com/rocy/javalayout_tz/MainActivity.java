package com.rocy.javalayout_tz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll =new LinearLayout(this);
		LinearLayout.LayoutParams llparams =new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(llparams);
		EditText et =new EditText(this);
		LinearLayout.LayoutParams etparams =new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
		etparams.weight=1.0f;
		et.setLayoutParams(etparams);
		Button btn =new Button(this);
		LinearLayout.LayoutParams btnparams =new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		btn.setLayoutParams(btnparams);
		ll.addView(et);
		ll.addView(btn);
		setContentView(ll);
	}
}
