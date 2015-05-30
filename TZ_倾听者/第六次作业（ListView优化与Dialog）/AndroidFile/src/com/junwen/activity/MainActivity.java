package com.junwen.activity;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.androidfile.R;
import com.junwen.adapter.MyAdapter;
import com.junwen.bean.FileItem;
import com.junwen.constant.Constant;

public class MainActivity extends Activity implements OnItemClickListener {
	//ListView
	private ListView lv;
	//�ļ�����
	private List<FileItem> data;
	//������
	private MyAdapter adapter;
	//��Ŀ¼·��
	private String Currindex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		readFileDirectory(Constant.root_path);
	}
	
	/**
	 * ����·������ȡ·���µ������ļ�
	 * @param rootPath
	 */
	private void readFileDirectory(String rootPath) {
		//���
		data.clear();
		//������Ŀ¼����
		File file = new File(rootPath);
		//���ݸ�Ŀ¼�����ȡĿ¼�µ������ļ�
		File[] listFiles = file.listFiles();
		//��ÿ���ļ������жϣ�����װ���ļ�������
		for (File f : listFiles) {
		FileItem item = new FileItem();
		String fileName = f.getName();
		item.setFile_Name(f.getName());
		item.setFile_path(f.getAbsolutePath());
		if(f.isDirectory())
		{
			//������ļ���
			item.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
			
		}else if (fileName.toUpperCase().endsWith(".JPG") || fileName.toUpperCase().endsWith(".PNG") ) {
			//��ͼƬ
			item.setSoftBitmap(new SoftReference<Bitmap>(null));
			item.setIspic(true);
		}else if (fileName.toUpperCase().endsWith(".TXT")) {
			//������ı��ļ�
			item.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.txt));
		}
		data.add(item);
		}
		
		//����������
		MyAdapter adapter = new MyAdapter(MainActivity.this, data);
		
		//��ListView����������
		lv.setAdapter(adapter);
		//��ֵ
		String parentPath = rootPath.substring(0, rootPath.lastIndexOf("/"));
		Currindex = parentPath;
	}
	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		lv =(ListView) findViewById(R.id.listview);
		data = new ArrayList<FileItem>();
		lv.setOnItemClickListener(this);
	}
	/**
	 * �������Ŀ��ʱ��
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = data.get(position).getFile_path();
		File file = new File(path);
		if(file.isDirectory())
		{
			//��������ļ���
			readFileDirectory(path);
		}
	}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	switch (keyCode) {
	case KeyEvent.KEYCODE_BACK:
		//�����·��ؼ�ʱ,�ж��Ƿ����ļ��У���������ļ��У��϶�û���ϼ���
		if(new File(Currindex).isDirectory())
		{
			//Ȼ���ٻ�ȡ�ļ�
			readFileDirectory(Currindex);
		}
		break;

	default:
		break;
	}
	return true;
}

}
