package com.junwen.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class MyServable {
	//观察者集合
	List<MyObserver> mObserver;
	public MyServable() {
		mObserver = new ArrayList<MyObserver>();
	}
	/**
	 * 注册观察者
	 * @param observer
	 */
	public void registerObserver(MyObserver observer)
	{
		mObserver.add(observer);
	}
	/**
	 * 解除观察者
	 * @param observer
	 */
	public void unregisterObserver(MyObserver observer)
	{
		if(mObserver.contains(observer))
		{
			mObserver.remove(observer);
		}
	}
	/**
	 * 清除所有的观察者
	 */
	public void unRegisterAllObserver()
	{
		mObserver.clear();
	}
}
