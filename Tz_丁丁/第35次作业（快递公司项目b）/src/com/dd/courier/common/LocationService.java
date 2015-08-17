package com.dd.courier.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.dd.courier.bean.request.RequestCourierLocation;
import com.dd.courier.http.HttpRequest;
import com.dd.courier.utils.MyDataUtils;
import com.dd.courier.utils.MyLog;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LocationService extends Service implements BDLocationListener{
	//通过service每5分钟更新坐标到服务端
	private Timer timer = new Timer();
	private LocationClient client;
	private TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//开启定位
			initBdLocation();
			MyLog.d("locationListener start");
		}

		
	};
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//开启定时器进行定时任务
		client = new LocationClient(this);
		timer.schedule(task, 0, MyConstants.LOCATION_PERIOD);
	}
	
	private void initBdLocation() {
		// TODO Auto-generated method stub
		
		LocationClientOption option = new LocationClientOption();
        // 定位模式(高精度)
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // 返回结果
        option.setCoorType("bd09ll");
        // 请求间隔
        option.setScanSpan(5000);
        // 定位的地址信息
        option.setIsNeedAddress(true);
        // 设备方向
        option.setNeedDeviceDirect(true);
        // 打开GPS
        option.setOpenGps(true);
        client.setLocOption(option);//设置定位参数
        client.registerLocationListener(this);
        client.start();//开启
	}

	
	//定位成功以后会返回定位的信息
	public void onReceiveLocation(BDLocation arg0) {
		if(arg0 == null){
			return;
		}
		double latitude = arg0.getLatitude();//维度
		double longitude = arg0.getLongitude();//经度
		MyLog.d("latitude:"+latitude+"\n"+"longitude:"+longitude);
		String addr = arg0.getAddrStr();//获取地址
		MyLog.d("addr:"+addr);
		//第一，保存当前位置到xml
		//第二，上传给服务器
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MyConstants.ADDR, addr);
		MyDataUtils.putData(this, MyConstants.LOC, map);
		String userName = (String) MyDataUtils.getData(this, MyConstants.ACCOUNT, MyConstants.USER_NAME, String.class);
		String password = (String) MyDataUtils.getData(this, MyConstants.ACCOUNT, MyConstants.PASSWORD, String.class);
		String token = (String) MyDataUtils.getData(this, MyConstants.PUSH, MyConstants.TOKEN, String.class);
		RequestCourierLocation courierLocation = new RequestCourierLocation(String.valueOf(longitude), String.valueOf(latitude), userName, password, 3, token);
		HttpRequest request = MyApplication.getHttpRequestInstance();
		if(userName.equals("")||password.equals("")){
			return;
		}
		//发送坐标给服务端
		request.doQuestByPostMethod(MyConstants.LOCATION, courierLocation, false, null);
		client.unRegisterLocationListener(LocationService.this);//关闭定位
		MyLog.d("locationListener stop");
	}
	
}
