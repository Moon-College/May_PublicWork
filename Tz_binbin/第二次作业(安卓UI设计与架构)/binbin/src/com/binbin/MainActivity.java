package com.binbin;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * 
 * @author wangbin
 *
 */
public class MainActivity extends ActionBarActivity {

	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=MainActivity.this;
		
		LinearLayout layout=new LinearLayout(context);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		EditText et=new EditText(context);
		Button btn=new Button(context);
		LinearLayout.LayoutParams llp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1);
		et.setLayoutParams(llp);

		LinearLayout.LayoutParams llp2=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,0);
		btn.setLayoutParams(llp2);
		btn.setText("搜索");
		layout.addView(et,0);
		layout.addView(btn,1);
		setContentView(layout);
	}

}
