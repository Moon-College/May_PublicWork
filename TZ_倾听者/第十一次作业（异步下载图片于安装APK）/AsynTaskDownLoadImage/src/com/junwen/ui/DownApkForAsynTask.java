package com.junwen.ui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asyntaskdownloadimage.R;
import com.junwen.adapter.AppAdapter;
import com.junwen.bean.App;
import com.junwen.constant.Constant;
import com.junwen.util.InstallApk;

public class DownApkForAsynTask extends Activity implements OnClickListener, OnItemClickListener {

	private ProgressBar bar; // ������
	private Button down; // ���ذ�ť
	private TextView text; // �����ı�
	private Button unInstall; // ж��
	private ListView lv;
	private List<App> data;
	private AppAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.downapk_layout);
		initView();
		initDate();
	}
	/**
	 * ��ȡ����Ӧ��
	 */
	private void initDate() {
		//��ȡ����Ӧ�ó�����Ϣ
		data = InstallApk.selectApplication(DownApkForAsynTask.this);
		adapter = new AppAdapter(DownApkForAsynTask.this,data);
		lv.setAdapter(adapter);
	}

	/**
	 * ��ʼ��
	 */
	private void initView() {
		bar = (ProgressBar) findViewById(R.id.apk_progress);
		down = (Button) findViewById(R.id.down);
		text = (TextView) findViewById(R.id.apk_text);
		unInstall = (Button) findViewById(R.id.uninstall);
		lv = (ListView) findViewById(R.id.listview);
		lv.setOnItemClickListener(this);
		data = new ArrayList<App>();
		down.setOnClickListener(this);
		unInstall.setOnClickListener(this);
	}

	/**
	 * ���������QQʱ
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.down:
			//����ͼƬ
			DownQQForAsynTask task = new DownQQForAsynTask(); // �����첽
			task.execute(Constant.APK_PAHT); // �����첽
			break;
		case R.id.uninstall:
			//ж��ͼƬ
			InstallApk.uninstall(Constant.APK_PACKAGE, DownApkForAsynTask.this);
			break;

		default:
			break;
		}

	}
	

	/**
	 * ִ������APK
	 * 
	 * @author admi
	 * 
	 */
	private class DownQQForAsynTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {

			InputStream is = null;

			FileOutputStream fos = null;
			BufferedInputStream bis = null;

			BufferedOutputStream bos = null;

			String root = params[0]; // apk·��
			try {
				URL url = new URL(root); // ����HttpClient
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection(); // ��������
				conn.setReadTimeout(5000);
				conn.setRequestMethod("GET");
				int code = conn.getResponseCode();
				if (code == 200) {
					// ������ӳɹ�!

					bar.setMax(conn.getContentLength()); // ���ý��������ֵ
					System.out.println(conn.getContentLength() + "");

					String path = Environment.getExternalStorageDirectory()
							.getAbsolutePath().toString().trim()
							+ "/" + root.lastIndexOf("="); // ��ȡ�ļ��洢����·��
					System.out.println("dddd");
					is = conn.getInputStream(); // ����������

					fos = new FileOutputStream(path); // �����ļ������

					bis = new BufferedInputStream(is); // ��������������

					bos = new BufferedOutputStream(fos); // �������������

					int len = 0;
					byte[] buffer = new byte[1024];
					while ((len = bis.read(buffer)) != -1) {
						bos.write(buffer, 0, len); // д���ļ�
						this.publishProgress(len); // ���½�����
					}
					// �ر���
					bos.close();
					bis.close();
					fos.close();
					is.close();
					return path;
				} else {
					Toast.makeText(DownApkForAsynTask.this, "���ӷ�����ʧ��!", 0)
							.show();
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * ��װ���
		 */
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// ��װAPP
			InstallApk.installApplication(result, DownApkForAsynTask.this);
			text.setText("������ɣ�");
		}

		/**
		 * ����������
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			int index = values[0]; // ��ȡд���ֽ���

			bar.setProgress(bar.getProgress() + index); // ���ý���

			int name = 100 * bar.getProgress() / bar.getMax(); // ������صİٷֱ�

			text.setText("��ǰ���ؽ��ȣ�" + name + "%"); // ���ý����ı�

		}
	}


	/**
	 * �������Ŀ��ʱ�����ж��
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//�Ե������Ŀ����ж��
		App app = data.get(arg2);
		InstallApk.uninstall(app.getPackageName(), DownApkForAsynTask.this);
		System.out.println(app.getPackageName());
	}
	/**
	 * ��ж���꣬�������¿ɼ�ʱ�����»�ȡ
	 */
	@Override
	protected void onRestart() {
		super.onRestart();
		if(adapter != null) {
			initDate();
		}
	}
}
