package com.decent.callinceptor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.decent.callinceptor.service.CallInceptorService;

public class DecentCallInterceptorActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent service = new Intent();
        service.setClass(this, CallInceptorService.class);
        startService(service);
    }
}