package com.decent.observable;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.decent.decentobserver.DecentAbstractObserver;

public class DecentNewsObserable extends DecentAbstractObservable
{
	private List<DecentAbstractObserver> mDAOList = new ArrayList<DecentAbstractObserver>();

	/**
	 * 添加观察者
	 * @param dao 要添加的观察者
	 */
	@Override
	public void addDecentObserver(DecentAbstractObserver dao)
    {
		mDAOList.add(dao);
		super.addDecentObserver(dao);
    }
	
	/**
	 * 删除观察者
	 * @param dao 要删除的观察者
	 */
	@Override
	public void delDecentObserver(DecentAbstractObserver dao)
	{
		mDAOList.remove(dao);
		super.delDecentObserver(dao);
	}
	
	@Override
	public void notifyAllDecentObserver(String... args)
	{
		Log.d("DecentNewsObserable", "into notifyAllDecentObserver");
		for(DecentAbstractObserver dao:mDAOList)
		{
			dao.onChange(args);
		}
		// TODO Auto-generated method stub
		super.notifyAllDecentObserver(args);
	}

}
