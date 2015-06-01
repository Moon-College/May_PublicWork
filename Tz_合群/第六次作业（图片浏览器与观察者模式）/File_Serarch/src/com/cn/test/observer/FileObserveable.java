package com.cn.test.observer;

/**
 * Created on2015-5-30 обнГ2:49:50 FileObserveable.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class FileObserveable  extends Observeable{
	public void notifyObserver(Observer observer){
		observer.onChanged();
	}
	public void notifyAllObserver(){
		for (Observer iterable_element : mObservers) {
			iterable_element.onChanged();
		}
	}
}
