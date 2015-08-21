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
		// 创建timer和timerTask
		super.onCreate();
		// 创建timer和timerTask
		mReportTimer = new Timer();
		mLocationClient = new LocationClient(this);
		mReportTimerTask = new TimerTask() {
			@Override
			public void run() {
				initLocationClient();
				DecentLogUtil.d("mLocationClient start");
			}
		};
		// 每xxx分钟执行一次，上报坐标任务
		mReportTimer.schedule(mReportTimerTask, 0,
				DecentConstants.LOCATION_REP_PERIOD);
		DecentLogUtil.d("LocationReportService onCreate finished");
	}

	private void initLocationClient() {
		if (mOption == null) {
			mOption = new LocationClientOption();
			// 初始化百度定位的那些参数
			mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
			// 请求间隔每20秒钟一次,如果请求成功了会在回调onReceiveLocation里面去stop掉的
			mOption.setScanSpan(20 * 1000);
			// 返回结果
			mOption.setCoorType("bd09ll");
			// 定位的地址信息
			mOption.setIsNeedAddress(true);
			// 设备方向
			mOption.setNeedDeviceDirect(true);
			// 打开GPS
			mOption.setOpenGps(true);
			// 设置回调函数
		}
		mLocationClient.setLocOption(mOption);
		// 设置定位回调函数,需要实现onReceiveLocation，在这个里面获得定位信息
		mLocationClient.registerLocationListener(this);
		// 开始定位
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
		 * 让 mReportTimerTask从timer的队列里面出来
		 */
		if (mReportTimerTask != null) {
			mReportTimerTask.cancel();
			mReportTimerTask = null;
			DecentLogUtil.d("mReportTimerTask canceled");
		}
		/*
		 * mReportTimer如果不调用cancel的话，会常驻内存，javadoc里面是这样写的 When a timer is no
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
		 * 使用httpRequest上报信息
		 */
		// 获取经纬度+地址字符串
		double longtitude = location.getLongitude();// 经度
		double latitude = location.getLatitude();// 维度
		String addrStr = location.getAddrStr();// 地址字符串

		// 保存地址信息到sharedpreference
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(DecentConstants.ADDR, addrStr);
		map.put(DecentConstants.LONGTITUDE, String.valueOf(longtitude));
		map.put(DecentConstants.LATITUDE, String.valueOf(latitude));
		DecentLogUtil.d("into onReceiveLocation longtitude=" + longtitude
				+ ",latitude=" + latitude + ",addrStr=" + addrStr);
		MyDataUtils.putData(this, DecentConstants.LOC, map);

		// 创建上报location的request类
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
		// 上报http
		HttpRequest request = new HttpRequest(this);
		request.doQuestByPostMethod(DecentConstants.LOCATION_URL, report,
				false, this);

		// 也许需要发广播,通知界面位置已经更新

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
		// 这次发送成功,就把把定位关闭掉，否则让定位继续
		if (mLocationClient != null) {
			if (mLocationClient.isStarted()) {
				mLocationClient.stop();
			}
		}		
	}

}
