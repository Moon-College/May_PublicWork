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
	 * 遍历指定路径的文件信息
	 * @author limingzhu
	 * @param path 当前需要遍历的文件路径
	 */
	private void traverseFile(String path) {
		list.clear();
		if(path == null || path.equals("")) {
			Toast.makeText(this, "路径为空哦，亲！~", Toast.LENGTH_LONG).show();
			return;
		}
		//添加顶部的返回Item
		MyFile back = new MyFile();
		int lastIndex = path.lastIndexOf("/");
		if(lastIndex == -1 || lastIndex == 0) {
			Toast.makeText(this, "已经不能再返回了，亲！~", Toast.LENGTH_LONG).show();
		} else {
			String back_path = path.substring(0, lastIndex);
			back.setName("back");
			back.setPath(back_path);
			back.setFile(new File(back_path));
			back.setPic(false);
			back.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.back));
			list.add(back);
		}
		
		//获取当前路径下的所有文件和文件夹
		File file = new File(path);
		Log.d("mingzhu", "path : " + path);
		File[] fileArray = file.listFiles();//获取path路径下的所有文件
		for(File f : fileArray) {
			MyFile myFile = new MyFile();
			myFile.setFile(f);//设置文件
			myFile.setPath(f.getAbsolutePath());//设置文件的路径
			myFile.setPic(false);
			myFile.setName(f.getName());//设置文件名称
			//设置文件图片 - 开始
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
			//设置文件图片 - 结束
			list.add(myFile);
		}
		adapter.list = list;
		adapter.notifyDataSetChanged();
	}

	/**
	 * 初始化数据
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