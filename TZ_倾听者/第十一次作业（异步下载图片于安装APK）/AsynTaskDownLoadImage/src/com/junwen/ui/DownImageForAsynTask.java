package com.junwen.ui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asyntaskdownloadimage.R;
import com.junwen.constant.Constant;

public class DownImageForAsynTask extends Activity implements OnClickListener {

	private Button downImage; //下载图片
	private ProgressBar bar; //进度条
	private ImageView img; //图片
	private TextView text; //文本

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.downimage_layout);
		initView();
	}

	/**
	 * 初始化
	 */
	private void initView() {
		//绑定
		downImage = (Button) findViewById(R.id.btn_down);
		bar = (ProgressBar) findViewById(R.id.progress);
		img = (ImageView) findViewById(R.id.image);
		text = (TextView) findViewById(R.id.text);
		//设置事件
		downImage.setOnClickListener(this);
	}

	/**
	 * 按了下载图片
	 */
	@Override
	public void onClick(View v) {
		
		DownImage task = new DownImage(); //创建一个异步
		task.execute(Constant.IMG_PAHT); //启动异步
	}
	
	private   class DownImage extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		@Override
		protected Bitmap doInBackground(String... params) {
			
			FileOutputStream fos = null ;
			
			BufferedOutputStream bos = null;
			
			BufferedInputStream bis  = null;
			
			InputStream is = null;
			
			String root = params[0]; //获取路径
			
			HttpClient client = new DefaultHttpClient(); //创建HttpClient
			
			HttpGet httpGet = new HttpGet(root); //创建HttpGet请求方式
			
			try {
				HttpResponse response = client.execute(httpGet); //执行这个get请求,返回一个返回对象
				
				if(response.getStatusLine().getStatusCode() == 200){
					//如果成功连接，可以获取数据
//					return BitmapFactory.decodeStream( response.getEntity().getContent());    //直接用bitmap返回这个图片
					
					//这里采用把图片存入到sd中，更新进度条，并且从sd卡中读取出图片，粘贴在ImageView上
					is = response.getEntity().getContent();
					
					bar.setMax((int) response.getEntity().getContentLength()); //设置这个进度条的最大值
						
					String path = Environment.getExternalStorageDirectory().getAbsolutePath().toString().trim() + "/" + root.lastIndexOf("/"); //获取文件存放路径
					
					fos  = new FileOutputStream(path); //创建文件输出对象\
					
					bos = new BufferedOutputStream(fos); //创建缓冲输出流对象
					
					bis = new BufferedInputStream(is); //创建缓冲输入流对象
					
					int len = 0;
					byte [] buffer = new byte[1024];
					while( (len = bis.read(buffer)) != -1){
						bos.write(buffer, 0, len); //写入sd卡
						this.publishProgress(len);  //更新进度条
					}
					//对流进行关闭
					bis.close();
					bos.close();
					fos.close();
					is.close();
					Bitmap bitmap = BitmapFactory.decodeFile(path); //根据路径，获取图片
					return bitmap; //返回图片
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * 执行主线程
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
			if(result != null) {
				img.setImageBitmap(result);
				text.setText("加载完成");
			}
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			
			if(values[0] != null) {
				super.onProgressUpdate(values);
				int value = values[0]; //获得每次更新进度的数量
				bar.setProgress(bar.getProgress()+value); //设置为当前进度加上新更新的进度
				int name =  100 * bar.getProgress() / bar.getMax() ; //计算当前的进度
				text.setText("当前进度为："+name+"%"); //设置文本
			}
			
		}
	}
}
