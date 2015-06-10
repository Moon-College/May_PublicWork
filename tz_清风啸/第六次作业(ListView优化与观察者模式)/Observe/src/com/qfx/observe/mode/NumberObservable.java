package com.qfx.observe.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * 被抽象的观察者
 * @author Administrator
 *
 */
public abstract class NumberObservable {

	List<NumberObserver> numberObservers = new ArrayList<NumberObserver>();
	
	public NumberObservable() {
		
	}
	
	/**
	 * 注册观察者
	 * @param numberObserver
	 */
	public void registerObserver(NumberObserver numberObserver) {
		if (numberObserver == null) {
			throw new NullPointerException("observer == null");
		}
		synchronized (this) {
			if (!numberObservers.contains(numberObserver)) {
				numberObservers.add(numberObserver);
			}
		}
	}
	
	/**
	 * 取消注册
	 * @param numberObserver
	 */
	public synchronized void unregisterObserver(NumberObserver numberObserver) {
		numberObservers.remove(numberObserver);
	}
	
	/**
	 * 移除所有观察者
	 */
	public void unregisterAll() {
		numberObservers.clear();
	}
}
