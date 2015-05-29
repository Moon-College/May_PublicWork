package com.decent.decentobserver;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 新闻观察者，一个具体的观察者类
 * @author 火牛_KOP
 *
 */
public class DecentNewsObserver extends DecentAbstractObserver
{
	private static int mObserverSerial=0;
	private int mObserverNum=0;
    private Context mContext;
    
	public DecentNewsObserver(Context context)
	{
		mContext = context;
		mObserverNum =  mObserverSerial++;

	}
	@Override
	public void onChange(String... args)
	{
		Log.d("DecentNewsObserver", "into onChange");
		Toast.makeText(mContext, "mObserverNum="+mObserverNum+",received message:"+args[0], Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
		super.onChange(args);
	}

}
