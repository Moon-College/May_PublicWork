package com.jim.sdlist;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import com.jim.sdlist.adapter.FileAdapter;
import com.jim.sdlist.beans.SDEntitiy;
import com.jim.sdlist.beans.utils.Const;

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
	private ListView listView;
	List<SDEntitiy> data;
	private static Bitmap mFolderIcon;
	private static Bitmap mFileIcon;
	private static Bitmap mBackIcon;
	private FileAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		if (mFolderIcon == null) {
			mFolderIcon = BitmapFactory.decodeResource(getResources(),
					R.drawable.folder);
		}
		if (mFileIcon == null) {
			mFileIcon = BitmapFactory.decodeResource(getResources(),
					R.drawable.file);
		}
		if (mBackIcon == null) {
			mBackIcon = BitmapFactory.decodeResource(getResources(),
					R.drawable.upfolder);
		}
		listView = (ListView) findViewById(R.id.listview);
		data = new ArrayList<SDEntitiy>();
//		adapter = new FileAdapter(data, this);
//		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		getDataFromDir(Const.SD_PATH);
	}

	private void getDataFromDir(String path) {
		// TODO Auto-generated method stub
		if (data != null) {
			data.clear();
		}
		File sdFile = new File(path);
		if (!sdFile.exists()) {
			Toast.makeText(this, "SD卡不存在", Toast.LENGTH_LONG).show();
		} else {
			String backPath = path.substring(0, path.lastIndexOf("/"));
			File backFile = new File(backPath);
			SDEntitiy backEntitiy = new SDEntitiy();

			backEntitiy.setFile(backFile);
			backEntitiy.setFile_name("返回" + backFile.getName());
			backEntitiy.setFile_path(backFile.getAbsolutePath());
			backEntitiy.setBitmap(mBackIcon);
			// backEntitiy.setFile_count("");
			backEntitiy.setPic(false);
			data.add(backEntitiy);

			File[] files = sdFile.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					SDEntitiy entitiy = new SDEntitiy();
					entitiy.setFile_name(f.getName());
					entitiy.setFile_path(f.getAbsolutePath());
					entitiy.setFile(f);
					
					if (f.isDirectory()) {
						entitiy.setPic(false);
						entitiy.setBitmap(mFolderIcon);
						// entitiy.setFile_count("文件:" +
						// FileUtils.getFileCount(f));
					} else if (f.getAbsolutePath().toUpperCase().endsWith("JPG")
							|| f.getAbsolutePath().toUpperCase().endsWith("PNG")
							|| f.getAbsolutePath().toUpperCase().endsWith("GIF")) {
						entitiy.setPic(true);
						entitiy.setSoftBitmap(new SoftReference<Bitmap>(null));
						// entitiy.setFile_count(FileUtils.getFileSize(f));
					} else {
						entitiy.setPic(false);
						entitiy.setBitmap(mFileIcon);
						// entitiy.setFile_count(FileUtils.getFileSize(f));
					}
					data.add(entitiy);
				}
			}
			adapter = new FileAdapter(data, this);
			listView.setAdapter(adapter);
		}

	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		SDEntitiy entitiy = data.get(position);
		if (entitiy.getFile().isDirectory()) {
			getDataFromDir(entitiy.getFile_path());
		}
		Toast.makeText(MainActivity.this, entitiy.getFile_path(), 1000).show();
//		adapter.notifyDataSetChanged();
	}
}