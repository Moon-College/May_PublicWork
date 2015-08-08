package com.tz.michael.activity;

import com.tz.michael.activity.MyServiceTest.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class ServiceProjectActivity extends Activity {
	
	private MyServiceTest.MyBinder binder;
	private MyServiceTest myServiceTest;
	
	private ServiceConnection conn=new ServiceConnection() {
		
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder=(MyBinder) service;
			myServiceTest=binder.getService();
		}
	};
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent=new Intent();
        intent.setAction("michael.tz.com");
        startService(intent);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
    
    public void myOnclick(View v){
    	switch (v.getId()) {
		case R.id.btn_pause:
			myServiceTest.pauseMusic();
			break;
		case R.id.btn_continue:
			myServiceTest.continueMusic();
			break;
		case R.id.btn_play:
			myServiceTest.playMusic();
			break;
		default:
			break;
		}
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if(conn!=null){
    		unbindService(conn);
    	}
    }
    
}