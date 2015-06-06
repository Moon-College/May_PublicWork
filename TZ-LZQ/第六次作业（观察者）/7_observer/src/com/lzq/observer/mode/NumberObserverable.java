package com.lzq.observer.mode;
/**
 * 真实被观察者
 * @author L_ZQ
 *
 */
public class NumberObserverable extends LZQNumberObserverable {

	public void notifyObserver(LZQNumberObserver lzqNumberObserver) {
		lzqNumberObserver.onChange();
	}

	public void notifyAllObserver() {
		for (LZQNumberObserver ob : lists) {
			ob.onChange();
		}
	}
}
