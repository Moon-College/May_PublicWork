package com.tz.observer;

public class RealNumberObserveable extends DannyNumberObserveable{
	
	/**
	 * ֪ͨ�۲��ߣ����ݷ����仯
	 * @param dannyNumberObserver
	 */
	public void notifyObserver(DannyNumberObserver dannyNumberObserver) {
		dannyNumberObserver.onChanged();
	}
	
	/**
	 * ֪ͨ���й۲��ߣ����ݷ����˱仯
	 */
	public void notifyAllObserver() {
		for(DannyNumberObserver dannyNumberObserver :mAllObservers) {
			dannyNumberObserver.onChanged();
		}
	}
}
