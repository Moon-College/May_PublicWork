package com.dingding.fivetwentyseven;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dingding.fivetwentyseven.adapter.ListAdapter;
import com.dingding.fivetwentyseven.bean.SdFile;
import com.dingding.fivetwentyseven.constants.Constants;
import com.example.fivetwentyseven.R;

public class MainActivity extends Activity implements OnItemClickListener {
	private ListView mListView;
	private List<SdFile> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		data = new ArrayList<SdFile>();
        mListView = (ListView) findViewById(R.id.file_search);
        mListView.setOnItemClickListener(this);
        initData(Constants.ROOT);
	}
	private void initData(String path) {
		data.clear();
		File file = new File(path);
		if (file.exists()) {
			Toast.makeText(MainActivity.this, "你好，你没有sd卡", 1000).show();
		}else {
			SdFile back = new SdFile();
			back.setFilePath(path.substring(0, path.lastIndexOf("/")));
			back.setName("返回");
			back.setFile(new File(path.substring(0, path.lastIndexOf("/"))));
			back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			back.setPic(false);
			data.add(back);
			File[] file_list = file.listFiles();
			for (File f : file_list) {
				SdFile sdFile = new SdFile();
				sdFile.setFile(f);
				sdFile.setFilePath(f.getAbsolutePath());
				sdFile.setName(f.getName());
				if(f.isDirectory()){
					sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
				}else if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")){
					sdFile.setBitmap(null);
				}else{
					sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}
				data.add(sdFile);
			}
			ListAdapter adapter = new ListAdapter(this, data);
			mListView.setAdapter(adapter);
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = data.get(position).getFilePath();
		File file = data.get(position).getFile();
		if(file.isDirectory()){
			initData(path);
		}
	}

}
