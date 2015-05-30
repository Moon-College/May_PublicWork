package com.tangcheng.demo;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class FileUiActivity extends Activity implements OnItemClickListener {
    private ListView mListView;
	private List<FileInfo> data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        data = new ArrayList<FileInfo>();
        mListView = (ListView) findViewById(R.id.file_search);
        mListView.setOnItemClickListener(this);
        initData(FileConstants.ROOT);
    }
    /**
     * 加载SDcard数据
     * @param path 路径
     */
	private void initData(String path) {
		data.clear();
		File file = new File(path);
		if(file==null){
			Toast.makeText(this, "没卡,请插卡", 1000).show();
		}else{
			FileInfo back = new FileInfo();//返回文件
			String back_path = path.substring(0,path.lastIndexOf("/"));
			back.setFilePath(back_path);
			back.setName("返回");
			back.setFile(new File(back_path));
			back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
			back.setPic(false);
			data.add(back);
			File[] file_list = file.listFiles();
			for(File f : file_list){
				FileInfo sdFile = new FileInfo();
				sdFile.setFile(f);
				sdFile.setFilePath(f.getAbsolutePath());
				sdFile.setName(f.getName());
				if(f.isDirectory()){//文件
					sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.folder));
				}else if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")){
					//是图片
					sdFile.setSoftBitmap(new SoftReference<Bitmap>(null));
					sdFile.setPic(true);
				}else{
					//普通文件
					sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}
				data.add(sdFile);
			}
			FileInfoAdapter adapter = new FileInfoAdapter(this, data);
			mListView.setAdapter(adapter);
		}
	}
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
			String path = data.get(position).getFilePath();
			File file = data.get(position).getFile();
			if(file.isDirectory()){
				initData(path);
			}
	}
	
}