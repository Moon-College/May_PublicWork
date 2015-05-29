package com.observer;

public class RealObserverable extends Observerable{
	public void notifyObserver(Observer mObserver){
		mObserver.onChanged();
	}

	public void notifyAllObserver(){
		for(Observer mObserver:mObserverData){
			mObserver.onChanged();
		}
	}
}
