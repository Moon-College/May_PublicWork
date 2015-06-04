package com.tz.observer;

public class RealNumberObserveable extends DannyNumberObserveable{
	
	/**
	 * 通知观察者，数据发生变化
	 * @param dannyNumberObserver
	 */
	public void notifyObserver(DannyNumberObserver dannyNumberObserver) {
		dannyNumberObserver.onChanged();
	}
	
	/**
	 * 通知所有观察者，数据发生了变化
	 */
	public void notifyAllObserver() {
		for(DannyNumberObserver dannyNumberObserver :mAllObservers) {
			dannyNumberObserver.onChanged();
		}
	}
}
