package cn.ysh.myasynctask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpStatus;
import cn.ysh.refutil.MyReflection;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownloadApkActivity extends Activity implements OnClickListener{
	private ProgressBar progressBar;
	private Button start;
	private Button pause;
	private Button cancel;
	private int fileLen;
	private TextView textView;
	private final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyReflection.initView(this, "findViewById");
		start.setOnClickListener(this);
		pause.setVisibility(View.GONE);
		cancel.setVisibility(View.GONE);
	}
	@Override
	public void onClick(View v) {
		MyAsyncTask task = new MyAsyncTask();
		task.execute("http://p.gdown.baidu.com/f9dfcdaaeda0c245453e724b0568a9ebd67dc9afc9f77882f227d27c51ddc1b12ea863fc784c8575b6e47527a8db9aefee1794c680b2fe8aabef64d45f2bb41bcfaf190971c8c0123637f0ef1966f85fe74161405138043cf3d1abf506857349d44d300fdd15cdb53897375a96e3ed4fad6ced408c9cd9674813c2b4d6f89516cba0ede9ed4908b7c6d16ad70f862ad10b33853fd978fb86c58f29b5df2eeb112ac01969bd8b07c589cbda558cd39f1b5cd8e45ee723a887efd27992bbd36cd48fdd1091dcb98deb9f726729013d3d9ecf87d789f91d1a99c25602b57739405fba73486db28fa3f60b70d3dec6b43c82");
		
	}
	
	private class MyAsyncTask extends AsyncTask<String, Integer, Uri>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Uri doInBackground(String... params) {
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
					String filePath = SDCARD_PATH+"/baiduMusic.apk";
					FileOutputStream os = new FileOutputStream(filePath);
					byte[] buffer = new byte[1024];
					int len = 0;
					while((len = is.read(buffer)) != -1){
						os.write(buffer, 0, len);
						publishProgress(len);
					}
					os.close();
					File file = new File(filePath);
					Uri uri = Uri.fromFile(file);
					return uri;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Uri result) {
			Log.d("URI", result+"");
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(result, "application/vnd.android.package-archive");
			startActivity(intent);
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
//			Log.d("currentLen", progressBar.getProgress()+"");
//			Log.d("total", fileLen+"");
			progressBar.setProgress(progressBar.getProgress()+values[0]);
			textView.setText("ÏÂÔØ½ø¶È:"+progressBar.getProgress()*100/fileLen+"%");
		}
		
		@Override
		protected void onCancelled(Uri result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}
		
	}
}
