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
 * Created on2015-5-27 上午9:03:22 MainActivity.java<br/>
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
	 * 初始化数据源 Author ZhuHequn 1363790125@qq.com <br/>
	 * 
	 */

	private void initData(String path) {
		data.clear();
		File file = new File(path);
		if (file == null) {
			Toast.makeText(this, "内存卡不存在", 1000).show();
		} else {

			SdFile back = new SdFile();
			String back_path = path.toString().substring(0, path.indexOf("/"));
			back.setFilepath(path);
			back.setName("返回");
			back.setFile(new File(back_path));
			// 设置返回的图片
			back.setBitmap(BitmapFactory.decodeResource(getResources(),
					R.drawable.dirs));
			back.setIspic(false);
			data.add(back);
			File[] file_list = file.listFiles();
			for (File f : file_list) {
				SdFile sdfile = new SdFile();
				sdfile.setFile(f);
				// 设置目录的图片
				sdfile.setFilepath(f.getAbsolutePath());
				sdfile.setName(f.getName());
				if (f.isDirectory()) {				
					sdfile.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.dirs));
				} else if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")) {
					// 是图片
					sdfile.setBitmap(null);
				} else {
					// 普通文件
					sdfile.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.file));
				}
				data.add(sdfile);

			}
			// 加载自定义适配器
			MyAdapter adapter = new MyAdapter(this, data);
			mlistview.setAdapter(adapter);
		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// 获取被点击到的文件的路径
		String path = data.get(position).getFilepath();
		// 被点击到的条目的文件对象
		File file = data.get(position).getFile();
		if (file.isDirectory()) {
			initData(path);
		}

	}

}
