package com.zjm.observer.custom;

import android.content.Context;

public class MyObservable extends BaseObservable {

	/**
	 * 通知指定观察者数据发送改变
	 * */
	public void notifyObserver(Context context,MyObserver myObserver,Object data){
		setChanged();
		myObserver.update(this, data);
		myObserver.insert(context,data);
	}
	
	/**
	 * 通知所有观察者数据发送改变
	 * */
	public void notifyAllObserver(Object data){
		setChanged();
		notifyObserver(data);
	}
	
}
