package com.decent.aidlserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.decent.aidlserver.bean.Info;

public class DecentAidlServie extends Service {
	private Info mInfo;
	private DecentAidl mDa;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d("INFO", "into onBind");
		if (mDa == null) {
			mDa = new DecentAidl();
		}
		return mDa;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.d("INFO", "into onCreate !!!");
		if (mInfo == null) {
			mInfo = new Info(666, "ÏûÏ¢from DecentAidlServer 666");
		}
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.d("INGO", "into onStart");
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	private class DecentAidl extends IDecentInfoService.Stub {

		@Override
		public int getPid() throws RemoteException {
			// TODO Auto-generated method stub
			return 888;
		}

		@Override
		public Info getInfo() throws RemoteException {
			// TODO Auto-generated method stub
			Log.d("INFO",
					"into getInfo mInfo mInfo.getMessage():"
							+ mInfo.getMessage());
			return mInfo;
		}

		@Override
		public void setInfo(Info info) throws RemoteException {
			// TODO Auto-generated method stub
			Log.d("INFO", "new info come in message=" + info.getMessage());
			mInfo = info;
			//mInfo.setMessage(info.getMessage());
		}

	}
}
