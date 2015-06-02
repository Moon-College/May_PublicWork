package com.tz.observer.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseObservable {
	private List<BaseObserver> observers;

	public BaseObservable() {
		observers = new ArrayList<BaseObserver>();
	}

	/**
	 * 绑定观察者
	 * @param observer
	 */
	public void registeObserver(BaseObserver observer) {
		observers.add(observer);
	}

	/**
	 * 解绑观察者
	 * @param observer
	 */
	public void unregisteObserver(BaseObserver observer) {
		if (observers.contains(observer)) {
			observers.remove(observer);
		}
	}

	/**
	 * 解绑所有
	 */
	public void unregisteAll() {
		observers.clear();
	}

	/**
	 * 通知所有观察者
	 */
	public void notifyDataChange() {
		for (BaseObserver observer : observers) {
			observer.onChange();
		}
	}

	/**
	 * 通知指定观察者
	 * @param observer
	 */
	public abstract void notifyObserver(BaseObserver observer);
}
