package com.casit.hc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MyfragmentActivity extends FragmentActivity {
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i("INFO", "Activityoncreate");
        setContentView(R.layout.main);

        
    }	
	@Override
	public void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		Log.i("INFO", "ActivityonAttach");
	}
	@Override
	protected void onStart() {
		
		super.onStart();
		Log.i("INFO", "Activityonstart");// TODO Auto-generated method stub
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		super.onResume();
		Log.i("INFO", "ActivityonResume");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		super.onPause();
		Log.i("INFO", "ActivityonPause");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub

		super.onStop();
		Log.i("INFO", "ActivityonStop");
	}
	@Override
	protected void onDestroy() {

		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("INFO", "ActivityonDestroy");
	}
	
}