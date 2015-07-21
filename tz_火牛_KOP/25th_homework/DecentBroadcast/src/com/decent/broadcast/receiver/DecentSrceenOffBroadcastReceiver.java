package com.decent.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.decent.broadcast.util.MemoryMangerUtil;
import com.decent.decentutil.DecentLogUtil;

public class DecentSrceenOffBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "DecentSrceenOffBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		DecentLogUtil.d(TAG, "SrceenOff received start memory clear thread!!!");
		MemoryClearThread mcThread = new MemoryClearThread(context);
		mcThread.run();
	}

	private class MemoryClearThread implements Runnable {

		private Context context;

		public MemoryClearThread(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			long memoryBeforeClear = MemoryMangerUtil
					.getAvailMemorySize(context);
			MemoryMangerUtil.killAllRunningProcess(context,-1);
			/*
			 * 在kill了之后，因为是异步的，所以需要等待
			 */
		    try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long memoryAfterClear = MemoryMangerUtil
					.getAvailMemorySize(context);
			long memoryCleared = (memoryAfterClear - memoryBeforeClear);
			DecentLogUtil.d(TAG, memoryCleared + "btyes Memory cleared!!!");
			DecentLogUtil.d(TAG, memoryCleared/(1024*1024) + "Mb Memory cleared!!!");
		}

	}
}
