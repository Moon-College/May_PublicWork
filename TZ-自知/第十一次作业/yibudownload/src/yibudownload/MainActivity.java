package yibudownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.yibudownload.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	//使用注解对field进行初始化，以及事件的注册
	@InjectionName private TextView imgjindu;
	@InjectionName private TextView apkjindu;
	@InjectionName private ProgressBar imgpro;
	@InjectionName private ProgressBar apkpro;
	@InjectionName(values = "downLoadImage") private Button btnimg;
	@InjectionName(values = "downLoadApk") private Button btnapk;
	@InjectionName private ImageView img;
	private String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();

	private boolean imgLoad = false;
	private boolean apkLoad = false;
	
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//初始化，并注册事件
		IcoUtilsCommon.Doinit(this);
		
		context = this;
	}

	/**
	 * 下载图片
	 */
	public void downLoadImage() {
		imgLoad = true;
		new DownLoadImage().execute("http://img.my.csdn.net/uploads/201309/01/1378037148_7104.jpg");
	}

	/**
	 * 下载Apk，并安装
	 */
	public void downLoadApk() {
		apkLoad = true;
		new DownLoadImage().execute("http://file.dingdone.com/dingdone_pre_android/debug/59b6e8b83b516434b24d3c00c30e79d7/8419074a-0f57-11e5-be7d-5254b38ad443.apk");
	}

	class DownLoadImage extends AsyncTask<String, Integer, Bitmap> {

		private int imgmaxSize;
		private int apkmaxSize;
		private boolean isSuccess;
		private File file;

		/**
		 * 下载任务
		 * @param str
		 * @return
		 */
		private boolean DownLoadData(String str) {
			URL url;
			try {
				url = new URL(str);
				HttpURLConnection huc = (HttpURLConnection) url
						.openConnection();
				huc.setConnectTimeout(15000);
				huc.setRequestMethod("GET");
				if (huc.getResponseCode() == 200) {
					//如果是下载图片就设置图片进度的大小
					if (imgLoad) {
						imgmaxSize = huc.getContentLength();
						imgpro.setMax(imgmaxSize);
					}
					//如果是下载APK就设置APK进度的大小
					if (apkLoad) {
						apkmaxSize = huc.getContentLength();
						apkpro.setMax(apkmaxSize);
					}

					InputStream ips = huc.getInputStream();
					String fileName = str.substring(str.lastIndexOf("/"));

					FileOutputStream fops = new FileOutputStream(SDCARD + "/" + fileName);
					int length = 0;  
					byte[] buffer = new byte[1024*4];
					while ((length = ips.read(buffer)) != -1) {
						fops.write(buffer,0,length);
						this.publishProgress(length);
					}
					fops.close();
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			
			file = new File(SDCARD, params[0].substring(params[0].lastIndexOf("/")));
			Bitmap bitmap = null;
			//如果目标存在就不去下载而是取缓存的文件，否则去下载文件
			if (file.exists()) {
				if ( imgLoad ) {
					bitmap = BitmapFactory.decodeFile(file.toString());
				}
				
				if ( apkLoad ) {
					isSuccess = true;
				}
			} else {
				isSuccess = DownLoadData(params[0]);
				if ( isSuccess ) {
					bitmap = BitmapFactory.decodeFile(file.toString());
				}
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			//下载图片成功就显示图片。否则显示默认图片
			if (null == result) {
				if ( imgLoad ) {
					imgLoad = false;
					img.setBackgroundColor(Color.BLUE);
					imgpro.setMax(0);
					imgpro.setProgress(0);
					imgpro.setVisibility(View.GONE);
					imgjindu.setVisibility(View.GONE);
				}
				
			} else {
				if ( imgLoad ) {
					imgLoad = false;
					img.setBackgroundDrawable(new BitmapDrawable(result));
					imgpro.setMax(0);
					imgpro.setProgress(0);
					imgpro.setVisibility(View.GONE);
					imgjindu.setVisibility(View.GONE);
				}
			} 
			
			//如果下载APK成功就去安装
			if ( apkLoad && isSuccess ) {
				apkLoad = false;
				//anzhuang
				apkpro.setMax(0);
				apkpro.setProgress(0);
				apkpro.setVisibility(View.GONE);
				apkjindu.setVisibility(View.GONE);
				
				Intent intent =new Intent(Intent.ACTION_VIEW);
			    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");  
			    context.startActivity(intent); 
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			
			//处理下载进度的问题
			int length = values[0];
			if (imgLoad) {
				if ( imgpro.getVisibility() != 0) {
					imgpro.setVisibility(View.VISIBLE);
					imgjindu.setVisibility(View.VISIBLE);
				}
				imgpro.setProgress(imgpro.getProgress() + length);
				String text = "下载进度:" + 100 * imgpro.getProgress() / imgmaxSize + "%";
				imgjindu.setText(text);
				img.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeFile(file.toString())));
			}
			
			if ( apkLoad ) {
				if ( apkpro.getVisibility() != 0) {
					apkpro.setVisibility(View.VISIBLE);
					apkjindu.setVisibility(View.VISIBLE);
				}
				apkpro.setProgress(apkpro.getProgress() + length);
				String text = "下载进度:" + 100 * apkpro.getProgress() / apkmaxSize + "%";
				apkjindu.setText(text);
			}
		}
	}
	
	/**
	 * 卸载程序的代码
	 */
	private void unInstall(String packageName) {
		Uri packageURI = Uri.parse(packageName);   
		Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);   
		startActivity(uninstallIntent);
	}

}
