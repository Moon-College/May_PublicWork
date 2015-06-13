package com.tz.vip.weixinslid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by qinhan on 15/6/13.
 */
public class ValueAnimateActivity extends Activity {
    private AnimateView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate);
        animationView = (AnimateView) findViewById(R.id.animationview);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.animationview:
                animationView.start();
                break;
        }
    }
    
}
