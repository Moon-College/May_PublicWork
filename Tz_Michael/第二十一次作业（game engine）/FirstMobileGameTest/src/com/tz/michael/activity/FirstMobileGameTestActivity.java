package com.tz.michael.activity;

import com.tz.michael.utils.Drawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class FirstMobileGameTestActivity extends Activity implements Callback {
    private SurfaceView surfaceView;
	private Drawer drawer;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new SurfaceView(this);
        surfaceView.getHolder().addCallback(this);
        setContentView(surfaceView);
    }

	public void surfaceCreated(SurfaceHolder holder) {
		drawer = new Drawer(this, holder, surfaceView.getWidth(), surfaceView.getHeight());
		drawer.start();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		drawer.setStart(false);
	}
}