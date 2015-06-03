package com.myandroid.fileSearch.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observeable {
	List<Observer> mobserver =new ArrayList<Observer>();
	
	/**
	 * 注册观察者
	 * @param observer 观察者
	 */
	public void registerObserver(Observer observer)
	{
		this.mobserver.add(observer);
	}
	
	/**
	 * 注销观察者
	 * @param observer 观察者
	 */
	public void unregisterObserver(Observer observer)
	{
		this.mobserver.remove(observer);
	}
	
	/**
	 * 注销所有观察者
	 * @param observer 观察者
	 */
	public void unregisterAllObserver(Observer observer)
	{
		this.mobserver.clear();
	}
}
