package com.tz.michael.aidl_client;

import com.tz.michael.aidl_server.bean.ExampleBean;
import com.tz.michael.aidl_server.bean.IExample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class AIDL_AppClientActivity extends Activity {
	
	private ServiceConnection conn=new ServiceConnection() {
		
		private IExample asInterface;

		public void onServiceDisconnected(ComponentName name) {
			
		}
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			asInterface = IExample.Stub.asInterface(service);
			try {
				String str=asInterface.getStringFromServer();
				Log.i("str--", str);
				ExampleBean exampleBean=asInterface.getExampleBean();
				Log.i("eb--", exampleBean.toString());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void myOnClick(View v){
    	Intent intent=new Intent();
    	intent.setAction("server.aidl.michael.tz.com");
    	bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if(conn!=null){
    		unbindService(conn);
    	}
    }
}