package com.zjm.observer.custom;

/**
 * @author Json
 * 观察者
 * */
public interface BaseObserver {

	void update(BaseObservable baseObservable, Object data);

}
