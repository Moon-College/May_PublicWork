package com.tz.michael.activity.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的被观察者
 * @author szm
 *
 */
public abstract class AbstractObServerable {

	/**
	 * 存放观察者的一个集合
	 * An observer can be in the list at most
     * once and will never be null.
	 */
	final List<AbstractObServer> obServerSets=new ArrayList<AbstractObServer>();
	
	/**
	 * 这里用到的是同一个集合，因此需要考虑，同步问题
	 * Adds an observer to the list. The observer cannot be null and it must not already
     * be registered.
	 * @param obServer  the observer to register
	 * @throws IllegalArgumentException the observer is null
     * @throws IllegalStateException the observer is already registered
	 */
	public void registerObServer(AbstractObServer obServer){
		if(obServerSets==null){
			throw new IllegalArgumentException("obServer is null");
		}
		synchronized (obServerSets) {
			if(obServerSets.contains(obServer)){
				throw new IllegalStateException("ObServer"+obServer+"is already registered");
			}
			obServerSets.add(obServer);
		}
	}
	
	/**
	 * Removes a previously registered observer. The observer must not be null and it
     * must already have been registered.
     * @param observer the observer to unregister
     * @throws IllegalArgumentException the observer is null
     * @throws IllegalStateException the observer is not yet registered
	 */
	public void unRegisterObServer(AbstractObServer obServer){
		if(obServer==null){
			throw new IllegalArgumentException("obServer is null");
		}
		synchronized (obServerSets) {
			if(obServerSets.contains(obServer)){
				obServerSets.remove(obServer);
			}else{
				throw new IllegalStateException("Observer " + obServer + " was not registered.");
			}
		}
	}
	
	/**
	 * Remove all registered observer
	 */
	public void unRegisterAll(){
		synchronized (obServerSets) {
			obServerSets.clear();
		}
	}
	
}
