package com.tz.observer.observer;

public class ViewObservable extends BaseObservable {

	@Override
	public void notifyObserver(BaseObserver observer) {
		observer.onChange();
	}

}
