package com.itskylin.android.filemanager;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.itskylin.android.filemanager.adapter.MyAdapter;
import com.itskylin.android.filemanager.contact.MyConstacts;
import com.itskylin.android.filemanager.entity.SdFile;

/**
 * ClassName: MainActivity
 * 
 * @Description: TODO
 * @author BlueSky QQ：345066543
 * @date 2015年5月27日
 */
public class MainActivity extends Activity {

	private SharedPreferences sp;
	private ListView mListView;
	private MyAdapter adapter;
	private ArrayList<SdFile> data;
	private String safePath;
	private final static String TAG = "mainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		data = new ArrayList<SdFile>();
		mListView = (ListView) findViewById(R.id.lv_files);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		String path = sp.getString("path", MyConstacts.ROOT);
		initData(MyConstacts.ROOT);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (data.get(position).isDir()) {
					initData(data.get(position).getFile().getAbsolutePath());
				} else {
					Intent intent = new Intent(Intent.ACTION_VIEW);
					startActivity(intent);
				}
			}
		});
	}

	private void initData(String root) {
		Log.i(TAG, "initData -root:" + root);
		File file = new File(root);
		if ("/".equals(file.getParentFile().getAbsolutePath())) {
			Toast.makeText(this, "已经是根目录了，不能返回了", Toast.LENGTH_SHORT).show();
			return;
		}
		data.clear();
		Log.i(TAG, "initData -root PARENT PATH:"
				+ file.getParentFile().getAbsolutePath());
		if (file.isDirectory()) {
			safePath = file.getAbsolutePath();
		} else {
			safePath = file.getParent();
		}
		SdFile backSdFile = null;
		if (MyConstacts.ROOT.equals("") || MyConstacts.ROOT == null) {
			Toast.makeText(this, "未检测到SD卡", Toast.LENGTH_SHORT).show();
		} else {
			backSdFile = new SdFile("返回", file.getParentFile(),
					BitmapFactory.decodeResource(getResources(),
							R.drawable.ic_launcher), true, false);

			File[] files = file.listFiles();
			for (File f : files) {
				SdFile sdFile2 = null;
				String name = f.getName();
				boolean isPic = false;
				String path = f.getAbsolutePath().toLowerCase();
				Bitmap bitmap = null;
				if (f.isDirectory()) {
					bitmap = BitmapFactory.decodeResource(getResources(),
							R.drawable.ic_launcher);
				} else {
					if (path.endsWith(".txt")) {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.txt);
					} else if (path.endsWith(".pdf")) {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.pdf);
					} else if (path.endsWith(".excel")) {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.excel);
					} else if (path.endsWith(".apk")) {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.apk);
					} else if (path.endsWith(".mp4") || path.endsWith(".rmvb")
							|| path.endsWith(".mkv")) {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.mp4);
					} else if (path.endsWith(".rar")) {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.rar);
					} else if (path.endsWith(".zip")) {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.zip);
					} else if (path.endsWith(".xml")) {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.xml);
					} else if (path.endsWith(".mp3") || path.endsWith(".ape")
							|| path.endsWith("m4a")) {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.mp3);
					} else if ((path.endsWith(".png") || path.endsWith(".jpg") || path
							.endsWith(".jpeg"))) {
						isPic = true;
					} else {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.file);
					}
				}
				sdFile2 = new SdFile(name, f, bitmap, f.isDirectory(), isPic);
				if (isPic) {
					sdFile2.setSoftBitmap(new SoftReference<Bitmap>(null));
				}
				data.add(sdFile2);
			}
		}
		// 按名字排序，目录在前，文件在后
		Comparator<? super SdFile> comparator = new Comparator<SdFile>() {

			@Override
			public int compare(SdFile lhs, SdFile rhs) {
				if (lhs.isDir() && rhs.getFile().isFile()) {
					return -1;
				}
				if (rhs.isDir() && lhs.getFile().isFile()) {
					return 1;
				}
				return lhs.getName().compareTo(rhs.getName());
			}
		};
		Collections.sort(data, comparator);
		// 返回在第一个位置
		data.add(0, backSdFile);
		adapter = new MyAdapter(this, data);
		mListView.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		Editor edit = sp.edit();
		edit.putString("path", safePath);
		edit.commit();
		super.onDestroy();
	}

}
