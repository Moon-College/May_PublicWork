package com.junwen.music.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.musicdemo.R;
import com.junwen.music.adapter.MusicAdapter;
import com.junwen.music.model.Music;
import com.junwen.music.service.MusicService;
import com.junwen.music.service.MusicService.MyBinder;
import com.junwen.music.util.MusicUtil;
import com.junwen.util.June;
import com.junwen.util.SPUtils;

public class MainActivity extends Activity implements OnItemClickListener,
		OnClickListener {

	private ListView lv; // ListView
	private MusicAdapter adapter; // 音乐适配器
	private List<Music> data; // 音乐集合
	private TextView song_name; // 音乐名字
	private ImageView start_over; // 播放或者停止
	private MusicService service; // 绑定的服务
	private boolean isBound; // 是否已经绑定了
	private ImageView next_song; //下一首
	private boolean isPlaying; //是否正在播放
	private int currend_song; //当前播放的索引
	private boolean default_state =true; //默认状态,播放第一首音乐
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		lv.setAdapter(adapter);
		startLongMenu();
	}
	/**
	 * 开启常驻任务栏
	 */
	private void startLongMenu() {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_menu);
		builder.setContent(remoteViews);
		builder.setOngoing(true);
		builder.setSmallIcon(R.drawable.ic_launcher);
		NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(0, builder.build());
		//对音乐的控制不会，还需要再学习下
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		data = MusicUtil.getAllMusic(this);
		adapter = new MusicAdapter(data, this);
		Intent intent = new Intent(this, MusicService.class);
		// Start SerVice
		this.startService(intent);
		//Bind Service
		this.bindService(intent, connection, BIND_AUTO_CREATE);
	}
	/**
	 * 初始化
	 */
	private void initView() {
		lv = (ListView) findViewById(R.id.listview);
		song_name = (TextView) findViewById(R.id.song_name);
		start_over = (ImageView) findViewById(R.id.start_over);
		next_song = (ImageView) findViewById(R.id.next_song);
		data = new ArrayList<Music>();
		lv.setOnItemClickListener(this);
		start_over.setOnClickListener(this);
		next_song.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// 开启播放
		startMusicService(position);
	}
	/**
	 * 根据索引来播放音乐
	 * @param position
	 */
	private void startMusicService(int position) {
		Music music = data.get(position);
		String fileName = music.getFileName();
		String path = music.getPath();
		song_name.setText(fileName);
		start_over.setImageResource(R.drawable.stop_music);
		currend_song = position;
		try {
			service.playMusic(path);
			isPlaying = true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 绑定服务状态
	 */
	public ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("onServiceDisconnected");
		}
		@Override
		public void onServiceConnected(ComponentName name, IBinder ibinder) {
			MusicService.MyBinder binder = (MyBinder) ibinder;
			service = binder.getService();
			isBound = true;
		}
	};
	
	/**
	 * 解除绑定
	 */
	protected void onDestroy() {
		super.onDestroy();
		if (isBound) {
			this.unbindService(connection);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_over:
			//开启播放或者暂停
			playMusic();
			break;
		case R.id.next_song:
			//下一首
			nextMusic();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 下一首音乐
	 */
	private void nextMusic() {
		currend_song++;
		currend_song = currend_song % data.size();
		startMusicService(currend_song);
	}
	
	/**
	 * 播放音乐或者暂停音乐
	 */
	private void playMusic() {
		if(isPlaying){
			//播放状态按的
			start_over.setImageResource(R.drawable.start_music);
			service.pauseMusic();
			isPlaying = false;
		}
		//如果一开始按开始，就让他默认播放第一首音乐
		else if(default_state){
			if(data.size()>0){
				startMusicService(0);
				default_state = false;
			}
		}
		else{
			//暂停状态按的
			start_over.setImageResource(R.drawable.stop_music);
			service.continuMusic();
			isPlaying = true;
		}
	};
}
