package com.limz.observerdemo.model;

import android.content.Context;

public abstract class AbstractObserver {
	public abstract void onChange(Context context);
}
