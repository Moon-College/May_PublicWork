package com.junwen.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class MyServable {
	//�۲��߼���
	List<MyObserver> mObserver;
	public MyServable() {
		mObserver = new ArrayList<MyObserver>();
	}
	/**
	 * ע��۲���
	 * @param observer
	 */
	public void registerObserver(MyObserver observer)
	{
		mObserver.add(observer);
	}
	/**
	 * ����۲���
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
	 * ������еĹ۲���
	 */
	public void unRegisterAllObserver()
	{
		mObserver.clear();
	}
}
