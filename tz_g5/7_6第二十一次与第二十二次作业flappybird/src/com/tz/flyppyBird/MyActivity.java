package com.tz.flyppyBird;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;

public class MyActivity extends Activity implements SurfaceHolder.Callback, View.OnTouchListener {

    private SurfaceView surfaceView;
    private Drawer drawer;//画家

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        surfaceView = new SurfaceView(this);
        setContentView(surfaceView);
        surfaceView.getHolder().addCallback(this);
        surfaceView.setOnTouchListener(this);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        //画板被创建好了，创建画家准备画画了
        drawer = new Drawer(this, holder, surfaceView.getWidth(), surfaceView.getHeight());
        drawer.start();

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        //画板改变了
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        //画板销毁了
        drawer.setStart(false);
    }


    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        switch (drawer.getGameStatu()) {
            case Drawer.READY:
                //ready界面点击屏幕，开始游戏
                drawer.setGameStatu(Drawer.RUNNING);
                break;
            case Drawer.RUNNING:
                //正在运行状态，鸟弹
                if (!drawer.isDrop()) {
                    drawer.getBird().setySpeed(-15);
                }
                break;
            case Drawer.OVER:
                drawer.setGameStatu(Drawer.READY);
                drawer.initAttrs();
                break;
            default:
                break;
        }
        return false;
    }
}
