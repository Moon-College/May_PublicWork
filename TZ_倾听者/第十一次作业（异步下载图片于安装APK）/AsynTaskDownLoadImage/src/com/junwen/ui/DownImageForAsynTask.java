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

	private Button downImage; //����ͼƬ
	private ProgressBar bar; //������
	private ImageView img; //ͼƬ
	private TextView text; //�ı�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.downimage_layout);
		initView();
	}

	/**
	 * ��ʼ��
	 */
	private void initView() {
		//��
		downImage = (Button) findViewById(R.id.btn_down);
		bar = (ProgressBar) findViewById(R.id.progress);
		img = (ImageView) findViewById(R.id.image);
		text = (TextView) findViewById(R.id.text);
		//�����¼�
		downImage.setOnClickListener(this);
	}

	/**
	 * ��������ͼƬ
	 */
	@Override
	public void onClick(View v) {
		
		DownImage task = new DownImage(); //����һ���첽
		task.execute(Constant.IMG_PAHT); //�����첽
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
			
			String root = params[0]; //��ȡ·��
			
			HttpClient client = new DefaultHttpClient(); //����HttpClient
			
			HttpGet httpGet = new HttpGet(root); //����HttpGet����ʽ
			
			try {
				HttpResponse response = client.execute(httpGet); //ִ�����get����,����һ�����ض���
				
				if(response.getStatusLine().getStatusCode() == 200){
					//����ɹ����ӣ����Ի�ȡ����
//					return BitmapFactory.decodeStream( response.getEntity().getContent());    //ֱ����bitmap�������ͼƬ
					
					//������ð�ͼƬ���뵽sd�У����½����������Ҵ�sd���ж�ȡ��ͼƬ��ճ����ImageView��
					is = response.getEntity().getContent();
					
					bar.setMax((int) response.getEntity().getContentLength()); //������������������ֵ
						
					String path = Environment.getExternalStorageDirectory().getAbsolutePath().toString().trim() + "/" + root.lastIndexOf("/"); //��ȡ�ļ����·��
					
					fos  = new FileOutputStream(path); //�����ļ��������\
					
					bos = new BufferedOutputStream(fos); //�����������������
					
					bis = new BufferedInputStream(is); //������������������
					
					int len = 0;
					byte [] buffer = new byte[1024];
					while( (len = bis.read(buffer)) != -1){
						bos.write(buffer, 0, len); //д��sd��
						this.publishProgress(len);  //���½�����
					}
					//�������йر�
					bis.close();
					bos.close();
					fos.close();
					is.close();
					Bitmap bitmap = BitmapFactory.decodeFile(path); //����·������ȡͼƬ
					return bitmap; //����ͼƬ
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * ִ�����߳�
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
			if(result != null) {
				img.setImageBitmap(result);
				text.setText("�������");
			}
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			
			if(values[0] != null) {
				super.onProgressUpdate(values);
				int value = values[0]; //���ÿ�θ��½��ȵ�����
				bar.setProgress(bar.getProgress()+value); //����Ϊ��ǰ���ȼ����¸��µĽ���
				int name =  100 * bar.getProgress() / bar.getMax() ; //���㵱ǰ�Ľ���
				text.setText("��ǰ����Ϊ��"+name+"%"); //�����ı�
			}
			
		}
	}
}
