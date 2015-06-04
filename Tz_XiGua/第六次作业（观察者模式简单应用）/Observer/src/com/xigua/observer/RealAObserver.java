package com.xigua.observer;

public class RealAObserver extends AObservered{
	/**
	 * 通知观察者数据发生变化
	 * @param dannyNumberObserver
	 */
	public void notifyObserver(Observer dannyNumberObserver){
		dannyNumberObserver.onChanged();
	}


	/**
	 * 通过所有观察者数据发生变化
	 */
	public void notifyAllObserver(){
		for(Observer dannyNumberObserver:mDannyNumberObservers){
			dannyNumberObserver.onChanged();
		}
	}
	
}
