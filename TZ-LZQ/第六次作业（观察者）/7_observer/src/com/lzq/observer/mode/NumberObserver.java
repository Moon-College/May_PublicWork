package com.lzq.observer.mode;

import android.util.Log;

public class NumberObserver extends LZQNumberObserver{

	@Override
	public void onChange() {
		Log.i("s", "我看见一个2 啦，哈哈哈，你个煞笔");
	}

}
