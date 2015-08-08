package com.tz.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar sb_progress;

    private Button btn_play,btn_resume,btn_pause, btn_stop;

    private MusicService service;
    private MyServiceConnection connection;
    private Timer timer;

    private TimerTask task;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==332) {
                int progress = (int) msg.obj;
                sb_progress.setProgress(progress);
            }
        }
    };
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Toast.makeText(this, "create", Toast.LENGTH_LONG).show();
        sb_progress = (SeekBar) findViewById(R.id.sb_progress);
        sb_progress.setOnSeekBarChangeListener(this);

        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_resume = (Button) findViewById(R.id.btn_resume);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        Intent service = new Intent(this, MusicService.class);
        startService(service);
        connection = new MyServiceConnection();

        bindService(service, connection, Context.BIND_AUTO_CREATE);

    }

    private void startSeek() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        task=new TimerTask() {
            @Override
            public void run() {
                int current = service.getMusicCurrentPosition();
                Message message = mHandler.obtainMessage();
                message.what = 332;
                message.obj =  current ;
                mHandler.sendMessage(message);
            }
        };

        timer.schedule(task,0,1000);
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        if (timer != null) {
            timer.cancel();

        }
        super.onDestroy();
    }

    public void onClick(View view) {
        if (service==null)return;
        switch (view.getId()) {
            case R.id.btn_pause:
                service.pauseMusic();
                break;
            case R.id.btn_play:
                Toast toast = new Toast(this);
                toast.setText("play");
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
                try {
                    service.playMusic();
                    startSeek();
                    sb_progress.setMax(service.getMusicDuration());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_resume:
                service.continuMusic();
                break;
            case R.id.btn_stop:
                service.stopMusic();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            service.setProgress(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Toast.makeText(MyActivity.this, "connected!!!!!!!!!!!!!!!!!!!!!222", Toast.LENGTH_LONG).show();
            MusicService.MyBinder myBinder = (MusicService.MyBinder) iBinder;
            service = (MusicService) myBinder.getService();
            Log.d("connect", "success");
            if (service.isPlaying()) {
                startSeek();
                sb_progress.setMax(service.getMusicDuration());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }


}

