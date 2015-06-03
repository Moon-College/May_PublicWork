package com.xigua.filebrowser;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	ListView mList;
	List<cFile> data;
	public static String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        mList = (ListView) findViewById(R.id.file_list);
        data = new ArrayList<cFile>();
        getFileList(ROOT_PATH);
        
        mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String path = data.get(position).getFilePath();
				File file = data.get(position).getFile();
				if(file.isDirectory()){
					getFileList(path);
				}
			}
		});
	}
	
	private void getFileList(String rOOT_PATH2) {
		data.clear();
		File file = new File(rOOT_PATH2);
		if(!file.exists()){
			Toast.makeText(MainActivity.this, "Œ¥’“µΩSDø®", Toast.LENGTH_SHORT).show();
		}else{
			cFile back = new cFile();
			String back_path = rOOT_PATH2.substring(0,rOOT_PATH2.lastIndexOf("/"));
		    Log.i("zzzzzzzzzzzzzzzzzyf", back_path);
			back.setFilePath(back_path);
			back.setName("∑µªÿ");
			back.setFile(new File(back_path));
			back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			back.setPic(false);
			data.add(back);
			File[] file_list = file.listFiles();
			for(File f : file_list){
				cFile cFile = new cFile();
				cFile.setFile(f);
				cFile.setFilePath(f.getAbsolutePath());
				cFile.setName(f.getName());
				if(f.isDirectory()){
					cFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
				}else if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")){
					cFile.setSoftBitmap(new SoftReference<Bitmap>(null));
					cFile.setPic(true);
				}else{
					cFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}
				data.add(cFile);
			}
			
			FileAdapter adapter = new FileAdapter(this, data);
			mList.setAdapter(adapter);
	}

	}
}
