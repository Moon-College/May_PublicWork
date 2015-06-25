package com.xigua.hideintent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class JumpActivity extends Activity {
	
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	RelativeLayout rLayout = new RelativeLayout(this);
    	LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    	rLayout.setLayoutParams(params);
    	
    	TextView text = new TextView(this);
    	RelativeLayout.LayoutParams params2= new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    	params2.addRule(RelativeLayout.CENTER_IN_PARENT);
    	text.setLayoutParams(params2);
    	text.setText("隐式跳转界面");
    	
    	rLayout.addView(text);
    	setContentView(rLayout);
    }

}
