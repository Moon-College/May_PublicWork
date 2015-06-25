package com.dd.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class DDButtonObserveable {
	List<DDButtonObserver> mDDButtonObservers;

	public DDButtonObserveable() {
		mDDButtonObservers = new ArrayList<DDButtonObserver>();
	}
	public void registeObserver(DDButtonObserver ddButtonObserver){
		this.mDDButtonObservers.add(ddButtonObserver);
	}
	public void unRegisteObserver(DDButtonObserver ddButtonObserver){
		if (this.mDDButtonObservers.contains(ddButtonObserver)) {
			this.mDDButtonObservers.remove(ddButtonObserver);
		}
	}
	
	public void unRegisteAll(){
		mDDButtonObservers.clear();
	}
}
