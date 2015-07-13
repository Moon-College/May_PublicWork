package com.keven.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Observerable {
   List<Observer> observers;
   public Observerable() {
	// TODO Auto-generated constructor stub
	   observers=new ArrayList<Observer>();
}
   public void registerObserver(Observer observer){
	   observers.add(observer);
   }
   public void unRegisterObserver(Observer observer){
	   if(observers.contains(observer)){
		   observers.remove(observer);
	   }
	   
   }
   public void notifyObserverChange(Observer observer){
	   observer.onChange();
   }
}
