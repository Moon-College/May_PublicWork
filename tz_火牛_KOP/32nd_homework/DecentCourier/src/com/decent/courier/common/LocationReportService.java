package com.decent.courier.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.decent.courier.bean.request.HttpRequestLocationReport;
import com.decent.courier.http.HttpRequest;
import com.decent.courier.listener.IHttpRequestCallback;
import com.decent.courier.utils.DecentConstants;
import com.decent.courier.utils.DecentLogUtil;
import com.decent.courier.utils.MyDataUtils;

public class LocationReportService extends Service implements
		BDLocationListener, IHttpRequestCallback {
	private Timer mReportTimer;
	private TimerTask mReportTimerTask;
	private LocationClient mLocationClient;
	private LocationClientOption mOption;

	@Override
	public void onCreate() {
		// ����timer��timerTask
		super.onCreate();
		// ����timer��timerTask
		mReportTimer = new Timer();
		mLocationClient = new LocationClient(this);
		mReportTimerTask = new TimerTask() {
			@Override
			public void run() {
				initLocationClient();
				DecentLogUtil.d("mLocationClient start");
			}
		};
		// ÿxxx����ִ��һ�Σ��ϱ���������
		mReportTimer.schedule(mReportTimerTask, 0,
				DecentConstants.LOCATION_REP_PERIOD);
		DecentLogUtil.d("LocationReportService onCreate finished");
	}

	private void initLocationClient() {
		if (mOption == null) {
			mOption = new LocationClientOption();
			// ��ʼ���ٶȶ�λ����Щ����
			mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
			// ������ÿ20����һ��,�������ɹ��˻��ڻص�onReceiveLocation����ȥstop����
			mOption.setScanSpan(20 * 1000);
			// ���ؽ��
			mOption.setCoorType("bd09ll");
			// ��λ�ĵ�ַ��Ϣ
			mOption.setIsNeedAddress(true);
			// �豸����
			mOption.setNeedDeviceDirect(true);
			// ��GPS
			mOption.setOpenGps(true);
			// ���ûص�����
		}
		mLocationClient.setLocOption(mOption);
		// ���ö�λ�ص�����,��Ҫʵ��onReceiveLocation������������ö�λ��Ϣ
		mLocationClient.registerLocationListener(this);
		// ��ʼ��λ
		mLocationClient.start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		DecentLogUtil.d("LocationReportService onStart finished");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		/*
		 * �� mReportTimerTask��timer�Ķ����������
		 */
		if (mReportTimerTask != null) {
			mReportTimerTask.cancel();
			mReportTimerTask = null;
			DecentLogUtil.d("mReportTimerTask canceled");
		}
		/*
		 * mReportTimer���������cancel�Ļ����᳣פ�ڴ棬javadoc����������д�� When a timer is no
		 * longer needed, users should call cancel(), which releases the timer's
		 * thread and other resources. Timers not explicitly cancelled may hold
		 * resources indefinitely.
		 */
		if (mReportTimer != null) {
			mReportTimer.cancel();
			mReportTimer = null;
			DecentLogUtil.d("mReportTimer canceled");
		}

		if (mLocationClient != null) {
			if (mLocationClient.isStarted()) {
				mLocationClient.stop();
			}
			mLocationClient.registerLocationListener(null);
			mLocationClient = null;
			DecentLogUtil.d("mReportTimer stopped");
		}
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		if (location == null) {
			DecentLogUtil.e("into onReceiveLocation location is null");
			return;
		}
		/*
		 * ʹ��httpRequest�ϱ���Ϣ
		 */
		// ��ȡ��γ��+��ַ�ַ���
		double longtitude = location.getLongitude();// ����
		double latitude = location.getLatitude();// ά��
		String addrStr = location.getAddrStr();// ��ַ�ַ���

		// �����ַ��Ϣ��sharedpreference
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(DecentConstants.ADDR, addrStr);
		map.put(DecentConstants.LONGTITUDE, String.valueOf(longtitude));
		map.put(DecentConstants.LATITUDE, String.valueOf(latitude));
		DecentLogUtil.d("into onReceiveLocation longtitude=" + longtitude
				+ ",latitude=" + latitude + ",addrStr=" + addrStr);
		MyDataUtils.putData(this, DecentConstants.LOC, map);

		// �����ϱ�location��request��
		String userName = MyDataUtils.getData(this, DecentConstants.USER,
				DecentConstants.USERNAME, String.class);
		String password = MyDataUtils.getData(this, DecentConstants.USER,
				DecentConstants.PASSWORD, String.class);
		String token = MyDataUtils.getData(this, DecentConstants.PUSH,
				DecentConstants.TOKEN, String.class);
		if (userName.equals("") || password.equals("")) {
			DecentLogUtil.e("report location but no username or password");
			return;
		}
		HttpRequestLocationReport report = new HttpRequestLocationReport(
				String.valueOf(longtitude), String.valueOf(latitude), userName,
				password, DecentConstants.ANDROID_DEVICE_TYPE, token);
		// �ϱ�http
		HttpRequest request = new HttpRequest(this);
		request.doQuestByPostMethod(DecentConstants.LOCATION_URL, report,
				false, this);

		// Ҳ����Ҫ���㲥,֪ͨ����λ���Ѿ�����

	}

	@Override
	public void onRequestFail(String result) {
		// TODO Auto-generated method stub
		DecentLogUtil.d("report location failed into onRequestFail result = "+result);
	}

	@Override
	public void onRequestSuccess(String result) {
		// TODO Auto-generated method stub
		DecentLogUtil.d("report location success into onRequestSuccess result = "+result);
		// ��η��ͳɹ�,�ͰѰѶ�λ�رյ��������ö�λ����
		if (mLocationClient != null) {
			if (mLocationClient.isStarted()) {
				mLocationClient.stop();
			}
		}		
	}

}
