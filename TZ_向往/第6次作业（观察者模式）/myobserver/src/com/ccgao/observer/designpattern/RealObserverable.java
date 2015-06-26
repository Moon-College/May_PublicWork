package com.ccgao.observer.designpattern;

public class RealObserverable extends NumObserverable {
	public void notifyObersers(NumObserver numObserver){
		numObserver.onchange();
	}
	
	public void notifyAllObservers(){
		for (NumObserver numObserver : numObserverList) {
			numObserver.onchange();
		}
	}

}
