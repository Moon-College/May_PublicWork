package com.keven.customview.activity;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.keven.customview.RippleView;
import com.keven.damp.R;

public class FileBrowserActivity extends Activity {
	ListView listView;
	Button btn;
	RippleView rippleView;
	List<FileDec> fileDecs;
	MyAdapter myAdapter;
	private String SDPath = Environment.getExternalStorageDirectory()
			.getAbsolutePath().toString();

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/*
		 * LinearLayout ll=new LinearLayout(this); LayoutParams lp=new
		 * LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		 * EditText et=new EditText(this);
		 */
		setContentView(R.layout.main);
		// listView=findViewById(R.i)
		fileDecs = new ArrayList<FileDec>();
		listView = (ListView) findViewById(R.id.file_list);
		// rippleView=(RippleView) findViewById(R.id.btn_back);
		initData(SDPath);
		listView.setAdapter(myAdapter = new MyAdapter(fileDecs, this));
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				FileDec fileDec = fileDecs.get(position);
				if (fileDec.getFile().isDirectory()) {

					initData(fileDec.getFilePath());
					myAdapter.notifyDataSetChanged();
				}
			}
		});
	}

	public void initData(String path) {
		fileDecs.clear();
		File file = new File(path);
		if (file == null) {
			Toast.makeText(this, "not che，there is not SD", 1000).show();
		} else {
			FileDec fileDec = new FileDec();
			if (file.getParent() != null) {
				String parentPath = file.getParent();
				Log.i("parentPath", parentPath);
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.dirs);
				fileDec.setBitmap(bitmap);
				fileDec.setFileName("parent catalog");
				fileDec.setFilePath(parentPath);
				fileDec.setFile(new File(parentPath));
				fileDecs.add(fileDec);
			}
			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				fileDec = new FileDec();
				// 根目录
				if (f.isDirectory()) {
					fileDec.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.dirs));
					// 细心点会死呀
				} else if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")) {
					fileDec.setPic(true);
					SoftReference<Bitmap> softBitmap=new SoftReference<Bitmap>(null);
					fileDec.setSoftBitmap(softBitmap);
				} else {
					fileDec.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.file));
				}
				fileDec.setFilePath(f.getAbsolutePath());
				fileDec.setFileName(f.getName());
				fileDec.setFile(f);
				fileDecs.add(fileDec);

			}
		}
	}

	
}


	


