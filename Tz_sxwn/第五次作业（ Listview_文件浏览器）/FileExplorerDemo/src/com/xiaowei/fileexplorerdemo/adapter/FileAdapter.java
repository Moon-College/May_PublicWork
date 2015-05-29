package com.xiaowei.fileexplorerdemo.adapter;

import java.util.List;

import com.xiaowei.fileexplorerdemo.R;
import com.xiaowei.fileexplorerdemo.bean.SdFile;

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

public class FileAdapter extends BaseAdapter {
	private Context context;
	private List<SdFile> data;
	private LayoutInflater inflater;
	
	public FileAdapter(Context context, List<SdFile> data) {
		super();
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		Log.i("INFO","加载第"+position+"个条目");
//		View view = inflater.inflate(R.layout.list_item, null);//解析xml加载布局视图
//		//通过view获取子控件
//		ImageView file_img = (ImageView) view.findViewById(R.id.file_img);//图片控件
//		TextView file_name = (TextView) view.findViewById(R.id.file_name);//文件名
//		SdFile file = data.get(position);
//		file_img.setImageBitmap(file.getBitmap());//绑定文件图片给图片控件
//		file_name.setText(file.getName());
//		return view;
		ViewHolder viewHolder = null;
		if (convertView == null) {
			//没有缓冲重新创建,创建了以后加入到缓存
			Log.i("INFO","convertview create");
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, null);
			//加入缓存给后面的条目用
			viewHolder.file_img = (ImageView) convertView.findViewById(R.id.file_img);//图片控件
			viewHolder.file_name = (TextView) convertView.findViewById(R.id.file_name);//文件名
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		SdFile sdFile = data.get(position);
		//将数据绑定给控件
		if (sdFile.getBitmap() == null) {
			//图片还未加载,启动一个异步任务去加载图片
			//设置默认图片
			viewHolder.file_img.setImageResource(android.R.color.white);
			//启动异步任务
			MyTask task = new MyTask();
			task.execute(sdFile.getFilePath(),String.valueOf(position),"hello");
		}else{
			viewHolder.file_img.setImageBitmap(sdFile.getBitmap());
		}
		//viewHolder.file_img.setImageBitmap(data.get(position).getBitmap());
		viewHolder.file_name.setText(data.get(position).getName());
		return convertView;
	}
	
	//麻布袋
	private class ViewHolder{
		ImageView file_img;
		TextView file_name;
	}
	private class MyTask extends AsyncTask<String,Void,Bitmap>{
		//在后台启动线程,在后台加载<子线程中运行>
		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0];//图片路径
			int position = Integer.valueOf(params[1]);//文件下标
			BitmapFactory.Options options = new Options();
			options.inSampleSize = 8;//图片缩小到1/4
			Bitmap bitmap = BitmapFactory.decodeFile(path,options);//通过路径加载的图片
			data.get(position).setBitmap(bitmap);//设置图片
			return bitmap;
		}
		//加载完毕后提供刷新UI<主线程执行>
		@Override
		protected void onPostExecute(Bitmap result) {
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}
