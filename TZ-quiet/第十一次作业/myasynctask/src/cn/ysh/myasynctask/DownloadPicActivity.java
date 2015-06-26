package cn.ysh.myasynctask;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpStatus;
import cn.ysh.refutil.MyReflection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DownloadPicActivity extends Activity implements OnClickListener {
	private TextView textView;
	private ProgressBar progressBar;
	private Button start;
	private Button pause;
	private Button cancel;
	private MyAsyncTask task;
	private ImageView imageView;
	
	private boolean isPause;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化控件
		MyReflection.initView(this, "findViewById");
		start.setOnClickListener(this);
		cancel.setOnClickListener(this);
		pause.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.start:
			if(task == null){
				synchronized (this) {
					task = new MyAsyncTask();
					imageView.setImageBitmap(null);
					progressBar.setProgress(0);
				}
				task.execute("http://e.hiphotos.baidu.com/image/pic/item/ae51f3deb48f8c54b0629f273a292df5e0fe7f00.jpg");
			}else{
				isPause = false;
			}
			
			start.setClickable(false);
			break;
		case R.id.pause:
			if(task == null){
				Toast.makeText(this, "无法暂停!", Toast.LENGTH_SHORT).show();
			}else{
				isPause = true;
				start.setClickable(true);
			}
			break;
		case R.id.cancel:
			if(task == null){
				Toast.makeText(this, "该任务未开始或已经停止!", Toast.LENGTH_SHORT).show();
			}else{
				boolean isCancel = task.cancel(true);
				if(isCancel){
					Toast.makeText(this, "已停止下载!", Toast.LENGTH_SHORT).show();
					task = null;
					progressBar.setProgress(0);
					isPause = false;
				}else{
					Toast.makeText(this, "停止失败!", Toast.LENGTH_SHORT).show();
				}
				start.setClickable(true);
			}
		default:
			break;
		}
		
	}
	private class MyAsyncTask extends AsyncTask<String,Integer,Bitmap>{
		private int fileLen;
		private RandomAccessFile randomFile;
		//当前下载的字节数
		private long currentPosition;
		private final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			URL url = null;
			try {
				url = new URL(params[0]);
				Log.d("url", ""+url);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setReadTimeout(5000);
				if(conn.getResponseCode() == HttpStatus.SC_OK){
					fileLen = conn.getContentLength();
					progressBar.setMax(fileLen);
					InputStream is = conn.getInputStream();
					String filePath = SDCARD_PATH+params[0].substring(params[0].lastIndexOf("/"));
					Log.d("filePath", filePath);
//					FileOutputStream os = new FileOutputStream(filePath);
					File pic = new File(filePath);
					randomFile = new RandomAccessFile(pic, "rw");
					byte[] buffer = new byte[1024];
					int len = 0;
					while((len = is.read(buffer)) != -1){
						while(isPause){
							Thread.sleep(50);
							start.setClickable(true);
						}
						Thread.sleep(50);
//						os.write(buffer, 0, len);
						randomFile.seek(currentPosition);
						randomFile.write(buffer, 0, len);
						currentPosition = randomFile.getFilePointer();
//						Log.d("len", len+"");
						publishProgress(len);
					}
//					os.close();
					Bitmap bitmap = BitmapFactory.decodeFile(filePath);
					return bitmap;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				return null;
			}
			
		@Override
		protected void onPostExecute(Bitmap result) {
			imageView.setImageBitmap(result);
			imageView.setScaleType(ScaleType.FIT_XY);
			start.setClickable(true);
			task = null;
			currentPosition = 0;
			}
			
		@Override
		protected void onProgressUpdate(Integer... values) {
			progressBar.setProgress(progressBar.getProgress()+values[0]);
			textView.setText("下载进度:"+progressBar.getProgress()*100/fileLen+"%");
//			Log.d("currentLen", progressBar.getProgress()+"");
//			Log.d("total", fileLen+"");
		}
			
		@Override
		protected void onCancelled(Bitmap result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}
			
		

	}

}
