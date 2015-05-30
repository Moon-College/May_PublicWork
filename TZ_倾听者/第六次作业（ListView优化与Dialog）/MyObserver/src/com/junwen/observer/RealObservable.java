package com.junwen.observer;

public class RealObservable extends MyServable {

	/**
	 * 通知观察者
	 */
	public void nofityDataSetObserver(MyObserver myObserver,int num)
	{
		myObserver.onUpdate(num);
	}
	/**
	 * 更新所有观察者
	 * @param num
	 */
	public void nofityAllDataSetObserver(int num)
	{
		for (MyObserver server : mObserver) {
			server.onUpdate(num);
		}
	}
}
