package com.p2y9y.observermodel.model;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseObserverable {

	List<BaseObserver> observerList;
	
	/**
	 * Add a observer to the list, the observer can not be null & it must not already;
	 * @param mBaseObserver the observer to register
	 * @throws IllegalStateException the observer is null
	 * @throws IllegalStateException the observer is already registered
	 */
	public void registerObserver(BaseObserver mBaseObserver){
		if (observerList == null) {
			observerList = new ArrayList<BaseObserver>();
		}
		if(mBaseObserver == null){
			throw new IllegalStateException("the observer is null");
		}
		synchronized (mBaseObserver) {
			if(observerList.contains(mBaseObserver)){
				throw new IllegalStateException("the observer "+ mBaseObserver + "is already registered");
			}
			observerList.add(mBaseObserver);	
		}
	}
	
	/**
	 * Remove a previously registered observer. The observer can not be null and it must already 
	 * hava been registered 
	 * @param mBaseObserver the observer to unregister
	 * @throws IllegalStateException never have observer be registered
	 * @throws IllegalStateException the observer is null
	 * @throws IllegalStateException the observer never been registered
	 */
	public void unregisterObserver(BaseObserver mBaseObserver){
		if(observerList == null){
			throw new IllegalStateException("never have observer been registered");
		}
		if(mBaseObserver == null){
			throw new IllegalStateException("the observer is null");
		}
		synchronized (mBaseObserver) {
			if(!observerList.contains(mBaseObserver)){
				throw new IllegalStateException("this observer "+ mBaseObserver +"never been registered");
			}
			observerList.remove(mBaseObserver);	
		}
	}
	
	/**
	 * Remove all observer
	 */
	public void unregisterAll(){
		if (observerList != null) {
			synchronized (observerList) {
				observerList.clear();
			}	
		}
	}
}
