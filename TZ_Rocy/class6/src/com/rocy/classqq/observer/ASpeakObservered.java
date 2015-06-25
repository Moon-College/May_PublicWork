package com.rocy.classqq.observer;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

/**
 * 被观察者
 * @author Administrator
 *
 */
public abstract class ASpeakObservered {
	protected Context context;
	
	
	
    public ASpeakObservered(Context context) {
		// TODO Auto-generated constructor stub
		list=new ArrayList<ASpeakObserver>();
		this.context=context;
	}
	//被观察者的集合
	  protected List<ASpeakObserver> list;
	
		/**
		 * 
		 * @param observer
		 * @return
		 */
       public boolean registerObserver(ASpeakObserver observer) {
    	 //添加被观察者
    	    return list.add(observer);
       }
       
       public boolean unRegisterObserver(ASpeakObserver observer){
    	   return list.remove(observer);
       }
       
       public  void unAllRegisterObserver(){
    	   list.clear();
       }
}
