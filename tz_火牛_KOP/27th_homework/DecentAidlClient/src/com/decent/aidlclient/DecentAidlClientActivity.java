package com.decent.aidlclient;

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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.decent.aidl.client.R;
import com.decent.aidlserver.bean.Info;
import com.decent.aidlserver.service.IDecentInfoService;

public class DecentAidlClientActivity extends Activity implements OnClickListener {
	public static final String TAG = "DecentAidlClientActivity";
	private IDecentInfoService decentAidlServie;
	private MyConnection mMyConnection;
	private EditText mSetMessageEt;
	private Button mSetMessageBt;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mSetMessageEt = (EditText) findViewById(R.id.setMessageEt);
		mSetMessageBt = (Button) findViewById(R.id.setMessageBt);
		mSetMessageBt.setOnClickListener(this);
		Intent intent = new Intent();
		intent.setAction("com.decnt.aidl.openservice");
		mMyConnection = new MyConnection();
		bindService(intent, mMyConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		unbindService(mMyConnection);
		super.onDestroy();
	}

	private class MyConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			decentAidlServie = IDecentInfoService.Stub.asInterface(service);
			try {
				//
				Log.d(TAG, "id=" + decentAidlServie.getInfo().getId());
				Log.d(TAG, "message=" + decentAidlServie.getInfo().getMessage());
				Log.d(TAG, "pid=" + decentAidlServie.getPid());

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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setMessageBt:
			String newMessage = mSetMessageEt.getText().toString().trim();
			Info info = new Info(666,newMessage);
			try {
				//更新services里面的变量
				if(decentAidlServie!=null){
				    decentAidlServie.setInfo(info);
				}else{
					Toast.makeText(this, "decentAidlServie is null", Toast.LENGTH_LONG).show();
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}
}