package com.myandroid.fileSearch.observer;

import android.util.Log;

public class RealObserver extends Observer {

	@Override
	public void Changed() {
		Log.i("INFO", "视图内容发生了改变------------------------------------------");
	}

}
