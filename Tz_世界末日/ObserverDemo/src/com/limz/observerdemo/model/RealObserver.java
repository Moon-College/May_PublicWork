package com.limz.observerdemo.model;

import android.content.Context;
import android.widget.Toast;

public class RealObserver extends AbstractObserver {

	@Override
	public void onChange(Context context) {
		if(context == null) {
			return;
		}
		Toast.makeText(context, "你输入了一个数字", Toast.LENGTH_LONG).show();
	}

}
