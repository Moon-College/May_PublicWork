package com.myandroid.fileSearch;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.myandroid.fileSearch.adapter.FileBaseAdapter;
import com.myandroid.fileSearch.bean.SdFile;
import com.myandroid.fileSearch.constant.Constant;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnItemClickListener {
	/** Called when the activity is first created. */
	private ListView mListView;
	private SdFile sdFile = null;
	private List<SdFile> mDate = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mListView = (ListView) findViewById(R.id.lv_file);
		mDate = new ArrayList<SdFile>();
		mListView.setOnItemClickListener(this);
		// 获取sd卡列表文件
		initflieDate(Constant.PATH);
		
	}

	/**
	 * @author 蓝天
	 * @param path
	 *            sd卡文件的目录
	 */
	public void initflieDate(String path) {
		mDate.clear();
		File file = new File(path);
		// 这个文件是否为空
		if (file.exists()) {
			sdFile = new SdFile();
			// 取到当前目录的上一级目录的地址
			String file_path = path.substring(0, path.lastIndexOf("/"));
			sdFile.setName("返回");
			sdFile.setFile_path(file_path);
			sdFile.setFile(new File(file_path));
			sdFile.setBitmap(BitmapFactory.decodeResource(getResources(),
					R.drawable.dirs));
			sdFile.setPic(false);
			mDate.add(sdFile);
			// 取到目录下的所有文件
			File[] file_list = file.listFiles();
			// 遍历每个文件存放到List列表中
			for (File f : file_list) {
				sdFile = new SdFile();
				sdFile.setName(f.getName());
				sdFile.setFile_path(f.getAbsolutePath());
				sdFile.setFile(f);
				if (f.isDirectory()) {
					// 是目录
					sdFile.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.dirs));
					Log.i("info",String.valueOf(sdFile.getBitmap()));
				} else if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")
						|| f.getName().toLowerCase().endsWith(".gif")) {
					sdFile.setSoftBitmap(new SoftReference<Bitmap>(null));
					sdFile.setPic(true);
				} else {
					sdFile.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.file));
				}
				mDate.add(sdFile);
			}
			FileBaseAdapter mAdapter = new FileBaseAdapter(this, mDate);
			mListView.setAdapter(mAdapter);

		} else {
			Toast.makeText(this, "您的手机没有sd卡", Toast.LENGTH_LONG).show();
		}

	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path=mDate.get(position).getFile_path();
		File f=mDate.get(position).getFile();
		if (f.isDirectory()) {
			initflieDate(path);
		}

	}
}