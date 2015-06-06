package com.dd.observer;

public class RealButtonObserveable extends DDButtonObserveable{
	public void notifyObserver(DDButtonObserver ddButtonObserver){
		ddButtonObserver.onChanged();
	}
	public void notifyAllObserver(){
		for (DDButtonObserver iterable_element : mDDButtonObservers) {
			iterable_element.onChanged();
		}
	}
}
