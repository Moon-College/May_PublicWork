package com.lin.mytzwork;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class MainActivity extends Activity implements SurfaceHolder.Callback, View.OnTouchListener {
    private Activity activity;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private Drawer drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        surfaceView = new SurfaceView(activity);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        setContentView(surfaceView);
        surfaceView.setOnTouchListener(this);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawer = new Drawer(holder, surfaceView.getWidth(), surfaceView.getHeight(), activity);
        drawer.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawer.setIsStart(false);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (drawer.gemeStatue) {
            case READY:
                drawer.initAttrs();
                drawer.getBird().setY(-drawer.getBird().getHeight());
                drawer.setGemeStatue(Drawer.GemeStatues.RUNNING);
                break;
            case RUNNING:
                if (!drawer.isDrop()) {
                    drawer.getBird().setySpeed(-10);
                }
                break;
            case OVER:
                drawer.setGemeStatue(Drawer.GemeStatues.READY);
                break;
        }
        return true;
    }
}
