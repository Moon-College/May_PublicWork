package com.jim.observer.observable;

import java.util.ArrayList;
import java.util.List;

import com.jim.observer.decentobserver.JimDecentAbstractObserver;

/**
 * ���󱻹۲���
 * 
 * @author Administrator
 * 
 */
public class JimAbstractObservable {
	/**
	 * �۲��߼���
	 */
	private List<JimDecentAbstractObserver> mList = new ArrayList<JimDecentAbstractObserver>();

	/**
	 * ��ӹ۲���
	 * 
	 * @param DAO
	 */
	public void addDecentObserver(JimDecentAbstractObserver DAO) {

	}

	/**
	 * ɾ���۲���
	 * 
	 * @param DAO
	 */
	public void deleteDecentObserver(JimDecentAbstractObserver DAO) {

	}

	/**
	 * ֪ͨ���й۲���
	 * 
	 * @param DAO
	 */
	public void notifyAllDecentObserver(String... strings) {

	}
}
