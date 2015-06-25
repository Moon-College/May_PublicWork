package com.tz.michael.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tz.michael.utils.BaseAsyncTask;
import com.tz.michael.utils.MyBaseAsyncTaskTry;
import com.tz.michael.utils.ReflectionUtils;

public class AsyncTaskTryActivity extends Activity implements OnClickListener {
	
	private TextView tv_show;
	private ProgressBar pro_bar;
	private Button btn_start,btn_download,btn_cancel;
	private ImageView img;
	private Context mContext;
	/**sd卡路径*/
	private final String SDPATH=Environment.getExternalStorageDirectory().getAbsolutePath();
	/**图片路径*/
	private String path="http://news.shangdu.com/gaoqing/20110329/P_260797_1__853934915.jpg";
    /**文件eg ：apk的路径*/
	private String file_path="http://news.shangdu.com/gaoqing/20110329/P_260797_1__853934915.jpg";
	private MyBaseAsyncTaskTry baseTry;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext=this;
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {//给主线程设置一个处理运行时异常的handler  
        	  
            public void uncaughtException(Thread thread, final Throwable ex) {  
  
                StringWriter sw = new StringWriter();  
                PrintWriter pw = new PrintWriter(sw);  
                ex.printStackTrace(pw);  
                  
                StringBuilder sb = new StringBuilder();  
                  
                sb.append("Version code is ");  
                sb.append(Build.VERSION.SDK_INT + "\n");//设备的Android版本号  
                sb.append("Model is ");  
                sb.append(Build.MODEL+"\n");//设备型号  
                sb.append(sw.toString());  
  
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);  
                sendIntent.setData(Uri.parse("mailto:1163216846@qq.com"));//发送邮件异常到csdn@csdn.com邮箱  
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "bug report");//邮件主题  
                sendIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());//堆栈信息  
                startActivity(sendIntent);  
                finish();  
            }  
        }); 
        ReflectionUtils.findViews(this);
        btn_start.setOnClickListener(this);
        btn_download.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }
    /**
     * 点击事件
     */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
//			MyAsyncTask task=new MyAsyncTask();
//			task.execute(path);
			MyBaseAsyncTaskBitmapTry bitmapTask=new MyBaseAsyncTaskBitmapTry(mContext, pro_bar, tv_show);
			bitmapTask.execute(path,null);
			break;
		case R.id.btn_download:
			baseTry = new MyBaseAsyncTaskTry(mContext,pro_bar, tv_show);
			baseTry.execute(file_path,null);
			break;
		case R.id.btn_cancel:
			if(baseTry!=null){
				baseTry.cancel(true);
			}
			break;
		default:
			break;
		}
	}
	
	class MyBaseAsyncTaskBitmapTry extends BaseAsyncTask {

		public MyBaseAsyncTaskBitmapTry(Context context,ProgressBar progressBar, TextView tv) {
			super(context,progressBar, tv);
		}

		@Override
		protected void onPostExecute(File result) {
			super.onPostExecute(result);
			Bitmap bitmap=BitmapFactory.decodeFile(result.getAbsolutePath());
			img.setImageBitmap(bitmap);
		}
	}
	
	
	/**
	 * 仅仅适合于图片
	 * @author admin
	 *
	 */
	class MyAsyncTask extends AsyncTask<String, Integer, Bitmap>{

		/**
		 * 改方法紧接onPreExecute方法之后，属于后台线程，耗时操作放在此方法内
		 */
		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = null;
			try {
				bitmap = getBitmapFromNet(params[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return bitmap;
		}

		/**
		 * 根据路径下载图片到SD卡，并返回一个Bitmap
		 * @param string 路径
		 * @throws IOException 
		 */
		private Bitmap getBitmapFromNet(String string) throws IOException {
			URL url=new URL(string);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5000);
			if(conn.getResponseCode()==200){
				//设置进度条的最大值
				pro_bar.setMax(conn.getContentLength());
				InputStream in=conn.getInputStream();
				FileOutputStream fo=new FileOutputStream(new File(SDPATH+File.separator+string.substring(string.lastIndexOf("/"))));
				int len=0;
				byte[] buffer=new byte[1024];
				while((len=in.read(buffer))!=-1){
					fo.write(buffer, 0, len);
					publishProgress(len);
					fo.flush();
				}
				in.close();
				fo.close();
				Bitmap bitmap=BitmapFactory.decodeFile(SDPATH+File.separator+string.substring(string.lastIndexOf("/")));
				return bitmap;
			}else{
				return null;
			}
		}

		/**
		 * 异步任务首先会执行该方法，一些初始化工作可以放在此处
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		/**
		 * 后台线程执行结束会调用该方法，该方法在主线程,可以在该方法中更新UI
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
			img.setImageBitmap(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			pro_bar.setProgress(pro_bar.getProgress()+values[0]);
			tv_show.setText(100*pro_bar.getProgress()/pro_bar.getMax()+"%");
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	}
}