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
		lv.setOnItemClickListener(this);// ������Ŀ����¼�
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		getData(path);// ��ȡ�ļ��б�����
	}

	private void getData(String path) {
		files.clear();
		File file = new File(path);
		if (file == null) {
			// ûSD��
			Toast.makeText(this, "���ˣ�û����", 0).show();

		} else {// ��SD��
			FileBean back_file = new FileBean();

			back_file.name = "����";
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
					// ��Ŀ¼s
					fileBean.bitmap = BitmapFactory.decodeResource(
							getResources(), R.drawable.dirs);
				} else if (f.getName().toLowerCase().endsWith(".jpg")
						|| f.getName().toLowerCase().endsWith(".jpeg")
						|| f.getName().toLowerCase().endsWith(".png")) {
					// ��ͼƬ
//					fileBean.bitmap = null;
					fileBean.softBitmap = new SoftReference<Bitmap>(null);
					fileBean.isPic = true;
				} else {
					// ���ļ�
					fileBean.bitmap = BitmapFactory.decodeResource(
							getResources(), R.drawable.file);
				}
				fileBean.name = f.getName();
				fileBean.file_path = f.getAbsolutePath();

				fileBean.file = f;

				files.add(fileBean);
			}

			// ���bug�����ٵ�����ؼ����ܳ��� ��ָ��
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
