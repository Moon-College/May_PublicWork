package com.jim.observer.decentobserver;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 观察者
 * 
 * @author Jim
 * 
 */
public class JimDecentNewsObserver extends JimDecentAbstractObserver {
	private static int mObserverSerial = 0;// 序列化数字
	private int mObserverNum = 0;// 抽象观察者的ID
	private Context mContext;

	public JimDecentNewsObserver(Context context) {
		mContext = context;
		mObserverNum = mObserverSerial++;// 把序列号的数字作为抽象观察者的ID
	}

	/**
	 * 实现抽象观察者中的更新方法
	 */
	@Override
	public void onChange(String... strings) {
		// TODO Auto-generated method stub
		Log.d("JimDecentNewsObserver", "into onChange");
		Toast.makeText(
				mContext,
				"mObserverNum=" + mObserverNum + ",received message:"
						+ strings[0], Toast.LENGTH_SHORT).show();
		super.onChange(strings);
	}
}
