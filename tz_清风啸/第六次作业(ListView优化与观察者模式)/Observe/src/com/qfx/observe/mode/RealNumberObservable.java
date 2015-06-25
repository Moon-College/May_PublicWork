package com.qfx.observe.mode;

/**
 * 真实的被观察者
 * @author Administrator
 *
 */
public class RealNumberObservable extends NumberObservable {
	
	/**
	 * 通知观察者数据发生变化
	 * @param numberObserver
	 */
	public void notifyObserver(NumberObserver numberObserver) {
		numberObserver.onChanged();
	}
	
	/**
	 * 通知所有的观察着数据发生变化
	 */
	public void notifyAllObserver() {
		for (NumberObserver observer : numberObservers) {
			observer.onChanged();
		}
	}
}
