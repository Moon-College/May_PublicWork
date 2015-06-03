package com.cn.test.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on2015-5-30 обнГ2:50:44 Observeable.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public abstract class Observeable {
	List<Observer> mObservers;

	public Observeable() {
		mObservers = new ArrayList<Observer>();
	}
	public void registeObserver(Observer observer){
		this.mObservers.add(observer);
	}
	public void unRegisteObserver(Observer observer){
		if (this.mObservers.contains(observer)) {
			this.mObservers.remove(observer);
		}
	}
	
	public void unRegisteAll(){
		mObservers.clear();
	}
}