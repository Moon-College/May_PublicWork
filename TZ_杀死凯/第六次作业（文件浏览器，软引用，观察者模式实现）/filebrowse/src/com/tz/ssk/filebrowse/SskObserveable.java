package com.tz.ssk.filebrowse;

import java.util.ArrayList;
import java.util.List;

public abstract class SskObserveable {
	List<SskObserver> mSskObserver;
	public SskObserveable()
	{
		mSskObserver=new ArrayList<SskObserver>();
	}
	//注册观察者
	public void registeObserver(SskObserver ssk)
	{
		mSskObserver.add(ssk);
	}
	//销毁观察者
	public void unregisteObserver(SskObserver ssk)
	{
		if(mSskObserver.contains(ssk))
		{
			//如果存在，才销毁
			mSskObserver.remove(ssk);
		}
	}
	//销毁所有观察者
	public void unregisteAll()
	{
		mSskObserver.clear();
	}
	
}
