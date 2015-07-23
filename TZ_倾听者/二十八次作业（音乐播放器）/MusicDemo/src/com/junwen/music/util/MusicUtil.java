package com.junwen.music.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.junwen.music.model.Music;

public class MusicUtil {
	
	/**
	 * 获取所有音乐
	 */
	public static List<Music> getAllMusic(Context context) {
		List<Music> list = new ArrayList<Music>();
		Music music  = null;
		ContentResolver contentResolver = context.getContentResolver();
		Cursor cursor = context.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				MediaStore.Audio.Media .DEFAULT_SORT_ORDER);
		if( cursor != null){
			while(cursor.moveToNext()){
				//ID
				long id = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Audio.Media._ID)); 
				//文件名
				String fileName = cursor.getString((cursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE))); 
				//文件路径
				String path = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.DATA)); 
				//音乐时长
				long duration = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Audio.Media.DURATION));
				music = new Music(id, fileName, path, duration);
				list.add(music);
			}
			cursor.close();
		}
		cursor.close();
		return list;
	}
	/**
	 * 格式化时间，将毫秒转换为分:秒格式
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime(long time) {
		String min = time / (1000 * 60) + "";
		String sec = time % (1000 * 60) + "";
		if (min.length() < 2) {
			min = "0" + time / (1000 * 60) + "";
		} else {
			min = time / (1000 * 60) + "";
		}
		if (sec.length() == 4) {
			sec = "0" + (time % (1000 * 60)) + "";
		} else if (sec.length() == 3) {
			sec = "00" + (time % (1000 * 60)) + "";
		} else if (sec.length() == 2) {
			sec = "000" + (time % (1000 * 60)) + "";
		} else if (sec.length() == 1) {
			sec = "0000" + (time % (1000 * 60)) + "";
		}
		return min + ":" + sec.trim().substring(0, 2);
	}
}
