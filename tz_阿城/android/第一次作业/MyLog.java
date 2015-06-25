package debugway;
/**
 *@author hc
 *@���� ��LOGCAT���м�¼��־
 *@
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class MyLog {

	
		public static final int BYINFOSEARCH = 0;
		public static final int SHOWBYV      = 1;
		public static final int SHOWBYD      = 2;
		public static final int SHOWBYI      = 3;
		public static final int SHOWBYW      = 4;
		public static final int SHOWBYE      = 5;
		private static final Context JoballActivity = null;

		
	
	private static boolean isDebug = true;//Ĭ��Ϊ�ǵ���ģʽ
    /**
     * @author  hucheng
     * description  ����ϵͳ��־������ϵͳ���ԵĿ�����ر�
     * @param   type LOG������Ϣ   tag LOG��Ϣ����   msg LOG��������Ϣ
     * @isDebug 
     */
    public static int println(int type,String tag,String msg) {
  	  if(isDebug) {  
  		  return Log.println(type, tag, msg); 
  	  }
  	  return 0;
    }    
    /**
     * @author  hucheng
     * description  �����Ƿ�Ϊ����ģʽ �������ΪTrue��Ϊ����ģʽ���������ΪFalseΪ�ǵ���ģʽ
     * @param   isDebugReslut  �Ƿ���Կ���
     * @isDebug 
     */ 
    public void setDebug(boolean isDebugReslut )
    {
    	isDebug = isDebugReslut;
    }
    /**
     * @author  hucheng
     * description  �����Ƿ�Ϊ����ģʽ �������ΪTrue��Ϊ����ģʽ���������ΪFalseΪ�ǵ���ģʽ
     * @param   v������Ϣ¼��   tag LOG��Ϣ����   msg LOG��������Ϣ
     * @date  2015-6-9 
     */    
    public static int v(String tag, String msg) {
        return Log.v(tag, msg);
    }
    /**
     * @author  hucheng
     * description  �����Ƿ�Ϊ����ģʽ �������ΪTrue��Ϊ����ģʽ���������ΪFalseΪ�ǵ���ģʽ
     * @param   d������Ϣ¼��   tag LOG��Ϣ����   msg LOG��������Ϣ
     * @date  2015-6-9 
     */    
    public static int d(String tag, String msg) {
        return Log.d(tag, msg);
    }
    /**
     * @author  hucheng
     * description  �����Ƿ�Ϊ����ģʽ �������ΪTrue��Ϊ����ģʽ���������ΪFalseΪ�ǵ���ģʽ
     * @param   i������Ϣ¼��   tag LOG��Ϣ����   msg LOG��������Ϣ
     * @date  2015-6-9 
     */    
    public static int i(String tag, String msg) {
        return Log.i(tag, msg);
    }
    /**
     * @author  hucheng
     * description  �����Ƿ�Ϊ����ģʽ �������ΪTrue��Ϊ����ģʽ���������ΪFalseΪ�ǵ���ģʽ
     * @param   w������Ϣ¼��   tag LOG��Ϣ����   msg LOG��������Ϣ
     * @date  2015-6-9 
     */    
    public static int w(String tag, String msg) {
        return Log.w(tag, msg);
    }
    /**
     * @author  hucheng
     * description  �����Ƿ�Ϊ����ģʽ �������ΪTrue��Ϊ����ģʽ���������ΪFalseΪ�ǵ���ģʽ
     * @param   e������Ϣ¼��   tag LOG��Ϣ����   msg LOG��������Ϣ
     * @date  2015-6-9 
     */    
    public static int e(String tag, String msg) {
        return Log.e(tag, msg);
    }

  
   public static int  showLoginfo(Activity activity , String cmdLine) throws IOException {
		MyLog.println(Log.WARN, "INFO", "start connectlog");
		StringBuffer sb = new StringBuffer();
	//	ArrayList<String> cmdLine = new ArrayList<String>();//�ռ��ò���ҪADB
	    Process exec = Runtime.getRuntime().exec(cmdLine);
	    InputStream inPutString = exec.getInputStream();
	    InputStreamReader inputStreamReader = new InputStreamReader(inPutString);//װ����
	    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	    String str;
	    int k = 0;
	    while (((str = bufferedReader.readLine())!= null)&&k<1000){
	    	sb.append(str); 
	    	sb.append("\n");
	    	 k++;
	    }

	    Toast.makeText(activity, sb.toString(), 5000).show();
	    return 0 ;
    }


}

