package com.casit.hc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OtherActivityTestActivity extends Activity {
    /** Called when the activity is first created. */
	Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button) findViewById(R.id.bt);       
    }
    public void jumptotwo(View view){
    	Intent intent =new Intent();
    	intent.setAction("android.intent.action.MAIN2");
    	intent.addCategory("android.intent.category.DEFAULT");
    	startActivity(intent);
    }
}