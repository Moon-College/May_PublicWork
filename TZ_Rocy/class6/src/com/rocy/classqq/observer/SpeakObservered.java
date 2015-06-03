package com.rocy.classqq.observer;

import android.content.Context;
import android.widget.Toast;

/**
 * 实际被观察者
 * @author Administrator
 *
 */
public class SpeakObservered  extends ASpeakObservered{
	private boolean isMap;//是不是图片
	
	public SpeakObservered(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	 public void notifySetChange(){
		for (ASpeakObserver speakObserver : list) {
			if(isMap){
				speakObserver.onSpeak1();
			}else{
				speakObserver.onSpeak2();
			}
		}
	}

	/**
	 * @return the isMap
	 */
	public boolean isMap() {
		return isMap;
	}

	/**
	 * @param isMap the isMap to set
	 */
	public void setMap(boolean isMap) {
		this.isMap = isMap;
	}

	 

}
