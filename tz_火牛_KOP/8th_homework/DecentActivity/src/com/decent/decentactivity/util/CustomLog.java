package com.decent.decentactivity.util;

import android.util.Log;

public class CustomLog
{
	//���ڱ�ʶ���ڴ�ӡ��ӡ
	private static boolean mNowDebugFlag = true;
	
	/**
	 * ���õ�ǰ�Ƿ��ӡ��־
	 * @param isNowDebug �Ƿ����ڴ�ӡ��־
	 */
    public static void setNowDebugFlag(boolean isNowDebug)
    {
    	mNowDebugFlag = isNowDebug;
    }
    
    /**
     * ��ȡ��ǰ�Ƿ��ӡ��־
     * @return ��ǰ�Ƿ��ӡ��־
     */
    public static boolean getNowDebugFlag()
    {
    	return mNowDebugFlag;
    }
    
    /**
     * ��ӡINFO�����log
     * @param tag log��TAG
     * @param msg log����
     * @return ���ش�ӡ��־�Ƿ�ɹ�
     */
    public static int i(String tag, String msg)
    {
    	if(mNowDebugFlag)
    	{
    		return Log.i(tag,msg);
    	}
    	return 0;
    }
    
    /**
     * ��ӡDEBUG�����log
     * @param tag log��TAG
     * @param msg log����
     * @return ���ش�ӡ��־�Ƿ�ɹ�
     */
    public static int d(String tag, String msg)
    {
    	if(mNowDebugFlag)
    	{
    		return Log.d(tag, msg);
    	}
    	return 0;
    }
    
    /**
     * ��ӡWARN�����log
     * @param tag log��TAG
     * @param msg log����
     * @return ���ش�ӡ��־�Ƿ�ɹ�
     */
    public static int w(String tag, String msg)
    {
    	if(mNowDebugFlag)
    	{
    		return Log.w(tag, msg);
    	}
    	return 0;
    }
    
    /**
     * ��ӡERROR�����log
     * @param tag log��TAG
     * @param msg log����
     * @return ���ش�ӡ��־�Ƿ�ɹ�
     */
    public static int e(String tag, String msg)
    {
    	if(mNowDebugFlag)
    	{
    		return Log.e(tag, msg);
    	}
    	return 0;
    }
}
