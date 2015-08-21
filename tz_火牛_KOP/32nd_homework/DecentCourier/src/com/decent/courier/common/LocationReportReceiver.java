package com.decent.courier.common;

import com.decent.courier.utils.DecentLogUtil;
import com.decent.courier.utils.ServiceUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LocationReportReceiver extends BroadcastReceiver {

	
	public LocationReportReceiver() {
		super();
		// TODO Auto-generated constructor stub
		DecentLogUtil.d("init LocationReportReceiver!!!");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		DecentLogUtil.d(LocationReportService.class.getName()
				+ " received action:" + intent.getAction());
		// �ж��Ǹ������Ƿ��������У����û���������У���������
		if (!ServiceUtil.isServiceRunning(context,
				LocationReportService.class.getName())) {
			Intent service = new Intent();
			service.setClass(context, LocationReportService.class);
			context.startService(service);
			DecentLogUtil.d("LocationReportService restart!!!");
		} else {
			DecentLogUtil.d("LocationReportService service is ok!!!");
		}
	}

}
