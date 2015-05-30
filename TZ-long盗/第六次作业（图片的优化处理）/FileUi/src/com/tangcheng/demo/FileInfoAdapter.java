package com.tangcheng.demo;

import java.lang.ref.SoftReference;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileInfoAdapter extends BaseAdapter{
	private Context context;
	private List<FileInfo> data;
	private LayoutInflater inflater;
	public FileInfoAdapter(Context context,List<FileInfo> data){
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}
	
	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, null);
			holder.file_img  = (ImageView) convertView.findViewById(R.id.file_img);
			holder.file_name = (TextView) convertView.findViewById(R.id.file_name);			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		FileInfo sdFile = data.get(position);
		if(sdFile.isPic()){
			if(sdFile.getSoftBitmap().get()==null){
				holder.file_img.setImageResource(android.R.color.white);
				MyTask task = new MyTask();
				task.execute(sdFile.getFilePath(),String.valueOf(position));
			}else{
				holder.file_img.setImageBitmap(sdFile.getSoftBitmap().get());
			}
		}else{
			holder.file_img.setImageBitmap(sdFile.getBitmap());
		}
		
		holder.file_name.setText(sdFile.getName());
		return convertView;
	}
	private class ViewHolder{
		ImageView file_img;
		TextView file_name;
	}
	
	private class MyTask extends AsyncTask<String, Void, SoftReference<Bitmap>>{
		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			String path = params[0];//图片路劲
			int position = Integer.valueOf(params[1]);//文件下标
			//压缩图片
			Bitmap bitmap = FileUtil.getIamge(path);
			SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitmap);
			data.get(position).setSoftBitmap(softBitmap);
			return softBitmap;
		}
		
		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			FileInfoAdapter.this.notifyDataSetChanged();//刷新
			super.onPostExecute(result);
		}
	}
}
