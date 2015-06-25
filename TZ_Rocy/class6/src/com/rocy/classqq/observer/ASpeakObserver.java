package com.rocy.classqq.observer;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

/**
 * 观察者
 * @author Administrator
 *
 */
public abstract class ASpeakObserver {
	protected Context context;
	
	public ASpeakObserver(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}

	protected abstract void onSpeak2() ;
    protected abstract void onSpeak1() ;
       
}
