package com.yl.absxbylayout;

import com.yl.layout.absxbylayout.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.i("INFO", "start1");
        super.onCreate(savedInstanceState);
        Log.i("INFO", "start2");
        setContentView(R.layout.main);
        Log.i("INFO", "start3");
    }
}