package com.lzq.observer.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * ���۲��߽ӿ�
 * 
 * @author L_ZQ
 * 
 */
public abstract class LZQNumberObserverable {

	/**
	 * �۲��߼���
	 */
	List<LZQNumberObserver> lists = new ArrayList<LZQNumberObserver>();

	/**
	 * ע��۲���
	 * 
	 * @param lzqNumberOberser
	 */
	public void registObserver(LZQNumberObserver lzqNumberOberser) {
		lists.add(lzqNumberOberser);
	}

	/**
	 * ȡ��ע��۲���
	 * 
	 * @param lzqNumberOberser
	 */
	public void unregistObserver(LZQNumberObserver lzqNumberOberser) {
		if (lists.contains(lzqNumberOberser)) {
			lists.remove(lzqNumberOberser);
		}
	}

	/**
	 * ȡ��ע��ȫ���۲���
	 */
	public void unregistAll() {
		lists.clear();
	}
}
