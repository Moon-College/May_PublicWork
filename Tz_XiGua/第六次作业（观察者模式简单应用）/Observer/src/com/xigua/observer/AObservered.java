package com.xigua.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class AObservered {
	
	List<Observer> mDannyNumberObservers;
	
	public AObservered(){
		mDannyNumberObservers = new ArrayList<Observer>();
	}
	
	public void registeObserver(Observer dannyNumberObserver){
		//注册观察者
		this.mDannyNumberObservers.add(dannyNumberObserver);
	}
	
	
	
	public void unregisteObserver(Observer dannyNumberObserver){
		if(this.mDannyNumberObservers.contains(dannyNumberObserver)){
			//取消注册
			this.mDannyNumberObservers.remove(dannyNumberObserver);
		}
	}
	
	
	/**
	 * 取消所有观察者
	 */
	public void unregisteAll(){
		mDannyNumberObservers.clear();
	}
}
