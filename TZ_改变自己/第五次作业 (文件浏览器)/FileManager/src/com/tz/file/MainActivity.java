package com.tz.file;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

import com.tz.file.R;
import com.tz.file.bean.FileItem;
import com.tz.utils.FileUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView mListView;
	
	private FileAdapter adapter;
	
	private Context mContext;
	
	private Resources resources;

	private ArrayList<FileItem> fileList = new ArrayList<FileItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//获取数据
		initView();
		initData();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		mContext = this;
		resources = mContext.getResources();
		mListView = (ListView) findViewById(R.id.listview);
		adapter = new FileAdapter(mContext,fileList);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
	}

	private void initData() {
		String rootPath = FileUtils.getSDcardRoot();
		Log.e("wdj","rootPath = " + rootPath);
		if(rootPath.equals("no sdcard")) {
			Toast.makeText(mContext, "晕倒,SD卡没了", Toast.LENGTH_LONG).show();
		}else{
			loadData(rootPath);
		}
	}

	private class FileAdapter extends BaseAdapter {

		private Context mContext;
		
		private ArrayList<FileItem> fileList = new ArrayList<FileItem>();
		
		private LayoutInflater inflate;
		
		final int LIMIT_WIDTH = 200;
		
		final int LIMIT_HEIGHT = 200;
		
		public FileAdapter(Context mContext, ArrayList<FileItem> fileList) {
			this.mContext = mContext;
			this.fileList = fileList;
			inflate = LayoutInflater.from(mContext);
		}
		
		@Override
		public int getCount() {
			
			return fileList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return fileList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			
			if(convertView == null) {
				holder = new ViewHolder();
				convertView = inflate.inflate(R.layout.file_item, parent, false);
				holder.filePic = (ImageView) convertView.findViewById(R.id.file_pic);
				holder.fileName = (TextView) convertView.findViewById(R.id.file_name);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			} 
			
			if(fileList.get(position).isDir()) {
				holder.filePic.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.file_icon_folder));
			}else{
				String fileType = fileList.get(position).getFileType();
				if(fileType.equals("mp3")) {
					holder.filePic.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.file_icon_mp3));
				}else if(fileType.equals("ppt")) {
					holder.filePic.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.file_icon_ppt));
				}else if(fileType.equals("doc")){
					holder.filePic.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.file_icon_doc));
				}else if(fileType.equals("pps")) {
					holder.filePic.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.file_icon_pps));
				}else if(fileType.equals("pdf")){
					holder.filePic.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.file_icon_pdf));
				}else if(fileType.equals("xls")) {
					holder.filePic.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.file_icon_xls));
				}else if(fileType.equals("txt")) {
					holder.filePic.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.file_icon_txt));
				}else if(fileType.equals("apk")) {
					holder.filePic.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.file_icon_apk));
				}else if(fileType.equals("png") || fileType.equals("jpg")) {
					FileItem item = fileList.get(position);
					if(item.getSoftBitmap() == null) {
						holder.filePic.setImageResource(android.R.color.white);
						ImageTask task = new ImageTask();
						task.execute(item.getFilePath(),String.valueOf(position));
					}else{
						holder.filePic.setImageBitmap(item.getSoftBitmap().get());
					}
				}else{
					holder.filePic.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.file_icon_default));
				}
			}
			
			holder.fileName.setText(fileList.get(position).getFileName());
			return convertView;
		}
		
		private class ViewHolder {
			ImageView filePic;
			TextView fileName;
		}
		
		private class ImageTask extends AsyncTask<String, Void, SoftReference<Bitmap>> {

			//后台加载图片
			@Override
			protected SoftReference<Bitmap> doInBackground(String... params) {
				String path = params[0];
				int position = Integer.valueOf(params[1]);
				SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(scaleBitmap(path));
				fileList.get(position).setSoftBitmap(softBitmap);
				return null;
			}
			
			/**
			 * 根据尺寸来缩放图片
			 */
			public Bitmap scaleBitmap(String path) {
				Bitmap bitmap = null;
				BitmapFactory.Options options = new Options();
				options.inJustDecodeBounds = true;
				bitmap = BitmapFactory.decodeFile(path, options);
				int width = options.outWidth;
				int height = options.outHeight;
				if(width > LIMIT_WIDTH && height > LIMIT_HEIGHT) {
					if(width > height) {
						options.inSampleSize = (width/LIMIT_HEIGHT + height/LIMIT_WIDTH)/2;
					}else{
						options.inSampleSize = (width/LIMIT_WIDTH + height/LIMIT_HEIGHT)/2;//计算缩放的倍数
					}
				}
				options.inJustDecodeBounds = false;
				bitmap = BitmapFactory.decodeFile(path, options); 
				return bitmap;
				
			}
			
			@Override
			protected void onPostExecute(SoftReference<Bitmap> result) {
				FileAdapter.this.notifyDataSetChanged();
				super.onPostExecute(result);
			}
			
		}
		
		
		
	}


	private void loadData(String path) {
		File file = new File(path);
		File item[] = file.listFiles();
		fileList.clear();
		FileItem firstItem= new FileItem();
		firstItem.setFilePath(path.substring(0,path.lastIndexOf("/")));//当前目录的父目录)
		firstItem.setFileName("返回");
		firstItem.setFileType("");
		fileList.add(firstItem);
		
		for(File obj : item) {
			FileItem fileItem = new FileItem();
			fileItem.setFileName(obj.getName());
			fileItem.setFilePath(obj.getAbsolutePath());
			fileItem.setFileSize(String.valueOf(obj.length()));
			fileItem.setDir(obj.isDirectory()?true:false);
			if(obj.isFile()) {
				String fileName = obj.getName();
				fileItem.setFileType(fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()));
			}else{
				fileItem.setFileType("");
			}
			
			Log.e("wdj","fileItem.toString() = " + fileItem.toString());
			fileList.add(fileItem);
		}
		
		adapter.fileList = fileList;
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.e("wdj","fileName = " + fileList.get(position).getFileName());
		String filePath = fileList.get(position).getFilePath();
		File file = new File(filePath);
		if(file.isDirectory()) {
			loadData(fileList.get(position).getFilePath());
		}else{
			//暂时不实现文件的打开操作
		}
		
	}
}
