package com.jim.observer.observable;

import java.util.ArrayList;
import java.util.List;

import com.jim.observer.decentobserver.JimDecentAbstractObserver;

/**
 * 被观察者
 * 
 * @author Jim
 * 
 */
public class JimNewsObservable extends JimAbstractObservable {
	// 观察者集合
	private List<JimDecentAbstractObserver> mDAOList = new ArrayList<JimDecentAbstractObserver>();

	/**
	 * 实现抽象被观察者中的添加观察者的方法
	 */
	@Override
	public void addDecentObserver(JimDecentAbstractObserver DAO) {
		// TODO Auto-generated method stub
		mDAOList.add(DAO);
		super.addDecentObserver(DAO);
	}

	/**
	 * 实现抽象被观察者中的删除观察者的方法
	 */
	@Override
	public void deleteDecentObserver(JimDecentAbstractObserver DAO) {
		// TODO Auto-generated method stub
		mDAOList.remove(DAO);
		super.deleteDecentObserver(DAO);
	}

	/**
	 * 实现抽象被观察者中的通知方法
	 */
	@Override
	public void notifyAllDecentObserver(String... strings) {
		// TODO Auto-generated method stub
		for (JimDecentAbstractObserver d:mDAOList){
			
		}
			super.notifyAllDecentObserver(strings);
	}
}
