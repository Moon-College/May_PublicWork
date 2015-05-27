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
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.myfilemanager.R;
import com.junwen.adapter.CustomAdapter;
import com.junwen.bean.FileItem;
import com.junwen.constant.Constant;
import com.junwen.util.JavaUtil;

public class MainActivity extends Activity implements OnItemClickListener {
	//ListView
	private ListView lv;
	//文件集合
	private List<FileItem> data;
	//Adapter适配器
	private CustomAdapter adapter; 
	//当前目录的父目录路径
	private String CurrParentPaht;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化
		initView();
		//根据路径获取所有文件
		readDirectory(Constant.ROOT_PATH);
	}
	
	/**
	 * 根据文件路径获取目录下的所有文件
	 * @param string
	 */
	private void readDirectory(String root) {
		List<FileItem> items = new ArrayList<FileItem>();
		//根据根目录，获取目录下的所有文件
		File root_path = new File(root);
		File[] listFiles = root_path.listFiles();
		
		//对所有文件进行遍历，获取出每一个文件对象
		for (File file : listFiles) {
			//获取到了每个文件,并且创建FileItem对象      /mnt/sdcard/text.txt
			FileItem item = new FileItem();
			//获取文件的绝对路径
			String item_path = file.getAbsolutePath().toString();
			//设置文件的文件名
			item.setFileName(item_path.substring(item_path.lastIndexOf("/")+1));
			//设置文件的路径
			item.setFilePath(item_path);
			//判断文件的类型
			if(file.isDirectory())
			{
				//如果是文件夹
				item.setFileImg(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
				item.setDirectory(true);
				item.setIsimg(false);
			}
			else if (item_path.toUpperCase().endsWith(".TXT")) {
				System.out.println("文本文件");
				//如果是文本文件
				item.setFileImg(BitmapFactory.decodeResource(getResources(), R.drawable.txt));
				item.setTxt(true);
				item.setFileSize("Txt  "+JavaUtil.getFileSize(file));
				item.setIsimg(false);
			}
			else if (item_path.toUpperCase().endsWith(".PNG") || item_path.toUpperCase().endsWith(".JPG")) {
				//如果图片
				item.setFileImg(null);
				item.setIsimg(true);
			}
			else if (item_path.toUpperCase().endsWith(".PDF")) {
				//如果是PDF文件
				item.setFileImg(BitmapFactory.decodeResource(getResources(), R.drawable.iconfont_pdf));
				item.setPdf(true);
				item.setIsimg(false);
			}
			items.add(item);
		}
		data = items;
		//创建Adapter，并传入对象集合
		CustomAdapter adapter= new CustomAdapter(data, MainActivity.this);
		//给ListView传入Adataper
		lv.setAdapter(adapter);
	}
	/**
	 * 初始化控件
	 */
	private void initView() {
		
		lv = (ListView) findViewById(R.id.listview);
		adapter = new CustomAdapter(data, this);
		data = new ArrayList<FileItem>();
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//当点击条目是，如果是文件夹，就进入其中
		String filePath = data.get(position).getFilePath();
		CurrParentPaht = filePath.substring(0,filePath.lastIndexOf("/"));
		File file = new File(filePath);
		if(file.isDirectory())
		{
			readDirectory(filePath);
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			//如果按了返回键
			//再根据路径进行获取目录
			readDirectory(CurrParentPaht);
			//重新设置当前目录的父目录
			CurrParentPaht  = CurrParentPaht.substring(0, CurrParentPaht.lastIndexOf("/"));
			break;
		default:
			break;
		}
		return true;
	}
}
