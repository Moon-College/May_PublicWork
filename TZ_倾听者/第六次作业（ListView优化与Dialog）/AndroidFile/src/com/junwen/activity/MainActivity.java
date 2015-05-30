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
	//文件集合
	private List<FileItem> data;
	//适配器
	private MyAdapter adapter;
	//父目录路径
	private String Currindex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		readFileDirectory(Constant.root_path);
	}
	
	/**
	 * 根据路径，获取路径下的所有文件
	 * @param rootPath
	 */
	private void readFileDirectory(String rootPath) {
		//清空
		data.clear();
		//创建根目录对象
		File file = new File(rootPath);
		//根据根目录对象获取目录下的所有文件
		File[] listFiles = file.listFiles();
		//对每个文件进行判断，并且装入文件对象中
		for (File f : listFiles) {
		FileItem item = new FileItem();
		String fileName = f.getName();
		item.setFile_Name(f.getName());
		item.setFile_path(f.getAbsolutePath());
		if(f.isDirectory())
		{
			//如果是文件夹
			item.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
			
		}else if (fileName.toUpperCase().endsWith(".JPG") || fileName.toUpperCase().endsWith(".PNG") ) {
			//是图片
			item.setSoftBitmap(new SoftReference<Bitmap>(null));
			item.setIspic(true);
		}else if (fileName.toUpperCase().endsWith(".TXT")) {
			//如果是文本文件
			item.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.txt));
		}
		data.add(item);
		}
		
		//创建适配器
		MyAdapter adapter = new MyAdapter(MainActivity.this, data);
		
		//给ListView设置适配器
		lv.setAdapter(adapter);
		//赋值
		String parentPath = rootPath.substring(0, rootPath.lastIndexOf("/"));
		Currindex = parentPath;
	}
	/**
	 * 初始化控件
	 */
	private void initView() {
		lv =(ListView) findViewById(R.id.listview);
		data = new ArrayList<FileItem>();
		lv.setOnItemClickListener(this);
	}
	/**
	 * 当点击条目得时候
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = data.get(position).getFile_path();
		File file = new File(path);
		if(file.isDirectory())
		{
			//如果他是文件夹
			readFileDirectory(path);
		}
	}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	switch (keyCode) {
	case KeyEvent.KEYCODE_BACK:
		//当按下返回键时,判断是否是文件夹，如果不是文件夹，肯定没有上级了
		if(new File(Currindex).isDirectory())
		{
			//然后再获取文件
			readFileDirectory(Currindex);
		}
		break;

	default:
		break;
	}
	return true;
}

}
