package com.xigua.aidl_client;

import com.xigua.aidl.IUser;
import com.xigua.aidl.MyOrder;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener, ServiceConnection {

	private Button btn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.button1);
	    btn1.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		case R.id.button1:
			Intent intent = new Intent();
			intent.setAction("com.xigua.aidl_service");
			this.bindService(intent, this, Context.BIND_AUTO_CREATE);
			break;

		default:
			break;
		}
	}
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		IUser myInterface = IUser.Stub.asInterface(service);
		try {
			String myName = myInterface.getName();
			MyOrder order = myInterface.getMyOrder();
			Log.i("INFO", myName);
			Log.i("INFO", order.getOrderNo());
			Log.i("INFO", order.getOrderPrice()+"");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onServiceDisconnected(ComponentName name) {
		
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
        unbindService(this);
	}
	
}
