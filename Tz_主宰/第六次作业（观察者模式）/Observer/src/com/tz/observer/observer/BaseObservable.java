package com.tz.observer.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseObservable {
	private List<BaseObserver> observers;

	public BaseObservable() {
		observers = new ArrayList<BaseObserver>();
	}

	/**
	 * �󶨹۲���
	 * @param observer
	 */
	public void registeObserver(BaseObserver observer) {
		observers.add(observer);
	}

	/**
	 * ���۲���
	 * @param observer
	 */
	public void unregisteObserver(BaseObserver observer) {
		if (observers.contains(observer)) {
			observers.remove(observer);
		}
	}

	/**
	 * �������
	 */
	public void unregisteAll() {
		observers.clear();
	}

	/**
	 * ֪ͨ���й۲���
	 */
	public void notifyDataChange() {
		for (BaseObserver observer : observers) {
			observer.onChange();
		}
	}

	/**
	 * ָ֪ͨ���۲���
	 * @param observer
	 */
	public abstract void notifyObserver(BaseObserver observer);
}
