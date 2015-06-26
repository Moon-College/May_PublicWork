package com.ccgao.observer.designpattern;

import java.util.ArrayList;
import java.util.List;

public abstract class NumObserverable {
	protected List<NumObserver> numObserverList;

	public NumObserverable() {
		numObserverList = new ArrayList<NumObserver>();
	}

	public void registeObserver(NumObserver numObserver) {
		numObserverList.add(numObserver);
	}

	public void unregisterObserver(NumObserver numObserver) {
		if (numObserverList.contains(numObserver)) {
			numObserverList.remove(numObserver);
		}
	}

	public void unAllRegister(NumObserver numObserver) {
		numObserverList.clear();
	}
}
