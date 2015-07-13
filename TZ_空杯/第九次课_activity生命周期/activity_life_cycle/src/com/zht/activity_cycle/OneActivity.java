package com.zht.activity_cycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class OneActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        System.out.println("OneActivity onCreate");
    }

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("OneActivity onStart");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		System.out.println("OneActivity onRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("OneActivity onResume");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("OneActivity onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("OneActivity onStop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("OneActivity onDestroy");
	}
    public void jump(View view){
    		Intent intent = new Intent(this,OtherActivity.class);
    		startActivity(intent);
    }

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		event.getX();
		System.out.println("你触摸了:"+event.getX()+" "+event.getY()+"这个点");
		return super.onTouchEvent(event);

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		super.onKeyDown(keyCode, event);
		System.out.println("onKeyDown"+keyCode);
		event.startTracking();
		return true;
				
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		System.out.println("onKeyUp");
		boolean flag = event.isLongPress();
		return super.onKeyUp(keyCode, event);
	
	}
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		super.onKeyLongPress(keyCode, event);
		System.out.println("onKeyLongPress");
		return true;
	}
	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		System.out.println("onKeyMultiple");
		return super.onKeyMultiple(keyCode, repeatCount, event);
	}
    
}