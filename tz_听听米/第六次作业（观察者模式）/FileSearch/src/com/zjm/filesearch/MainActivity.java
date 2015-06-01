package com.zjm.filesearch;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import com.zjm.filesearch.adapter.FileAdapter;
import com.zjm.filesearch.bean.MyFile;
import com.zjm.filesearch.util.Constants;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	public final static String TAG = MainActivity.class.getSimpleName().toString();

	private List<MyFile> data;
	private ListView list_file;
	private MyFile myFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		list_file = (ListView) findViewById(R.id.list_file);
		list_file.setOnItemClickListener(this);
		data = new ArrayList<MyFile>();
		initData(Constants.root);
	}
	

	/**
	 * 获取指定目录下的所有文件列表
	 * @author Json
	 * */
	private void initData(String root) {
		if(data.size() > 0){
			data.clear();
		}
		File file_root = new File(root);
		
		if(!file_root.exists()){
			Toast.makeText(this, "内存卡不存在", Toast.LENGTH_SHORT);
		}else{
			myFile = new MyFile();
			String file_back = root.substring(0,root.lastIndexOf("/"));
			myFile.setPath(file_back);
			myFile.setName("返回");
			myFile.setFile(new File(file_back));
			myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(),R.drawable.th_bookshelf)));
			data.add(myFile);
			System.out.println(file_root.length());
			if(null == file_root.listFiles()&&file_root.listFiles().length>0){
				Toast.makeText(getApplicationContext(), "目录为空", Toast.LENGTH_SHORT).show();
				return;
			}
			for(File f : file_root.listFiles()){
				myFile = new MyFile();
				String path = f.getAbsolutePath();
				myFile.setFile(f);
				myFile.setPath(path);
				myFile.setName(f.getName());
				
				if(f.isDirectory()){
					myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(),R.drawable.th_bookshelf)));
				}else if(path.toUpperCase().endsWith("JPG")||path.toUpperCase().endsWith("PNG")||path.toUpperCase().endsWith("GIF")){
					myFile.setBitmap(new SoftReference<Bitmap>(null));//异步调用
					myFile.setPic(true);
				}else{
					myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(),R.drawable.th_bookshelf)));
				}
				data.add(myFile);
			}
			//创建适配器
			FileAdapter adapter = new FileAdapter(this, data);
			list_file.setAdapter(adapter);
		}
		
	}
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MyFile myFile = data.get(position);
		//如果点击的图标是目录，则更新activity视图，显示下一级文件列表的路线
		if(myFile.getFile().isDirectory()){
			initData(myFile.getPath());
		}else{
			openFile(myFile);
		}
	}


	/**
	 * 
	 * @author 听听米
	 * @data 2015-5-31下午4:42:50
	 * @belong com.zjm.filesearch.MainActivity
	 * @describe：就打开文件
	 * @param myFile2
	 * void
	 */
	private void openFile(MyFile myFile) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		File file = myFile.getFile();
		Log.d(TAG, "file.getName():" + file.getName() + ",MIMEType="
				+ Constants.getFileMIMEType(file.getName()));
		intent.setDataAndType(Uri.fromFile(file),
				Constants.getFileMIMEType(file.getName()));
		startActivity(intent);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			MyFile myFile = data.get(0);
			Log.d(TAG, myFile.getPath());
			if(!myFile.getPath().equals("")){
				initData(myFile.getPath());
				return false;
			}else{
				/**
				 * 如果是/mnt目录,点击两次退出
				 */
				exit();
				return false;
			}
		}
		
		return super.onKeyDown(keyCode, event);
	}

	long currentTime ;
	
	/**
	 * 
	 * @author 听听米
	 * @data 2015-5-31下午4:53:53
	 * @belong com.zjm.filesearch.MainActivity
	 * @describe：退出
	 * void
	 */
	private void exit() {
		
		if(System.currentTimeMillis() - currentTime >2000){
			currentTime = System.currentTimeMillis();
			Toast.makeText(this, "再按一次就退出！！！", Toast.LENGTH_SHORT).show();
		}else{
			finish();
	    	System.exit(0);
		}
	}

}
