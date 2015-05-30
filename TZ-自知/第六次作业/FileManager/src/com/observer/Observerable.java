package com.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observerable {
	public List<Observer> mObserverData=new ArrayList<Observer>();
	
	public void registObserver(Observer mObserver)
	{
		mObserverData.add(mObserver);
	}
	
	public void unrigistObserver(Observer mObserver)
	{
		if(mObserverData.contains(mObserver))
		{
			mObserverData.remove(mObserver);
		}
	}
	
	public void unrighstAll()
	{
		mObserverData.clear();
	}
}
