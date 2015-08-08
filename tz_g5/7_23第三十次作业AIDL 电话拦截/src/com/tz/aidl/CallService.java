package com.tz.aidl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.android.internal.telephony.ITelephony;

import java.lang.reflect.Method;

/**
 * Created by qinhan on 15/7/24.
 */
public class CallService extends Service {

    private TelephonyManager telephonyManager;

    private PhoneStateListener listener;

    private ITelephony mITelephony;

    private String number;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        Log.d("service", "create");
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        listener = new MyPhoneStateListener();
        telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        number = telephonyManager.getLine1Number();
        try {
            initITelephony();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("number", number);

        try {
            call("110");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:

                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    //监听电话叫起的状态
                    Log.i("INFO", "phone ringing");
                    if (incomingNumber.equals("18629324792")) {
                        try {
                            endCall(incomingNumber);
                            call(number);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void initITelephony() throws Exception{
        Class<?> forName = Class.forName("android.os.ServiceManager");
        Method method = forName.getMethod("getService", String.class);
        IBinder ibinder = (IBinder) method.invoke(null, Context.TELEPHONY_SERVICE);
        mITelephony  = ITelephony.Stub.asInterface(ibinder);
    }


    private void endCall(String incomingNumber) throws Exception {
        //反射
        mITelephony.endCall();//挂掉电话
        Toast.makeText(CallService.this, "成功拦截电话：" + incomingNumber, Toast.LENGTH_SHORT).show();
    }

    private void call(String number)throws Exception {
        Class mClass = mITelephony.getClass();
        Method call =null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR2) {
            call = mClass.getMethod("call", String.class, String.class);
            call.invoke(mITelephony, "com.tz.aidl", "10010");
        } else {
            call = mClass.getMethod("call", String.class);
            call.invoke(mITelephony, "10010");
        }
    }
}
