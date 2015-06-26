package com.ccgao.observer.designpattern;

import android.util.Log;

public class RealObserver extends NumObserver {

	@Override
	protected void onchange() {
		Log.i("INFO", "hahaha,I have seen the number 2");
	}

}
