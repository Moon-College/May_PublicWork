package com.cn.test;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.cn.test.adapter.MyAdapter;
import com.cn.test.been.SdFile;
import com.cn.test.constants.Myconstants;

/**
 * Created on2015-5-27 ����9:03:22 MainActivity.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class MainActivity extends Activity implements OnItemClickListener {
	private ListView mlistview;
	private List<SdFile> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mlistview = (ListView) findViewById(R.id.listView);
		mlistview.setOnItemClickListener(this);
		initData(Myconstants.ROOT);
	}

	/**
	 * ��ʼ������Դ Author ZhuHequn 1363790125@qq.com <br/>
	 * 
	 */

	private void initData(String path) {
		data.clear();
		File file = new File(path);
		if (file == null) {
			Toast.makeText(this, "�ڴ濨������", 1000).show();
		} else {

			SdFile back = new SdFile();
			String back_path = path.toString().substring(0, path.indexOf("/"));
			back.setFilepath(path);
			back.setName("����");
			back.setFile(new File(back_path));
			// ���÷��ص�ͼƬ
			back.setBitmap(BitmapFactory.decodeResource(getResources(),
					R.drawable.dirs));
			back.setIspic(false);
			data.add(back);
			File[] file_list = file.listFiles();
			for (File f : file_list) {
				SdFile sdfile = new SdFile();
				sdfile.setFile(f);
				// ����Ŀ¼��ͼƬ
				sdfile.setFilepath(f.getAbsolutePath());
				sdfile.setName(f.getName());
				if (f.isDirectory()) {				
					sdfile.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.dirs));
				} else if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")) {
					// ��ͼƬ
					sdfile.setBitmap(null);
				} else {
					// ��ͨ�ļ�
					sdfile.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.file));
				}
				data.add(sdfile);

			}
			// �����Զ���������
			MyAdapter adapter = new MyAdapter(this, data);
			mlistview.setAdapter(adapter);
		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// ��ȡ����������ļ���·��
		String path = data.get(position).getFilepath();
		// �����������Ŀ���ļ�����
		File file = data.get(position).getFile();
		if (file.isDirectory()) {
			initData(path);
		}

	}

}
