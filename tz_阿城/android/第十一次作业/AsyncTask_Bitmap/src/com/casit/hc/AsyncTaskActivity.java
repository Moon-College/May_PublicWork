package com.casit.hc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskActivity extends Activity {
    /** Called when the activity is first created. */
	private Button button;
	private ProgressBar bar;
	private ImageView imageView;
	private final String SD_ROOT =  Environment.getExternalStorageDirectory().getAbsolutePath();
    private int maxLenth;
    TextView textView;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }
   public void initView(){
	   button = (Button) findViewById(R.id.bt);
	   bar = (ProgressBar) findViewById(R.id.pregressbar);
	   imageView = (ImageView) findViewById(R.id.iv);
	   textView = (TextView) findViewById(R.id.tv);
   }
    
    public void start(View view){
    	String image_url = "http://b.hiphotos.baidu.com/image/pic/item/77c6a7efce1b9d16bc8de463f1deb48f8c54646d.jpg";
        MyHttpTask httpTask = new MyHttpTask();
        httpTask.execute(image_url);
    }
    
    private class MyHttpTask extends AsyncTask<String, Integer, Bitmap>{
		//Ԥ���ء���һ��  ��ʼ������
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		//�������Ժ����onpostExecute,ʵ��ԭ����HANDLER 3bu
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			
			super.onPostExecute(result);
			Log.i("INFO", "image");
			imageView.setImageBitmap(result);
		}

        //handle ԭ�� ˢ��UI 
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			int len = values[0];
			bar.setProgress(bar.getProgress()+len);			
			textView.setText("���ؽ���"+String.valueOf(bar.getProgress()*100/maxLenth)+"%");
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}
        //��̨���أ� �߳�ԭ��2bu
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			//����ͼƬ�������ر߸���ͼƬ��
			String url = params[0];
			Bitmap bitmap = null;
			try {
				bitmap = downLoadSister(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}
		private Bitmap downLoadSister(String url) throws IOException{
			Bitmap bitmap = null;
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setConnectTimeout(5000);
		    bar.setProgress(0);
		    imageView.setImageResource(R.drawable.ic_launcher);
		  //  imageView.setImageBitmap(bitmap);
		    if(conn.getResponseCode()==200){
		    	//��ȡ�ļ�����
		    	maxLenth = conn.getContentLength();
		    	bar.setMax(maxLenth);
		    	Log.i("INFO", "�ɹ�");
		    	InputStream inputStream = conn.getInputStream();
		  //  	bitmap = BitmapFactory.decodeStream(inputStream);
		  // ��д��SDK
		    	String fileName = url.substring(url.lastIndexOf("/"));
		    	FileOutputStream os = new FileOutputStream(SD_ROOT+"/"+fileName);
		    	int len = 0;
		    	int i = 0 ;
		    	byte[] buffer = new byte[1024];//���� ���������Ǹ����ڴ�������
		    	while((len = inputStream.read(buffer))!=-1){
		    		os.write(buffer,0,len);
                    //���½�����
		    		this.publishProgress(len);
		    	}
		    	os.close();
		    	bitmap = BitmapFactory.decodeFile(SD_ROOT+"/"+fileName);
		    	return bitmap;
		    }
		    return null;
			
		}
		
    	
		
    }
}