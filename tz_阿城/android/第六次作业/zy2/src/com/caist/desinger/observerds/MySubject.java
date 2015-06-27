package com.caist.desinger.observerds;

public class MySubject extends AbstractSubject {

	public void NotifyObserver(AbstractObserver abstractObserver){
		abstractObserver.onChange();
	}
	public void NitifyAllObject(){
		for(AbstractObserver observer:observers){
			observer.onChange();
		}
	}
}
