package com.zjm.filesearch;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import com.zjm.filesearch.adapter.FileAdapter;
import com.zjm.filesearch.bean.MyFile;
import com.zjm.filesearch.util.Constants;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private List<MyFile> data;
	private ListView list_file;
	private MyFile myFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		list_file = (ListView) findViewById(R.id.list_file);
		list_file.setOnItemClickListener(this);
		initData(Constants.root);
	}

	/**
	 * 获取指定目录下的所有文件列表
	 * @author Json
	 * */
	private void initData(String root) {
		List<MyFile> files = new ArrayList<MyFile>();
		File file_root = new File(root);
		myFile = new MyFile();
		if(null == myFile){
			Toast.makeText(this, "内存卡不存在", Toast.LENGTH_SHORT);
		}else{
			String file_back = root.substring(0,root.lastIndexOf("/"));
			myFile.setPath(file_back);
			myFile.setName("返回");
			myFile.setFile(new File(file_back));
			myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(),R.drawable.th_bookshelf)));
			files.add(myFile);
			for(File f : file_root.listFiles()){
				myFile = new MyFile();
				String path = f.getAbsolutePath();
				myFile.setFile(f);
				myFile.setPath(path);
				myFile.setName(f.getName());
				
				if(f.isDirectory()){
					myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(),R.drawable.th_bookshelf)));
				}else if(path.toUpperCase().endsWith("JPG")||path.toUpperCase().endsWith("PNG")||path.toUpperCase().endsWith("GIF")){
					myFile.setBitmap(new SoftReference<Bitmap>(null));//异步调用
					myFile.setPic(true);
				}else{
					myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(),R.drawable.th_bookshelf)));
				}
				files.add(myFile);
			}
			data = files;
			//创建适配器
			FileAdapter adapter = new FileAdapter(this, files);
			list_file.setAdapter(adapter);
		}
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MyFile myFile = data.get(position);
		if(myFile.getFile().isDirectory()){
			initData(myFile.getPath());
		}
	}

}
