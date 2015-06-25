package com.caist.desinger.observerds;

import java.util.ArrayList;
import java.util.List;

public class AbstractSubject {

	public List<AbstractObserver> observers;
	public AbstractSubject(){
		observers = new ArrayList<AbstractObserver>();
	}
	public void AddObserver(AbstractObserver abstractObserver){
		observers.add(abstractObserver);
	}
	public void removeObserver(AbstractObserver abstractObserver){
	    if(observers.contains(abstractObserver))
		  observers.remove(abstractObserver);
	}
   public void removeAll(){
	   observers.clear();
   }
}
