package com.xigua.observer;

public class RealAObserver extends AObservered{
	/**
	 * ֪ͨ�۲������ݷ����仯
	 * @param dannyNumberObserver
	 */
	public void notifyObserver(Observer dannyNumberObserver){
		dannyNumberObserver.onChanged();
	}


	/**
	 * ͨ�����й۲������ݷ����仯
	 */
	public void notifyAllObserver(){
		for(Observer dannyNumberObserver:mDannyNumberObservers){
			dannyNumberObserver.onChanged();
		}
	}
	
}
