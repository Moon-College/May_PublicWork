package com.decent.observable;

import java.util.ArrayList;
import java.util.List;

import com.decent.decentobserver.DecentAbstractObserver;

/**
 * ����Ŀɱ��۲���
 * 
 * @author ��ţ_KOP
 * 
 */
public abstract class DecentAbstractObservable
{
	private List<DecentAbstractObserver> mDAOList = new ArrayList<DecentAbstractObserver>();

	/**
	 * ��ӹ۲���
	 * @param dao Ҫ��ӵĹ۲���
	 */
	public void addDecentObserver(DecentAbstractObserver dao)
    {
		
    }
	
	/**
	 * ɾ���۲���
	 * @param dao Ҫɾ���Ĺ۲���
	 */
	public void delDecentObserver(DecentAbstractObserver dao)
	{
		
	}
	
	/**
	 * ֪ͨ���й۲���
	 * @param args ֪ͨ���ݲɲ���
	 */
	public void notifyAllDecentObserver(String... args)
	{

	}
}
