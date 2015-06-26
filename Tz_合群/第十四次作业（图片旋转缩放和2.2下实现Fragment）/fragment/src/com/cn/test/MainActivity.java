package com.cn.test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MainActivity extends FragmentActivity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("INFO", "Activity create");
    }
    
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	Log.i("INFO", "Activity start");
    	super.onStart();
    }
    
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	Log.i("INFO", "Activity Pause");
    	super.onPause();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	Log.i("INFO", "Activity resume");
    	super.onResume();
    }
    
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	Log.i("INFO", "Activity destroy");
    	super.onDestroy();
    }
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	Log.i("INFO", "Activity stop");
    	super.onStop();
    }
}