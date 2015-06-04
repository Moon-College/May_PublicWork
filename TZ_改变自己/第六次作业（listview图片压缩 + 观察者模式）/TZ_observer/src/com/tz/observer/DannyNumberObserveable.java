package com.tz.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class DannyNumberObserveable {
	List<DannyNumberObserver> mAllObservers; //���еĹ۲���
	
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
	 * ȡ�����еĹ۲���
	 */
	public void unregisterAllObserver() {
		this.mAllObservers.clear();
	}
	
	
}
