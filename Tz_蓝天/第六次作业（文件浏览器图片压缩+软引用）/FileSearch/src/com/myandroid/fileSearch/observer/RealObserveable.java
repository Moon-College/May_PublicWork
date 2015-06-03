package com.myandroid.fileSearch.observer;

public class RealObserveable extends Observeable {
	
	/**
	 * 通知观察者，内容发生了改变
	 * @param observer
	 */
	public void notifyObserver(Observer observer){
		observer.Changed();
	}
	
	/**
	 * 通知所有观察者，内容发生了改变
	 */
	public void notifyAllObserver()
	{
		for (Observer observer : mobserver) {
			observer.Changed();
		}
	}
}
