package debugway;
/**
 *@author hc
 *@作用 对LOGCAT进行记录日志
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

		
	
	private static boolean isDebug = true;//默认为非调试模式
    /**
     * @author  hucheng
     * description  存入系统日志，方斌系统调试的开启与关闭
     * @param   type LOG级别信息   tag LOG信息分类   msg LOG的数据信息
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
     * description  设置是否为调试模式 如果设置为True就为调试模式，如果设置为False为非调试模式
     * @param   isDebugReslut  是否调试开关
     * @isDebug 
     */ 
    public void setDebug(boolean isDebugReslut )
    {
    	isDebug = isDebugReslut;
    }
    /**
     * @author  hucheng
     * description  设置是否为调试模式 如果设置为True就为调试模式，如果设置为False为非调试模式
     * @param   v级别信息录入   tag LOG信息分类   msg LOG的数据信息
     * @date  2015-6-9 
     */    
    public static int v(String tag, String msg) {
        return Log.v(tag, msg);
    }
    /**
     * @author  hucheng
     * description  设置是否为调试模式 如果设置为True就为调试模式，如果设置为False为非调试模式
     * @param   d级别信息录入   tag LOG信息分类   msg LOG的数据信息
     * @date  2015-6-9 
     */    
    public static int d(String tag, String msg) {
        return Log.d(tag, msg);
    }
    /**
     * @author  hucheng
     * description  设置是否为调试模式 如果设置为True就为调试模式，如果设置为False为非调试模式
     * @param   i级别信息录入   tag LOG信息分类   msg LOG的数据信息
     * @date  2015-6-9 
     */    
    public static int i(String tag, String msg) {
        return Log.i(tag, msg);
    }
    /**
     * @author  hucheng
     * description  设置是否为调试模式 如果设置为True就为调试模式，如果设置为False为非调试模式
     * @param   w级别信息录入   tag LOG信息分类   msg LOG的数据信息
     * @date  2015-6-9 
     */    
    public static int w(String tag, String msg) {
        return Log.w(tag, msg);
    }
    /**
     * @author  hucheng
     * description  设置是否为调试模式 如果设置为True就为调试模式，如果设置为False为非调试模式
     * @param   e级别信息录入   tag LOG信息分类   msg LOG的数据信息
     * @date  2015-6-9 
     */    
    public static int e(String tag, String msg) {
        return Log.e(tag, msg);
    }

  
   public static int  showLoginfo(Activity activity , String cmdLine) throws IOException {
		MyLog.println(Log.WARN, "INFO", "start connectlog");
		StringBuffer sb = new StringBuffer();
	//	ArrayList<String> cmdLine = new ArrayList<String>();//收集拿不需要ADB
	    Process exec = Runtime.getRuntime().exec(cmdLine);
	    InputStream inPutString = exec.getInputStream();
	    InputStreamReader inputStreamReader = new InputStreamReader(inPutString);//装饰器
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

