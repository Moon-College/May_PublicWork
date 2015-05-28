package com.xiaowei.fileexplorerdemo;
/**
 * 文件浏览器
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.xiaowei.fileexplorerdemo.adapter.FileAdapter;
import com.xiaowei.fileexplorerdemo.bean.SdFile;
import com.xiaowei.fileexplorerdemo.constants.MyConstants;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{
	private ListView mListView;
	private List<SdFile> data;
	/**Called when the activity is called create **/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		data = new ArrayList<SdFile>();
		mListView = (ListView) findViewById(R.id.file_search);
		//适配器
		//获取数据
		initData(MyConstants.ROOT);
		mListView.setOnItemClickListener(this);
	}
	/**
	 * @author Wp 未婚
	 * @param path 当前列表的目录
	 */
	private void initData(String path) {
		data.clear();//清除掉所有数据
		File file = new File(path);
		if (file == null) {
			Toast.makeText(this,"别扯了,没卡",Toast.LENGTH_LONG).show();
		}else{
			SdFile back = new SdFile();//返回文件
			String back_path = path.substring(0, path.lastIndexOf("/"));//当前目录的父目录
			back.setFilePath(back_path);
			back.setName("返回");//文件名
			back.setFile(new File(back_path));//文件对象
			back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dir));
			back.setPic(false);//不是图片
			data.add(back);//加入到数组里
			File[] file_list = file.listFiles();//拿到该目录下所有的文件对象
			for (File f : file_list) {
				SdFile sdFile = new SdFile();
				sdFile.setFile(f);//文件对象
				sdFile.setFilePath(f.getAbsolutePath());//文件路径 
				sdFile.setName(f.getName());//文件名
				if (f.isDirectory()) {
					//是一个目录
					sdFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dir));
				}else if(f.getName().toLowerCase().endsWith(".png") || f.getName().toLowerCase().endsWith("jpg")){
					//sdFile.setBitmap(BitmapFactory.decodeFile(f.getAbsolutePath()));
					sdFile.setBitmap(null);
				}else{
					sdFile.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
				}
				data.add(sdFile);
			}
			//创建适配器,显示listview
			FileAdapter adapter = new FileAdapter(this, data);//适配器创建出来了
			mListView.setAdapter(adapter);//设置适配器
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = data.get(position).getFilePath();//获取被点击到的文件的路径
		File file = data.get(position).getFile();//被点击到的条目的文件对象
		if (file.isDirectory()) {
			initData(path);
		}
	}


}
