package com.qfx.observe.mode;

import android.util.Log;

/**
 * 真实的观察者
 * @author Administrator
 *
 */
public class RealNumberObserver implements NumberObserver {

	@Override
	public void onChanged() {
		Log.i("INFO", "哈哈哈，我已经看到了数据变为2了");
	}

}
