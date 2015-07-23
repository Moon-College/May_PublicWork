package com.dd.dd_service.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

/**
 * 1) �����������ʽ  ����������ʽ��ͬӵ�в�ͬ����������

-> startService()����   oncreate  onstart   ->ondestroy
 
-> bindService()����    oncreate  onbind   ->onunbind  ondestroy


1*�������һ���������Ͳ�������oncreate

2*����ֻ�������еİ󶨶�����˻������������ķ���ֹͣ�˲Ż�����


**��α��ַ����ڴ��ڣ�һ��ͨ��ͬʱstartservice  bindservice  �˳�����ʱunbindservice


**��α��ַ���������
**�㲥������---->����ϵͳ�������ֵĹ㲥�����������Ժ�������
 * @author dd
 *
 */
public class MyService extends Service {
	private MyBinder binder;
	private MediaPlayer mediaPlayer;
	@Override
	public IBinder onBind(Intent intent) {
		binder = new MyBinder();
		return binder;
	}
	
	@Override
	public void onCreate() {
		mediaPlayer = new MediaPlayer();
		super.onCreate();
	}

	public class MyBinder extends Binder{
		public MyService getMyService(){
			return MyService.this;
		}
	}

	public void playMusic() throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
		mediaPlayer.reset();//���þ����ò��������ڿ���״̬
		mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/������.mp3");
		mediaPlayer.prepare();//����
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mp.start();
			}
		});
	}

	public void pauseMusic() {
		if(mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
	}

	public void continuMusic() {
		mediaPlayer.start();
		
	}

	public void stopMusic() {
		mediaPlayer.stop();
	}
	public void check(){
		Log.i("INFO", "��鴴�����");
	}
}
