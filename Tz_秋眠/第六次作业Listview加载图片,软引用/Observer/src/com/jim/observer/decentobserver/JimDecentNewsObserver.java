package com.jim.observer.decentobserver;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * �۲���
 * 
 * @author Jim
 * 
 */
public class JimDecentNewsObserver extends JimDecentAbstractObserver {
	private static int mObserverSerial = 0;// ���л�����
	private int mObserverNum = 0;// ����۲��ߵ�ID
	private Context mContext;

	public JimDecentNewsObserver(Context context) {
		mContext = context;
		mObserverNum = mObserverSerial++;// �����кŵ�������Ϊ����۲��ߵ�ID
	}

	/**
	 * ʵ�ֳ���۲����еĸ��·���
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
