package com.tz.filebrowser;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import com.tz.filebrowser.adapter.FileAdapter;
import com.tz.filebrowser.bean.MyFile;
import com.tz.filebrowser.constants.MyContants;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	
	private ListView lv;
	private List<MyFile> data;
	private FileAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ���ؼ����¼�����
		initView();
		//����
		initData(MyContants.ROOT);
	}
	
	/**
	 * ��ʼ���ؼ����¼�����
	 */
	private void initView() {
		data = new ArrayList<MyFile>();  //��ʼ������
		lv = (ListView) findViewById(R.id.lv);
		lv.setOnItemClickListener(this);
	}

	/**
	 * ��ȡָ��Ŀ¼�µ������ļ��б�
	 * @param path ��ǰ�б��Ŀ¼
	 */
	@SuppressWarnings("unused")
	private void initData(String path) {
		// ��������ݣ���ˢ��ListView�б�
		data.clear();  
		// ��ȡsd����Ŀ¼�������ļ��б����
		File file = new File(path);
		if(file == null){
			Toast.makeText(this, "û��sd����", Toast.LENGTH_SHORT).show();
		}else{
			MyFile file_back = new MyFile();  // �����ļ������������Ŀ
			// ��ǰĿ¼�ĸ�Ŀ¼	 /mnt/sdcard  /mnt
			String back_path = path.substring(0, path.lastIndexOf("/"));  //�����ļ�·��
			file_back.setFilePath(back_path);   //�����ļ�����·��
			file_back.setFileName("����");  //�����ļ���
			file_back.setFile(new File(back_path));  //�����ļ�����
			file_back.setPic(true);  //������ͼƬ��һ��Ҫ���ô�����
//			file_back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));   //���÷����ļ�ͼƬ
			file_back.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.back)));   
			
			data.add(file_back);  //�������ļ���ӵ�������
			
			//��ǰĿ¼�µ������ļ�����   file.listFiles()
			for (File f : file.listFiles()) {
				// fָ��ǰĿ¼�µ������ļ�
				String filePath = f.getAbsolutePath();  //�ļ�����·��
				MyFile myFile = new MyFile();
				myFile.setFilePath(filePath);
				myFile.setFileName(f.getName());
				myFile.setFile(f);
				if(f.isDirectory()){
					//���ļ�Ŀ¼
//					myFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
					myFile.setDir(true);  //��Ŀ¼
					myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.dirs)));
				}else if(f.getName().toLowerCase().endsWith(".jpg")||f.getName().toLowerCase().endsWith(".png")){
					//��ͼƬ
					// ͬ�����أ�������ͼƬһ��ȫ��������ʾ����
//					myFile.setBitmap(BitmapFactory.decodeFile(filePath));
					// �첽����,����ͼƬΪnull
					myFile.setBitmap(null);
					//������
					myFile.setPic(true);  //��ͼƬ
					myFile.setBitmap(new SoftReference<Bitmap>(null));
				}else{
					//����ͨ�ļ�
//					myFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
					myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.file)));
				}
				data.add(myFile); //���ļ���ӵ�������
			}
			// ����������
			adapter = new FileAdapter(this, data);
			// ����������
			lv.setAdapter(adapter);
		}
	}

	/**
	 * ��Ŀ����¼�
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// ��ȡ������ļ���·��
		String path = data.get(position).getFilePath();
		// ��ȡ��������ļ�����
//		File file = new File(path);
		File file = data.get(position).getFile();
		if(file.isDirectory()){
			initData(path);
		}
	}
}
