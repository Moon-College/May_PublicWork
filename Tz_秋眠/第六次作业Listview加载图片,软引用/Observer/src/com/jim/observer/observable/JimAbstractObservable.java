package com.jim.observer.observable;

import java.util.ArrayList;
import java.util.List;

import com.jim.observer.decentobserver.JimDecentAbstractObserver;

/**
 * 抽象被观察者
 * 
 * @author Administrator
 * 
 */
public class JimAbstractObservable {
	/**
	 * 观察者集合
	 */
	private List<JimDecentAbstractObserver> mList = new ArrayList<JimDecentAbstractObserver>();

	/**
	 * 添加观察者
	 * 
	 * @param DAO
	 */
	public void addDecentObserver(JimDecentAbstractObserver DAO) {

	}

	/**
	 * 删除观察者
	 * 
	 * @param DAO
	 */
	public void deleteDecentObserver(JimDecentAbstractObserver DAO) {

	}

	/**
	 * 通知所有观察者
	 * 
	 * @param DAO
	 */
	public void notifyAllDecentObserver(String... strings) {

	}
}
