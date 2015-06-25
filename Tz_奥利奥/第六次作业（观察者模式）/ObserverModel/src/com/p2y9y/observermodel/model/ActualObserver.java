package com.p2y9y.observermodel.model;

import android.util.Log;

public class ActualObserver extends BaseObserver {

	@Override
	public void dataChange() {
		Log.i("con.p2y9y.observermodel", "ondatachange");
	}

}
