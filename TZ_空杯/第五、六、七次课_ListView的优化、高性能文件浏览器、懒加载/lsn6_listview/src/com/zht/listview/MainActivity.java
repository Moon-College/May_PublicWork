/**
 * Project Name:lsn6_listview
 * File Name:MainActivity.java
 * Package Name:com.zht.listview
 * Date:2015-6-8下午5:52:11
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
*/

package com.zht.listview;

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

import com.zht.listview.adapter.FileAdapter;
import com.zht.listview.bean.SdcardFile;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-6-8 下午5:52:11 <br/>
 * @author   hongtao
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MainActivity extends Activity {
    private List<SdcardFile> mList;
	private ListView listView;
	private String sdCardPath;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString();
    	mList = new ArrayList<SdcardFile>();
    	setContentView(R.layout.main);
    	listView = (ListView) findViewById(R.id.listView);
    	initData(sdCardPath);
    	listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String path = mList.get(position).getFilePath();
				File file = mList.get(position).getFile();
				if(file.isDirectory()){
					initData(path);
				}
				
			}
		});
    }

	private void initData(String sdCardPath) {
		mList.clear();
		File file = new File(sdCardPath);
		if(file != null){
			//首先每层添加返回文件夹
			//返回文件
			SdcardFile backFile = new SdcardFile();
			backFile.setName("返回");
			String backPath = sdCardPath.substring(0, sdCardPath.lastIndexOf("/"));
			backFile.setFilePath(backPath);//  /mnt目录
			backFile.setFile(new File(backPath));
			backFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			backFile.setBitmap(false);
			mList.add(backFile);
			//遍历该层的所有文件
            File[] listFiles = file.listFiles();
            for (File f : listFiles) {
            	SdcardFile mFile = new SdcardFile();
            	mFile.setFile(f);
            	mFile.setName(f.getName());
            	mFile.setFilePath(f.getAbsolutePath()); 
            	//判断当前文件是 文件夹 、图片还是其他
				if(f.isDirectory()){
					mFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
				}else if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")){
					//mFile.setBitmap(null);
					//软引用处理:在内存压力过大时回收掉
					mFile.setSoftBitmap(new SoftReference<Bitmap>(null));
					mFile.setBitmap(true);
				}else{
					mFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}
				mList.add(mFile);
			}
        	FileAdapter adapter = new FileAdapter(mList,this);
        	listView.setAdapter(adapter);
		}
	}
}
