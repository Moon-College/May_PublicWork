package com.wrz.fileexplorer;

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
import android.widget.Toast;

import com.wrz.fileexplorer.adapter.FileAdapter;
import com.wrz.fileexplorer.bean.SdFile;
import com.wrz.fileexplorer.constants.MyConstants;

public class MainActivity extends Activity implements OnItemClickListener {
	
	private List<SdFile> data;
	private ListView mListView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        data = new ArrayList<SdFile>();
        mListView = (ListView) findViewById(R.id.file_listview);
        mListView.setOnItemClickListener(this);
        // 数据源
        initData(MyConstants.ROOT);
       
    }

    /***
     * 初始化数据
     * 
     * @param path 当前路径
     */
	@SuppressWarnings("unused")
	private void initData(String path) {
		data.clear();
		File file = new File(path);
		// file = null; 测试Toast
		if(file == null){
			// sd卡不存在或已损毁
			Toast.makeText(this, R.string.msg, 1000).show();
		} else{
			// 创建返回数据
			SdFile back = new SdFile();
			// 返回  路径为 上一级路径
			String back_path = path.substring(0, path.lastIndexOf("/"));
			back.setFilePath(back_path);
			back.setName("返回");// 文件名
			back.setFile(new File(path));// 文件对象
			back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));// 图片
			back.setPic(false);// 是否是图片
			data.add(back);
			// 拿到当前路径下的所有文件
			File[] file_list = file.listFiles();
			for(File f : file_list){
				SdFile sdFile = new SdFile();
				sdFile.setFile(f);// 文件对象
				sdFile.setFilePath(f.getAbsolutePath());// 文件路径
				sdFile.setName(f.getName());// 文件名字
				if(f.isDirectory()){
					// 文件夹
					sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
				}/*else if(f.isHidden()){
					Log.i("INFO","这是一个隐藏文件:"+f.getName());
					
				}*/else if(f.getName().toLowerCase().endsWith(".jpg") 
						|| f.getName().toLowerCase().endsWith(".png")
						|| f.getName().toLowerCase().endsWith(".gif")
						|| f.getName().toLowerCase().endsWith(".ico")
						|| f.getName().toLowerCase().endsWith(".bmp")){
					// 文件是图片类型
					sdFile.setBitmap(null);
				}else{
					// 普通文件
					sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
				}
				data.add(sdFile);
			}
		}
		 // 适配器
		FileAdapter adapter = new FileAdapter(this, data);
		mListView.setAdapter(adapter);
	}

	/***
	 * 点击itme进入下一级或返回上一级目录
	 */
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = data.get(position).getFilePath();// 获取被点击到的文件的路径
		File file = data.get(position).getFile();// 被点击到的条目的文件对象
		if(file.isDirectory()){
			initData(path);
		}
		
	}
}