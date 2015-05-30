package com.limz.observerdemo.model;

import java.util.ArrayList;

public abstract class AbstractObservable {
	public ArrayList<AbstractObserver> observerList;
	
	AbstractObservable() {
		observerList = new ArrayList<AbstractObserver>();
	}
	
	public void registeredObserver(AbstractObserver observer) {
		observerList.add(observer);
	}
	
	public void unRegisteredObserver(AbstractObserver observer) {
		if(observerList.contains(observer)) {
			observerList.remove(observer);
		}
	}
}
