package com.tz.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Administrator on 2015/7/23 0023.
 */
public class MusicService extends Service {
    private MyBinder mMyBinder;
    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        mMyBinder = new MyBinder();

        return mMyBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

    }

    //���ֲ���
    public void playMusic() throws Exception, IllegalStateException, IOException {
        Log.i("INFO", "play music");
        mediaPlayer.reset();//���þ����ò��������ڿ���״̬
        mediaPlayer.setDataSource(this, Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Lights.mp3"));
        mediaPlayer.prepare();//����
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    public int getMusicDuration() {
        return mediaPlayer.getDuration();
    }

    public int getMusicCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void setProgress(int progress) {
        int duration = getMusicDuration();
        mediaPlayer.seekTo(progress);
    }

    public void pauseMusic(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }


    public void continuMusic(){
        mediaPlayer.start();
    }

    public void stopMusic(){
        mediaPlayer.stop();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public Service getService() {

            return MusicService.this;
        }
    }
}
