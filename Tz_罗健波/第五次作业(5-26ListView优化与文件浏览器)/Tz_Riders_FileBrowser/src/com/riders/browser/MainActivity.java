package com.riders.browser;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import com.riders.browser.adapter.RidersListAdapter;
import com.riders.browser.constans.RidersConstans;
import com.riders.browser.entity.SdFile;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @ClassName: MainActivity
 * @Description: TODO
 * @author: Riders
 * @date: 2015-5-27 下午2:48:25 
 * 文件浏览器实现思路：
 *  1.找到系统挂载SD卡路径(还需完善内外置SD)
 *  2.实例化视图 适配器
 *  3.对于图片启用异步线程 压缩显示
 */
public class MainActivity extends Activity implements OnItemClickListener {

	private List<SdFile> data;
	private ListView mlistView;
	private RidersListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		data = new ArrayList<SdFile>();
		mlistView = (ListView) findViewById(R.id.listview);
		mlistView.setOnItemClickListener(this);
		initDate(RidersConstans.MNTROOT);
	}

	/**
	 * @Title: initDate
	 * @Description: TODO
	 * @param key 当前目录的路径
	 * @return: void
	 */
	private void initDate(String key) {
		data.clear();
		File file = new File(key);
		if (file != null) {
//			SdFile back = new SdFile();//返回文件
//			String back_path = key.substring(0,key.lastIndexOf("/"));//当前目录的父目录
//			back.setFilePath(back_path);
//			back.setFileName("返回");//文件名
//			back.setFile(new File(back_path));//文件对象
//			back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.back));
//			back.setPic(false);//不是图片
//			data.add(back);//加入到数组里
			//返回文件按钮
			SdFile backFile = new SdFile();
			//当前目录的上一级
			String back_path = key.substring(0, key.lastIndexOf("/"));
			//返回文件路径
			backFile.setFilePath(back_path);
			//返回文件对象
			backFile.setFile(new File(back_path));
			//返回文件名
			backFile.setFileName("返回");
			backFile.setPic(false);//不是图片
			//设置返回图标
			backFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.back));
			data.add(backFile);//添加到数组
			//文件路径下面的文件集
			File[] file_list = file.listFiles();
			for(File f : file_list){
				//遍历文件集给SdFile
				SdFile sdFile = new SdFile();
				//列表对应文件对象
				sdFile.setFile(f);
				//列表对应的文件名
				sdFile.setFileName(f.getName());
				//列表对应文件的路径
				sdFile.setFilePath(f.getAbsolutePath());
				//根据文件类型判断显示的图片
				if (f.isDirectory()) {
					//是文件目录
					sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}else if (f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")) {
					//是图片
					sdFile.setSoftReference(new SoftReference<Bitmap>(null));
					sdFile.setPic(true);
				}else {
					//其它文件
					sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file_normal));
				}
				data.add(sdFile);
			}
				List<SdFile> nFiles = new ArrayList<SdFile>();
				nFiles.addAll(data);
				adapter = new RidersListAdapter(this, nFiles);
				mlistView.setAdapter(adapter);
		} else {
			Toast.makeText(this, "抱歉!您未插入SD卡!", Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * Item条目点击事件处理
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// 获取被点击Item对应的文件路径
		String key = data.get(position).getFilePath();
		// 创建被点击Item的文件对象
		File file = data.get(position).getFile();
		if (file.isDirectory() && key != null) {
				initDate(key);
		}
		if (key.startsWith("/")) {
			Toast.makeText(MainActivity.this, "最上层目录", 1000);
		}

	}

}
