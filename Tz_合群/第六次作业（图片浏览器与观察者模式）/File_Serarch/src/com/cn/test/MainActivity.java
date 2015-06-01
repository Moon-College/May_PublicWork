package com.cn.test;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.cn.test.adapter.FileAdapter;
import com.cn.test.bean.SdFile;
import com.cn.test.constans.Constants;

/**
 * Created on2015-5-29 上午11:25:02 MainActivity.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class MainActivity extends Activity {
	private ListView listView;
	private List<SdFile> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView = (ListView) findViewById(R.id.file_search);
		data = new ArrayList<SdFile>();
		initData(Constants.Path);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String path = data.get(position).getFilePath();
				File file = data.get(position).getFile();
				if (file.isDirectory()) {
					initData(path);
				}
			}
		});
	}

	private void initData(String path) {
		data.clear();
		File file = new File(path);
		if (!(file.exists())) {
			Toast.makeText(this, "没有卡", Toast.LENGTH_SHORT).show();
		} else {
			SdFile back = new SdFile();
			back.setName("返回");
			back.setFilePath(path.substring(0, path.lastIndexOf("/")));
			back.setPic(false);
			back.setFile(new File(path.substring(0, path.lastIndexOf("/"))));
			back.setBitmap(BitmapFactory.decodeResource(getResources(),
					R.drawable.dirs));
			data.add(back);
			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				SdFile sdFile = new SdFile();
				sdFile.setFile(f);
				sdFile.setFilePath(f.getAbsolutePath());
				sdFile.setName(f.getName());
				if (f.isDirectory()) {
					sdFile.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.dirs));
				} else if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")) {
					sdFile.setSoftBitmap(new SoftReference<Bitmap>(null));
					sdFile.setPic(true);
				} else {
					sdFile.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.file));
				}
				data.add(sdFile);
			}
			FileAdapter adapter = new FileAdapter(this, data);
			listView.setAdapter(adapter);
		}
	}

}
