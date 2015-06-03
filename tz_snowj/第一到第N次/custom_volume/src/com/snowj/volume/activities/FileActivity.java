package com.snowj.volume.activities;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.snowj.volume.BaseActivity;
import com.snowj.volume.R;
import com.snowj.volume.model.FileInfo;
import com.snowj.volume.utils.BitmapUtil;
import com.snowj.volume.utils.Global;
import com.snowj.volume.widget.ListAdapter;
import com.snowj.volume.widget.ListItem;

public class FileActivity extends BaseActivity{

	
	private ListView listView;
	
	private List<FileInfo> list = new ArrayList<FileInfo>();

	private ListAdapter<FileInfo> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		getView();
		initView();
		initData(Global.ROOT);
	}


	private void getView() {
		listView = (ListView) findViewById(R.id.listView);
	}
	


	private void initView() {
		adapter = new ListAdapter<FileInfo>(list, buileListItem());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String path = list.get(position).getFilePath();
				File file = list.get(position).getFile();
				if(file.isDirectory()){
					initData(path);
				}
				
			}
		});
	}

	
	@SuppressWarnings("unused")
	private void initData(String path) {
		list.clear();
		File file = new File(path);
		if(file==null){
			Toast.makeText(this, "has no sdCard", Toast.LENGTH_SHORT).show();
		}else{
			/**
			 * 顶部Item为返回上一层
			 */
			FileInfo fileInfo = new FileInfo();
			String parentPath = path.substring(0, path.lastIndexOf("/"));
			fileInfo.setFilePath(parentPath);
			fileInfo.setName("back");
			fileInfo.setFile(new File(parentPath));
			fileInfo.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dirs));
			fileInfo.setPic(false);
			list.add(fileInfo);
			/**
			 * 获取路径下面所有文件,文件夹
			 */
			File[] fileList = file.listFiles();
			if(fileList!=null){
				
				for (File f : fileList) {
					FileInfo info = new FileInfo();
					info.setFile(f);
					info.setFilePath(f.getAbsolutePath());
					info.setName(f.getName());
					if(f.isDirectory()){
						//是文件夹
						info.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
					}else if(f.getName().toLowerCase().endsWith(".png")||f.getName().toLowerCase().endsWith(".jpg")){
						//是图片
						info.setSoftBitmap(new SoftReference<Bitmap>(null));
						info.setPic(true);
					}else{
						//普通文件
						info.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
					}
					list.add(info);
				}
			}
			adapter.notifyDataSetChanged();
			
		}
		
		
	}

	private ListItem buileListItem() {
		return new ListItem() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
				ViewHolder vh;
				if(convertView==null){
					vh = new ViewHolder();
					convertView = LayoutInflater.from(FileActivity.this).inflate(R.layout.item_file_listview, null);
					vh.iv = (ImageView) convertView.findViewById(R.id.iv);
					vh.tv = (TextView) convertView.findViewById(R.id.tv);
					convertView.setTag(vh);
				}else{
					vh = (ViewHolder) convertView.getTag();
				}
				FileInfo info = list.get(position);
				vh.tv.setText(info.getName());
				if(info.isPic()){
					vh.iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
					BitmapTask task = new BitmapTask();
					task.execute(info.getFilePath(),String.valueOf(position));
				}else{
					vh.iv.setImageBitmap(info.getBitmap());
				}
				
				
				return convertView;
			}
		};
	}
	
	
	class ViewHolder{
		ImageView iv;
		TextView tv;
	}
	
	class BitmapTask extends AsyncTask<String, Void, SoftReference<Bitmap>>{

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			
			SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(
					new BitmapUtil().compressImage(BitmapFactory.decodeFile(path)));
			
			list.get(position).setSoftBitmap(softBitmap);
			return softBitmap;
		}
		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			// TODO Auto-generated method stub
			adapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}
