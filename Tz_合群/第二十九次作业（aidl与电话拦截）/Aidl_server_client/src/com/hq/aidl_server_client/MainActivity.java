package com.hq.aidl_server_client;

import com.hq.service.aidl.IDog;
import com.hq.service.aidl.MyDog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {
	MyServiceConnection conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void bind(View v) {
		Intent intent = new Intent();
		intent.setAction("com.hq.aidl");
		this.bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}

	private class MyServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i("INFO", "远程服务连接成功");
			IDog asInterface = IDog.Stub.asInterface(service);
			try {
				MyDog mydog = asInterface.getMyDog();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (conn != null) {
			this.unbindService(conn);
		}
	}

}
