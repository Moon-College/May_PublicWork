package com.tz.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class DannyNumberObserveable {
	List<DannyNumberObserver> mAllObservers; //所有的观察者
	
	public DannyNumberObserveable() {
		mAllObservers = new ArrayList<DannyNumberObserver>();
	}
	public void registerObserver(DannyNumberObserver dannyNumberObserver) {
		this.mAllObservers.add(dannyNumberObserver);
	}
	
	public void unregisterObserver(DannyNumberObserver dannyNumberObserver) {
		if(this.mAllObservers.contains(dannyNumberObserver)) {
			this.mAllObservers.remove(dannyNumberObserver);
		}
	}
	
	/**
	 * 取消所有的观察者
	 */
	public void unregisterAllObserver() {
		this.mAllObservers.clear();
	}
	
	
}
