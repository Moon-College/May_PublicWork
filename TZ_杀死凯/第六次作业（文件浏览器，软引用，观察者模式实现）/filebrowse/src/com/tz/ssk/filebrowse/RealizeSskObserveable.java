package com.tz.ssk.filebrowse;

public class RealizeSskObserveable extends SskObserveable{

	//通知一个观察者
	public void notifyObserver(SskObserver ssk)
	{
		ssk.OnChanged();
	}
	//通知所有观察者
	public void notifyObserverAll()
	{
		for(SskObserver ssk:mSskObserver)
		{
			ssk.OnChanged();
		}
	}
}
