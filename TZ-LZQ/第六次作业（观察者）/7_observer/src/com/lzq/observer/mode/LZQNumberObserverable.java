package com.lzq.observer.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者接口
 * 
 * @author L_ZQ
 * 
 */
public abstract class LZQNumberObserverable {

	/**
	 * 观察者集合
	 */
	List<LZQNumberObserver> lists = new ArrayList<LZQNumberObserver>();

	/**
	 * 注册观察者
	 * 
	 * @param lzqNumberOberser
	 */
	public void registObserver(LZQNumberObserver lzqNumberOberser) {
		lists.add(lzqNumberOberser);
	}

	/**
	 * 取消注册观察者
	 * 
	 * @param lzqNumberOberser
	 */
	public void unregistObserver(LZQNumberObserver lzqNumberOberser) {
		if (lists.contains(lzqNumberOberser)) {
			lists.remove(lzqNumberOberser);
		}
	}

	/**
	 * 取消注册全部观察者
	 */
	public void unregistAll() {
		lists.clear();
	}
}
