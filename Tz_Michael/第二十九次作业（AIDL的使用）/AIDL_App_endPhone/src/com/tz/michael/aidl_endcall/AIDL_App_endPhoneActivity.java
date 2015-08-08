package com.tz.michael.aidl_endcall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AIDL_App_endPhoneActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void myOnClick(View v){
    	Intent intent=new Intent(this,MyService.class);
    	startService(intent);
    }
}