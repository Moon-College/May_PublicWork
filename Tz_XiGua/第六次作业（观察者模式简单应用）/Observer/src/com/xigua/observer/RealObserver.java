package com.xigua.observer;

import android.util.Log;

public class RealObserver extends Observer{

	@Override
	public void onChanged() {
		Log.i("INFO", "HAHAHHA, I HAVA SEEN THE NUMBER TWO");
	}

}
