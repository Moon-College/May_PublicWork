package com.lzq.file_brower_listview;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView lv;
	private List<FileBean> files = new ArrayList<FileBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv);
		lv.setOnItemClickListener(this);// 设置条目点击事件
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		getData(path);// 获取文件列表数据
	}

	private void getData(String path) {
		files.clear();
		File file = new File(path);
		if (file == null) {
			// 没SD卡
			Toast.makeText(this, "别扯了，没卡！", 0).show();

		} else {// 有SD卡
			FileBean back_file = new FileBean();

			back_file.name = "返回";
			String back_file_path = path.substring(0, path.lastIndexOf("/"));
			back_file.file_path = back_file_path;
			back_file.bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.dirs);
			back_file.isPic = false;
			back_file.file = new File(back_file_path);
			files.add(back_file);

			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				FileBean fileBean = new FileBean();
				if (f.isDirectory()) {
					// 是目录s
					fileBean.bitmap = BitmapFactory.decodeResource(
							getResources(), R.drawable.dirs);
				} else if (f.getName().toLowerCase().endsWith(".jpg")
						|| f.getName().toLowerCase().endsWith(".jpeg")
						|| f.getName().toLowerCase().endsWith(".png")) {
					// 是图片
//					fileBean.bitmap = null;
					fileBean.softBitmap = new SoftReference<Bitmap>(null);
					fileBean.isPic = true;
				} else {
					// 是文件
					fileBean.bitmap = BitmapFactory.decodeResource(
							getResources(), R.drawable.file);
				}
				fileBean.name = f.getName();
				fileBean.file_path = f.getAbsolutePath();

				fileBean.file = f;

				files.add(fileBean);
			}

			// 解决bug，快速点击返回键可能出现 空指针
			List<FileBean> files2 = new ArrayList<FileBean>();

			files2.addAll(files);
			FileAdapter adapter = new FileAdapter(this, files2);
			lv.setAdapter(adapter);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		FileBean fileBean = files.get(position);
		if (fileBean.file.isDirectory()) {
			getData(fileBean.file_path);
		}
	}
}
