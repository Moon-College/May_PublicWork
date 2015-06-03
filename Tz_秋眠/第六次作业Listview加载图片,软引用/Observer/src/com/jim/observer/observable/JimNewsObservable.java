package com.jim.observer.observable;

import java.util.ArrayList;
import java.util.List;

import com.jim.observer.decentobserver.JimDecentAbstractObserver;

/**
 * ���۲���
 * 
 * @author Jim
 * 
 */
public class JimNewsObservable extends JimAbstractObservable {
	// �۲��߼���
	private List<JimDecentAbstractObserver> mDAOList = new ArrayList<JimDecentAbstractObserver>();

	/**
	 * ʵ�ֳ��󱻹۲����е���ӹ۲��ߵķ���
	 */
	@Override
	public void addDecentObserver(JimDecentAbstractObserver DAO) {
		// TODO Auto-generated method stub
		mDAOList.add(DAO);
		super.addDecentObserver(DAO);
	}

	/**
	 * ʵ�ֳ��󱻹۲����е�ɾ���۲��ߵķ���
	 */
	@Override
	public void deleteDecentObserver(JimDecentAbstractObserver DAO) {
		// TODO Auto-generated method stub
		mDAOList.remove(DAO);
		super.deleteDecentObserver(DAO);
	}

	/**
	 * ʵ�ֳ��󱻹۲����е�֪ͨ����
	 */
	@Override
	public void notifyAllDecentObserver(String... strings) {
		// TODO Auto-generated method stub
		for (JimDecentAbstractObserver d:mDAOList){
			
		}
			super.notifyAllDecentObserver(strings);
	}
}
