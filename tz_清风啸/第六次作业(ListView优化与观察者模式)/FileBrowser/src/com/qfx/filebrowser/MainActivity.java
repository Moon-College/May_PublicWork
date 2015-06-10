package com.qfx.filebrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.qfx.filebrowser.adapter.FileListAdapter;
import com.qfx.filebrowser.bean.SdFile;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	private ListView mListView;
	private List<SdFile> data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		
	}
	
	private void initView() {
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.lv_filelist);
		data = new ArrayList<SdFile>();
		mListView.setOnItemClickListener(this);
		initData(SD_PATH);
	}
	
	private void initData(String path) {
		File file = new File(path);
		if (!file.exists()) {
			Toast.makeText(this, "别扯了，SD卡不存在", 1000).show();
		} else {
			data.clear();
			SdFile backFile = new SdFile();
			String backPath = path.substring(0, path.lastIndexOf("/"));
			backFile.setFilePath(backPath);
			backFile.setFileName("返回");
			backFile.setDirectory(true);
			data.add(backFile);
			File[] files = file.listFiles();
			for (File f : files) {
				SdFile sdFile = new SdFile();
				sdFile.setFileName(f.getName());
				sdFile.setFilePath(f.getAbsolutePath());
				if (f.isDirectory()) {
					//是目录
					sdFile.setDirectory(true);
				} else if(f.getName().toLowerCase().endsWith(".png") || f.getName().toLowerCase().endsWith(".jpg")) {
					//是图片
					sdFile.setDirectory(false);
					sdFile.setPicture(true);
				} else {
					//是文件
					sdFile.setDirectory(false);
					sdFile.setPicture(false);
				}
				data.add(sdFile);
				
			}
			FileListAdapter adapter = new FileListAdapter(this, data);
			mListView.setAdapter(adapter);
		}
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		SdFile sdFile = (SdFile) parent.getAdapter().getItem(position);
		if (sdFile.isDirectory()) {
			initData(sdFile.getFilePath());
		}
	}
}
