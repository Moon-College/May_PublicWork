package com.casit.hc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityMainTestActivity extends Activity {
    /** Called when the activity is first created. */
    Button button;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button) findViewById(R.id.bt);
        
    }
    public void jumpto(View view){
    	Log.i("INFO", "Jump");
    	Intent intent = new Intent();
    //	intent.setClass(this, SecondActivity.class);
    	intent.setAction("android.intent.action.MAIN2");
   // 	intent.addCategory("android.intent.category.DEFAULT");
    	startActivity(intent);
    }
}