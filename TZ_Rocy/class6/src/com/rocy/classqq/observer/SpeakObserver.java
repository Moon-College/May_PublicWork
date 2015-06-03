package com.rocy.classqq.observer;

import android.content.Context;
import android.widget.Toast;

/**
 * 实际观察者
 * @author Administrator
 *
 */
public class SpeakObserver extends ASpeakObserver {

	public SpeakObserver(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 没图片
	 */
	@Override
	public void onSpeak2() {
		// TODO Auto-generated method stub
		Toast.makeText(context, "我很丑没有图片", 1000).show();
	}

	/**
	 * 有图片
	 */
	@Override
	public void onSpeak1() {
		Toast.makeText(context, "想看就看吧，让你看个够", 1000).show();
		
	}
	
	

}
