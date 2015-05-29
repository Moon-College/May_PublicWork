package com.decent.observable;

import java.util.ArrayList;
import java.util.List;

import com.decent.decentobserver.DecentAbstractObserver;

/**
 * 抽象的可被观察者
 * 
 * @author 火牛_KOP
 * 
 */
public abstract class DecentAbstractObservable
{
	private List<DecentAbstractObserver> mDAOList = new ArrayList<DecentAbstractObserver>();

	/**
	 * 添加观察者
	 * @param dao 要添加的观察者
	 */
	public void addDecentObserver(DecentAbstractObserver dao)
    {
		
    }
	
	/**
	 * 删除观察者
	 * @param dao 要删除的观察者
	 */
	public void delDecentObserver(DecentAbstractObserver dao)
	{
		
	}
	
	/**
	 * 通知所有观察者
	 * @param args 通知内容采参数
	 */
	public void notifyAllDecentObserver(String... args)
	{

	}
}
