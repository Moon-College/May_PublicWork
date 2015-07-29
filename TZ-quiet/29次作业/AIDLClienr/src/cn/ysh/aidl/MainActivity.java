package cn.ysh.aidl;

import cn.ysh.aidlclienr.R;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public class MainActivity extends Activity {

	private MyServiceConnection conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		conn = new MyServiceConnection();
	}
	/**
	 * 绑定服务
	 * @param v
	 */
	public void bind(View v){
		Intent intent = new Intent("cn.ysh.server");
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onDestroy() {
		if(conn != null){
			this.unbindService(conn);
		}
		super.onDestroy();
	}
	
	private class MyServiceConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			IUser iuser = IUser.Stub.asInterface(service);
			try {
				int age = iuser.getAge();
				Log.i("INFO", age+"");
				MyOrder order = iuser.getMyOrder();
				Log.i("INFO", "单号:"+order.getOrderNumber()+"---地址:"+order.getAddress());
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
}
