package com.zjm.observer.custom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Json 被观察者
 * */
public abstract class BaseObservable {

	List<BaseObserver> mObservers = new ArrayList<BaseObserver>();

	boolean changed = false;

	/**
	 * 注册观察者
	 * 
	 * @author Json
	 * */
	public void registerObserver(BaseObserver observer) {
		if (observer == null) {
			throw new IllegalArgumentException("The observer is null.");
		}
		synchronized (mObservers) {
			if (mObservers.contains(observer)) {
				throw new IllegalStateException("Observer " + observer
						+ " is already registered.");
			}
			mObservers.add(observer);
		}
	}

	/**
	 * 取消注册观察者
	 * 
	 * @author Json
	 * */
	public void unregisterObserver(BaseObserver observer) {
		if (observer == null) {
			throw new IllegalArgumentException("The observer is null.");
		}
		synchronized (mObservers) {
			int index = mObservers.indexOf(observer);
			if (index == -1) {
				throw new IllegalStateException("Observer " + observer
						+ " was not registered.");
			}
			mObservers.remove(observer);
		}
	}

	/**
	 * 设置改值表示数据发生变化
	 * 
	 * @author Json
	 * */
	protected void setChanged() {
		changed = true;
	}

	protected void clearChanged() {
		changed = false;
	}

	/**
	 * 通知观察者
	 * 
	 * @author Json
	 * */
	public void notifyObservers() {
		notifyObserver(null);
	}
	

	/**
	 * 通知所有观察者 并发送数据
	 * 
	 * @author Json
	 * */
	public void notifyObserver(Object data) {
		int size = 0;
		BaseObserver[] arrays = null;
		synchronized (this) {
			clearChanged();
			size = mObservers.size();
			arrays = new BaseObserver[size];
			mObservers.toArray(arrays);
		}
		if (arrays != null) {
			for (BaseObserver baseObserver : arrays) {
				baseObserver.update(this, data);
			}
		}
	}

	public void unregisterAll() {
		synchronized (mObservers) {
			mObservers.clear();
		}
	}

}
