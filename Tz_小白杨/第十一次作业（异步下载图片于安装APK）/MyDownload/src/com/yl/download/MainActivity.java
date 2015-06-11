package com.yl.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yl.constant.Constant;

public class MainActivity extends Activity implements OnClickListener {
	private Button button;
	private ProgressBar pb;
	private Button stop;
	private ImageView image;
	private TextView tv;
	private boolean isContinue;
	private int maxLength;	
	private String url="http://192.168.43.97:8080/MyServlet/img/img.jpg";
	private String file;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        button.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

	private void initView() {
		button=(Button) findViewById(R.id.button);
		pb=(ProgressBar) findViewById(R.id.pro);
		image=(ImageView) findViewById(R.id.img);
		tv=(TextView) findViewById(R.id.tv);
		stop=(Button) findViewById(R.id.stop);
		stop.setEnabled(false);
		
	}
	
	

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			isContinue=true;
			button.setEnabled(false);
			stop.setEnabled(true);
			MyTask mytask=new MyTask();
			mytask.execute(url);			
			break;
		case R.id.stop:
			if(isContinue){
				isContinue=false;
				button.setEnabled(true);
				stop.setEnabled(false);
				Toast.makeText(this, "停止", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
		
		
	}
	
	
	class MyTask extends AsyncTask<String, Integer, Bitmap>{

		@Override
		protected Bitmap doInBackground(String... params) {
			try {
				return this.getImageByUrl(params[0]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			pb.setProgress(pb.getProgress()+values[0]);
			int num=pb.getProgress()*100/maxLength;
			String schedule="进度："+num+"%";
			tv.setText(schedule);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(result!=null && isContinue){
				image.setImageBitmap(result);
				button.setEnabled(false);
				stop.setEnabled(false);
				deleteFile(file);
				//Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
			}
		}
		/**
		 * 通过url获取image
		 * @param url
		 * @return
		 * @throws IOException
		 */
		private Bitmap getImageByUrl(String url) throws IOException{
			URL u=new URL(url);
			Bitmap bitmap=null;
			file=Constant.URL+"/"+System.currentTimeMillis()+".jpg";
			FileOutputStream fos=new FileOutputStream(file);
			HttpURLConnection conn=(HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(3000);
			maxLength=conn.getContentLength();
			pb.setMax(maxLength);
			if(conn.getResponseCode()==200){
				InputStream inputStream = conn.getInputStream();
				int p=0;
				byte []bt=new byte[1024];			
				while((p=inputStream.read(bt))!=-1){
					if(isContinue){
						fos.write(bt, 0, p);
						publishProgress(p);
					}else{
						break;
					}
				}
				fos.close();
				File f=new File(file);
				if(f.isFile()){
					bitmap=BitmapFactory.decodeFile(file);
				}
			}
			return bitmap;
		}
		/**
		 * 删除文件
		 * @param url
		 */
		private void deleteFile(String url){
			File f=new File(url);
			if(f.exists() && f.isFile()){
				f.delete();
			}
		}
		
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pb.setProgress(0);
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			pb.setProgress(0);
		}
		
	}
	
	
	
	
	
	
}