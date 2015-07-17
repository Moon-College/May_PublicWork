package com.tz.michael.activity;

import com.tz.michael.bean.DoorConstants;
import com.tz.michael.bean.DoorFactory;
import com.tz.michael.bean.FingerPrintIdentifyDoor;

import android.app.Activity;
import android.os.Bundle;

public class FramworkBaseAndLifePhilosophyActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FingerPrintIdentifyDoor fingerPrintIdentifyDoor=(FingerPrintIdentifyDoor) DoorFactory.newInstance(DoorConstants.FINGERPRINT);
        fingerPrintIdentifyDoor.lock();
        fingerPrintIdentifyDoor.isFingerPrintRight("ssdsj".getBytes());
        fingerPrintIdentifyDoor.unLock();
        
    }
}