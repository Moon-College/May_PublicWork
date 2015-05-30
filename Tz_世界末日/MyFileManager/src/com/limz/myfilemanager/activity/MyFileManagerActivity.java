package com.limz.myfilemanager.activity;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

import com.limz.myfilemanager.adapter.MyAdapter;
import com.limz.myfilemanager.bean.MyFile;
import com.limz.myfilemanager.constant.MyConstant;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MyFileManagerActivity extends Activity implements OnItemClickListener {
    /** Called when the activity is first created. */
	
	private ListView listView;
    private MyAdapter adapter;
    private ArrayList<MyFile> list;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.mylistview);
        init();
        traverseFile(MyConstant.path);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

	/**
	 * ����ָ��·�����ļ���Ϣ
	 * @author limingzhu
	 * @param path ��ǰ��Ҫ�������ļ�·��
	 */
	private void traverseFile(String path) {
		list.clear();
		if(path == null || path.equals("")) {
			Toast.makeText(this, "·��Ϊ��Ŷ���ף�~", Toast.LENGTH_LONG).show();
			return;
		}
		//��Ӷ����ķ���Item
		MyFile back = new MyFile();
		int lastIndex = path.lastIndexOf("/");
		if(lastIndex == -1 || lastIndex == 0) {
			Toast.makeText(this, "�Ѿ������ٷ����ˣ��ף�~", Toast.LENGTH_LONG).show();
		} else {
			String back_path = path.substring(0, lastIndex);
			back.setName("back");
			back.setPath(back_path);
			back.setFile(new File(back_path));
			back.setPic(false);
			back.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.back));
			list.add(back);
		}
		
		//��ȡ��ǰ·���µ������ļ����ļ���
		File file = new File(path);
		Log.d("mingzhu", "path : " + path);
		File[] fileArray = file.listFiles();//��ȡpath·���µ������ļ�
		for(File f : fileArray) {
			MyFile myFile = new MyFile();
			myFile.setFile(f);//�����ļ�
			myFile.setPath(f.getAbsolutePath());//�����ļ���·��
			myFile.setPic(false);
			myFile.setName(f.getName());//�����ļ�����
			//�����ļ�ͼƬ - ��ʼ
			if(f.isDirectory()) {
				myFile.setIcon(BitmapFactory.decodeResource(
						getResources(), R.drawable.file));
			} else if(f.getName().toLowerCase().endsWith(".jpg")
					|| f.getName().toLowerCase().endsWith(".png")
					|| f.getName().toLowerCase().endsWith(".ico")) {
				myFile.setPic(true);
//				myFile.setIcon(null);
				myFile.setSoftBitmap(new SoftReference(null));
				
			} else {
				myFile.setIcon(BitmapFactory.decodeResource(
						getResources(), R.drawable.ic_launcher));
			}
			//�����ļ�ͼƬ - ����
			list.add(myFile);
		}
		adapter.list = list;
		adapter.notifyDataSetChanged();
	}

	/**
	 * ��ʼ������
	 */
	private void init() {
		list = new ArrayList<MyFile>();
		adapter = new MyAdapter(this, list);
	}

	public void onItemClick(AdapterView<?> views, View view, int pos, long id) {
		MyFile mFile = list.get(pos);
		Log.d("mingzhu", "I Click : " + mFile.getName());
		if(mFile.getFile().isDirectory()) {
			String newPath = mFile.getPath();
			Log.d("mingzhu", "path = " + newPath);
			traverseFile(newPath);
		}
	}
}