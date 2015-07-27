package com.decent.callinceptor.util;

import java.lang.reflect.Method;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;

import com.android.internal.telephony.ITelephony;

public class CallInterceptorUtil {
	private static final String SERVICE_MANAGER_CALSS_NAME = "android.os.ServiceManager";
	private static final String GET_SERVICE_METHOD = "getService";
	private static ITelephony mITelephony;

	/**
	 * 通过反射获得phone service对应的ITelephony
	 * 
	 * @return phone service对应的ITelephony
	 */
	public static ITelephony getITelephony() {
		if (mITelephony != null) {
			return mITelephony;
		}
		try {
			Class serviceManagerClass = Class
					.forName(SERVICE_MANAGER_CALSS_NAME);
			Method getServiceMethod = serviceManagerClass.getDeclaredMethod(
					GET_SERVICE_METHOD, String.class);
			getServiceMethod.setAccessible(true);
			IBinder iBinder = (IBinder) getServiceMethod.invoke(null,
					Context.TELEPHONY_SERVICE);
			mITelephony = ITelephony.Stub.asInterface(iBinder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mITelephony;
	}

	/**
	 * 拦截callNumber对应的电话号码
	 * 
	 * @param callNumber
	 *            电话号码
	 * @return 是否拦截成功
	 */
	public static boolean endCall(String callNumber) {
		boolean result = false;
		// 使用反射的方法获得ITelephony的实例
		// ITelephony phone =
		// ITelephony.Stub.asInterface(ServiceManager.getService("phone"));
		/*
		 * 1、ServiceManager是hidden的需要反射获得 2、获得 ServiceManager之后获得getService静态方法
		 */
		ITelephony iTelephony = getITelephony();
		if (iTelephony != null) {
			try {
				result = iTelephony.endCall();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 然后调用endcall
		return result;
	}
	
	public static void call(String callNumber){
		ITelephony iTelephony = getITelephony();
		if(iTelephony!=null){
			try {
				iTelephony.call(callNumber);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
