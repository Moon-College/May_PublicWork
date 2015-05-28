package com.example.wenjianliulanqi;

import java.io.File;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import com.example.wenjianliulanqi.adapter.FileAdapter;
import com.example.wenjianliulanqi.bean.SDFile;
import com.example.wenjianliulanqi.uri.Constants;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{
    private ListView list;
    private List<SDFile>data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		data=new ArrayList<SDFile>();
		list=(ListView)findViewById(R.id.file_search);
		list.setOnItemClickListener(this);
		//适配器
		//读取数据
		initData(Constants.ROOT);
	}
	/**
	 * @author chen qian
	 * @param path  当前列表的目录
	 */
	private void initData(String path) {
		data.clear();
		File file=new File(path);
		if(file==null){
			Toast.makeText(this, "别扯了，没SD卡", Toast.LENGTH_SHORT).show();
		}else{
			SDFile back=new SDFile();//返回文件
			String back_path=path.substring(0, path.lastIndexOf("/"));
			back.setFilePath(back_path);
			back.setName("返回");
			back.setFile(new File(back_path));
			back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
		    back.setPic(false);
		    data.add(back);
		    File[] file_list=file.listFiles();//拿到该目录下所有的文件对象
		    for(File f:file_list){
		    	SDFile sdFile=new SDFile();
		    	sdFile.setFile(f);
		    	sdFile.setFilePath(f.getAbsolutePath());
		    	sdFile.setName(f.getName());
		    	if(f.isDirectory()){
		    		//是一个目录
		    		sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
		    	}else if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")){
		    		sdFile.setBitmap(null);
		    	}else{
		    		//普通文件
		    		sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
		    	}
		    	data.add(sdFile);
		    }
		    FileAdapter adapter=new FileAdapter(this, data);
		    list.setAdapter(adapter);
		}
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path=data.get(position).getFilePath();//获取被点击到的文件的路径
		File file=data.get(position).getFile();//被点击到的条目的文件对象
		if(file.isDirectory()){
			initData(path);
		}
		
	}

}
