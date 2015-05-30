package com.limz.observerdemo.model;

import android.content.Context;

public class RealObservable extends AbstractObservable {

	public void notifyOneObserver(AbstractObserver observer, Context context) {
		observer.onChange(context);
	}
	
	public void notifyAllObserver(Context context) {
		for(AbstractObserver observer : observerList) {
			observer.onChange(context);
		}
	}
}