package com.junwen.observer;

public class RealObservable extends MyServable {

	/**
	 * ֪ͨ�۲���
	 */
	public void nofityDataSetObserver(MyObserver myObserver,int num)
	{
		myObserver.onUpdate(num);
	}
	/**
	 * �������й۲���
	 * @param num
	 */
	public void nofityAllDataSetObserver(int num)
	{
		for (MyObserver server : mObserver) {
			server.onUpdate(num);
		}
	}
}
