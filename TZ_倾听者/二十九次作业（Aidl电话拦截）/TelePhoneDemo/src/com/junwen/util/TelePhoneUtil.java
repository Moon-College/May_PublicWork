package com.junwen.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.os.IBinder;

import com.android.internal.telephony.ITelephony;

public class TelePhoneUtil {
	
	/**
	 * 打开拨号盘
	 * @param number
	 * @throws Exception 
	 */
	public static void openCallScreen(String number) throws Exception{
		ITelephony telePhone = getTelePhone();
		telePhone.dial(number);
	}
;	/**
	 * 后台打电话
	 */
	public static void call(String number){
		ITelephony telePhone = getTelePhone();
		Class<? extends ITelephony> class1 = telePhone.getClass();
		try {
			Method declaredMethod = class1.getDeclaredMethod("call", String.class);
			declaredMethod.invoke(telePhone, number);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取出电话的系统服务接口
	 * @return
	 */
	public static ITelephony getTelePhone(){
		ITelephony asInterface = null;
		Class<?> forName;
		try {
			forName = Class.forName("android.os.ServiceManager");
			Method method = forName.getDeclaredMethod("getService", String.class);
			method.setAccessible(true);
			IBinder invoke = (IBinder) method.invoke(null, Context.TELEPHONY_SERVICE);
			asInterface = ITelephony.Stub.asInterface(invoke);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return asInterface;
	}
}
