package com.tz.volume;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.tz.volmue.R;

public class MyActivity extends Activity {

    private VolumeView mVolumeView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mVolumeView = (VolumeView) findViewById(R.id.vol);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean flag=mVolumeView.changeVolume(keyCode);
        return flag?flag:super.onKeyDown(keyCode, event);
    }
}
