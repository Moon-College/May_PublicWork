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
		
		//初始化控件和事件监听
		initView();
		//数据
		initData(MyContants.ROOT);
	}
	
	/**
	 * 初始化控件和事件监听
	 */
	private void initView() {
		data = new ArrayList<MyFile>();  //初始化数据
		lv = (ListView) findViewById(R.id.lv);
		lv.setOnItemClickListener(this);
	}

	/**
	 * 获取指定目录下的所有文件列表
	 * @param path 当前列表的目录
	 */
	@SuppressWarnings("unused")
	private void initData(String path) {
		// 先清除数据，在刷新ListView列表
		data.clear();  
		// 获取sd卡根目录下所有文件列表对象
		File file = new File(path);
		if(file == null){
			Toast.makeText(this, "没有sd卡！", Toast.LENGTH_SHORT).show();
		}else{
			MyFile file_back = new MyFile();  // 返回文件，最上面的条目
			// 当前目录的父目录	 /mnt/sdcard  /mnt
			String back_path = path.substring(0, path.lastIndexOf("/"));  //返回文件路径
			file_back.setFilePath(back_path);   //设置文件返回路径
			file_back.setFileName("返回");  //设置文件名
			file_back.setFile(new File(back_path));  //设置文件对象
			file_back.setPic(true);  //设置是图片，一定要设置此属性
//			file_back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));   //设置返回文件图片
			file_back.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.back)));   
			
			data.add(file_back);  //将返回文件添加到集合中
			
			//当前目录下的所有文件对象   file.listFiles()
			for (File f : file.listFiles()) {
				// f指当前目录下的所有文件
				String filePath = f.getAbsolutePath();  //文件绝对路径
				MyFile myFile = new MyFile();
				myFile.setFilePath(filePath);
				myFile.setFileName(f.getName());
				myFile.setFile(f);
				if(f.isDirectory()){
					//是文件目录
//					myFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
					myFile.setDir(true);  //是目录
					myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.dirs)));
				}else if(f.getName().toLowerCase().endsWith(".jpg")||f.getName().toLowerCase().endsWith(".png")){
					//是图片
					// 同步加载，将所有图片一次全部加载显示出来
//					myFile.setBitmap(BitmapFactory.decodeFile(filePath));
					// 异步加载,设置图片为null
					myFile.setBitmap(null);
					//软引用
					myFile.setPic(true);  //是图片
					myFile.setBitmap(new SoftReference<Bitmap>(null));
				}else{
					//是普通文件
//					myFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
					myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.file)));
				}
				data.add(myFile); //将文件添加到集合中
			}
			// 创建适配器
			adapter = new FileAdapter(this, data);
			// 设置适配器
			lv.setAdapter(adapter);
		}
	}

	/**
	 * 条目点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// 获取被点击文件的路径
		String path = data.get(position).getFilePath();
		// 获取被点击的文件对象
//		File file = new File(path);
		File file = data.get(position).getFile();
		if(file.isDirectory()){
			initData(path);
		}
	}
}
