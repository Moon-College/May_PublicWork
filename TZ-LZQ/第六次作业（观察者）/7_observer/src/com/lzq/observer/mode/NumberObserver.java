package com.lzq.observer.mode;

import android.util.Log;

public class NumberObserver extends LZQNumberObserver{

	@Override
	public void onChange() {
		Log.i("s", "�ҿ���һ��2 ���������������ɷ��");
	}

}
