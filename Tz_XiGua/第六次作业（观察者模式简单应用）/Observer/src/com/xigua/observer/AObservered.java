package com.xigua.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class AObservered {
	
	List<Observer> mDannyNumberObservers;
	
	public AObservered(){
		mDannyNumberObservers = new ArrayList<Observer>();
	}
	
	public void registeObserver(Observer dannyNumberObserver){
		//ע��۲���
		this.mDannyNumberObservers.add(dannyNumberObserver);
	}
	
	
	
	public void unregisteObserver(Observer dannyNumberObserver){
		if(this.mDannyNumberObservers.contains(dannyNumberObserver)){
			//ȡ��ע��
			this.mDannyNumberObservers.remove(dannyNumberObserver);
		}
	}
	
	
	/**
	 * ȡ�����й۲���
	 */
	public void unregisteAll(){
		mDannyNumberObservers.clear();
	}
}
