package com.file.activity;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import com.example.filemanager.R;
import com.file.adapter.FileAdapter;
import com.file.bean.FileBean;
import com.first.work.sdcard.utils.SDcardUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FileActivty extends Activity implements OnItemClickListener{
	 private ListView mFileList;
	 private List<FileBean> mFileData=new ArrayList<FileBean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mFileList=(ListView) findViewById(R.id.filelist);
		mFileList.setOnItemClickListener(this);
		initData(SDcardUtils.ROOT);
	}
	private void initData(String root) {
		mFileData.clear();
		File file=new File(root);
		if(!file.exists())
		{
			return;
		}
		FileBean first = new FileBean();
		String back_path = root.substring(0,root.lastIndexOf("/"));
		first.setFilePath(back_path);
		first.setName("返回上一节");
		first.setFile(new File(back_path));
		first.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
		first.setPic(false);
		mFileData.add(first); 
		
		File[] file_list = file.listFiles();
		for(File f : file_list){
			FileBean mFileBean = new FileBean();
			mFileBean.setFile(f); 
			mFileBean.setFilePath(f.getAbsolutePath());
			mFileBean.setName(f.getName());
			if(f.isDirectory()){
				mFileBean.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			}else if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")){
				mFileBean.setSoftBitmap(new SoftReference<Bitmap>(null));
				mFileBean.setPic(true);
			}else{
				mFileBean.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
			}
			mFileData.add(mFileBean);
		}
		FileAdapter adapter = new FileAdapter(this,mFileData);
		mFileList.setAdapter(adapter);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = mFileData.get(position).getFilePath();
		File file = mFileData.get(position).getFile();
		if(file.isDirectory()){
			initData(path);
		}
	}

}
