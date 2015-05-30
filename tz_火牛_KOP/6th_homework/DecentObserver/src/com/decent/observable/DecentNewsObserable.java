package com.decent.observable;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.decent.decentobserver.DecentAbstractObserver;

public class DecentNewsObserable extends DecentAbstractObservable
{
	private List<DecentAbstractObserver> mDAOList = new ArrayList<DecentAbstractObserver>();

	/**
	 * ��ӹ۲���
	 * @param dao Ҫ��ӵĹ۲���
	 */
	@Override
	public void addDecentObserver(DecentAbstractObserver dao)
    {
		mDAOList.add(dao);
		super.addDecentObserver(dao);
    }
	
	/**
	 * ɾ���۲���
	 * @param dao Ҫɾ���Ĺ۲���
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
