package com.casit.hc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ReflectionActivity extends Activity {
    /** Called when the activity is first created. */
   private TextView text1 ,text2 ,text3 ,text4 ,text5 ;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		initViews(this);
    }
}