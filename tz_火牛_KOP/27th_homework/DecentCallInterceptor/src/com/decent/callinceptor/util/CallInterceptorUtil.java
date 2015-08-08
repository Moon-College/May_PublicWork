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
	 * ͨ��������phone service��Ӧ��ITelephony
	 * 
	 * @return phone service��Ӧ��ITelephony
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
	 * ����callNumber��Ӧ�ĵ绰����
	 * 
	 * @param callNumber
	 *            �绰����
	 * @return �Ƿ����سɹ�
	 */
	public static boolean endCall(String callNumber) {
		boolean result = false;
		// ʹ�÷���ķ������ITelephony��ʵ��
		// ITelephony phone =
		// ITelephony.Stub.asInterface(ServiceManager.getService("phone"));
		/*
		 * 1��ServiceManager��hidden����Ҫ������ 2����� ServiceManager֮����getService��̬����
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
		// Ȼ�����endcall
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
