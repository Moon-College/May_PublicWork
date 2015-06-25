package com.tz.michael.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 自定义异步，封装了文件的下载,及UI的刷新
 * @author michael
 *
 * @param <T>
 */
public class BaseAsyncTask  extends AsyncTask<String, Integer, File>{

	private String savePath=Environment.getExternalStorageDirectory().getAbsolutePath();
	/**进度条*/
	private ProgressBar progressBar;
	/**用来显示数字进度的TextView*/
	private TextView tv;
	/**上下文对象，用来处理后续，步入弹出对话框，安装应用或执行一些需要上下文的操作*/
	private Context mContext;
	private final int PIC=1;
	private final int FILE=2;
	private int flag;
	
	public BaseAsyncTask(){
		this(null);
	}
	
	public BaseAsyncTask(Context context){
		this(context,null,null);
	}
	
	/**
	 * 构造函数，可以传入两个控件
	 * @param progressBar 
	 * @param tv
	 */
	public BaseAsyncTask(ProgressBar progressBar,TextView tv){
		this(null,progressBar,tv);
	}
	
	public BaseAsyncTask(Context context,ProgressBar progressBar,TextView tv){
		this.mContext=context;
		this.progressBar=progressBar;
		this.tv=tv;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected File doInBackground(String... params) {
		if(this.isCancelled()){
			return null;
		}
		File f = null;
		try {
			f = getFileFromNet(params[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(params[1]!=null&&params[1].trim().length()!=0){
			savePath=params[1];
		}
		return f;
	}

	private File getFileFromNet(String string) throws IOException {
		URL url=new URL(string);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		if(conn.getResponseCode()==200){
			File f=new File(savePath+File.separator+string.substring(string.lastIndexOf("/")));
			//设置进度条的最大值
			if(progressBar!=null){
				progressBar.setMax(conn.getContentLength());
			}
			InputStream in=conn.getInputStream();
			FileOutputStream fo=new FileOutputStream(f);
			int len=0;
			byte[] buffer=new byte[1024];
			while((len=in.read(buffer))!=-1){
				fo.write(buffer, 0, len);
				if(progressBar!=null){
					publishProgress(len);
				}
				fo.flush();
			}
			in.close();
			fo.close();
			return f;
		}else{
			return null;
		}
	}

	@Override
	protected void onPreExecute() {
		
	}

	/**
	 * 子类可以重写此方法,来实现自己的特有操作
	 */
	@Override
	protected void onPostExecute(File result) {
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		if(progressBar!=null){
			progressBar.setProgress(progressBar.getProgress()+values[0]);
			if(tv!=null){
				tv.setText(100*progressBar.getProgress()/progressBar.getMax()+"%");
			}
		}
		
		
	}

	@Override
	protected void onCancelled() {
		this.cancel(true);
	}
}
