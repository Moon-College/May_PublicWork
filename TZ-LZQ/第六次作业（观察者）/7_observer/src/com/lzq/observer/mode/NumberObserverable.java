package com.lzq.observer.mode;
/**
 * ��ʵ���۲���
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
