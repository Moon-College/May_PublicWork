package com.lin.myobserver.app.Observer;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/6/2.
 */
public class RealClickObserver extends ClickObserver {
    @Override
    public void onClick() {
        Log.i("info", "我被点击了");
    }
}
