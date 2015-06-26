package com.p2y9y.observermodel.model;

public class ActualObserverable extends BaseObserverable {
	
	public void notifyDataSetChange(BaseObserver observer){
		observer.dataChange();
	}
}
