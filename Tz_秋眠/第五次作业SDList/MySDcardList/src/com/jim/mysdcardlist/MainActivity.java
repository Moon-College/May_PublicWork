package com.jim.mysdcardlist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.jim.mysdcardlist.adapter.FileAdapter;
import com.jim.mysdcardlist.beans.SDFiler;
import com.jim.mysdcardlist.utils.Const;
import com.jim.mysdcardlist.utils.FileUtils;

public class MainActivity extends Activity {

	private RelativeLayout relativeLayout;
	private ListView listView;
	private static final int LISTVIEW_ID = 1000000;
	private List<SDFiler> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		relativeLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams layoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		relativeLayout.setLayoutParams(layoutParams);

		listView = new ListView(this);
		listView.setId(LISTVIEW_ID);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		listView.setLayoutParams(params);
		listView.setVisibility(View.VISIBLE);

		relativeLayout.addView(listView);
		setContentView(relativeLayout);
		data = new ArrayList<SDFiler>();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String path = data.get(position).getFile_url();
				File file = data.get(position).getFile();
				if (file.isDirectory()) {
					Log.i("item_path", path);
					initData(path);
				}
			}
		});
		initData(Const.SD_PATH);
	}

	@SuppressWarnings("unused")
	private void initData(String path) {
		// TODO Auto-generated method stub
		Log.i("INFO", path);
		data.clear();
		File files = new File(path);
		if (files == null) {
			Toast.makeText(this, "未检测到SD卡", Toast.LENGTH_LONG).show();

		} else {
			SDFiler sdFiler_back = new SDFiler();
			String back_path = "";
			back_path = path.substring(0, path.lastIndexOf("/"));
			Log.i("INFO_BACK", back_path);
			sdFiler_back.setBitmap(BitmapFactory.decodeResource(getResources(),
					R.drawable.dirs));
			sdFiler_back.setFile_name("..返回");
			sdFiler_back.setFile_url(back_path);
			sdFiler_back.setFile(new File(back_path));
			sdFiler_back.setPic(false);
			sdFiler_back.setFile_count("-1");
			data.add(sdFiler_back);
			File[] file_list = files.listFiles();
			for (File f : file_list) {
				SDFiler sdFiler = new SDFiler();
				sdFiler.setFile(f);
				sdFiler.setFile_url(f.getAbsolutePath());
				sdFiler.setFile_name(f.getName());
				if (f.isDirectory()) {
					sdFiler.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.dirs));
					sdFiler.setFile_count("文件:" + FileUtils.getFileCount(f));
				} else if (f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".jpg")) {
					sdFiler.setBitmap(null);
					sdFiler.setFile_count("大小" + f.length());
				} else {
					sdFiler.setBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.file));
					String sSize = "";
					float size =0;
					if(f.length()<1024){
						size= f.length();
						sSize = "大小:"+size+"KB";
					}else if(f.length()>=1024&&f.length()<1024*1024){
						size = (f.length())/1024;
						sSize = "大小:"+size+"MB";
					}else{
						size = (f.length())/1024/1024;
						sSize = "大小:"+size+"GB";
					}
					sdFiler.setFile_count(sSize);
				}
				data.add(sdFiler);
			}
			FileAdapter adapter = new FileAdapter(data, this);
			listView.setAdapter(adapter);
		}

	}
}
