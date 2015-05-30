package com.tz.michael.activity.utils;
/**
 * 真实的额被观察者
 * @author szm
 *
 */
public class RealObServerable extends AbstractObServerable{

	/**
	 * Invokes onChanged on each observer. Called when the data set being observed has
     * changed, and which when read contains the new state of the data.
	 */
	public void notifyChanged(){
		synchronized (obServerSets) {
			/*
			 * since onChanged() is implemented by the app, it could do anything, including
             *	removing itself from {@link mObservers} - and that could cause problems if
             *	an iterator is used on the ArrayList {@link mObservers}.
             *	to avoid such problems, just march thru the list in the reverse order.
			 */
			for(int i=obServerSets.size()-1;i>=0;i--){
				obServerSets.get(i).onChanged();
			}
		}
	}
	
	
	
}
