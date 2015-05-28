package com.tz.filebrowser.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import com.tz.filebrowser.R;
import com.tz.filebrowser.bean.MyFile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 文件适配器
 */
public class FileAdapter extends BaseAdapter {

	private List<MyFile> data;
	private Context context;
	private LayoutInflater inflater;

	public FileAdapter(Context context, List<MyFile> data) {
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}

	// 用多少个条目
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

	// 每个条目具体的内容
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			// 说明没有缓存，解析xml创建视图
			convertView = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.file_img = (ImageView) convertView.findViewById(R.id.file_img);
			holder.file_name = (TextView) convertView.findViewById(R.id.file_name);
			convertView.setTag(holder); // 将麻布袋holder放到convertView中一起缓存
		} else {
			holder = (ViewHolder) convertView.getTag(); // 说明有缓存，从convertView取出数据放到holder直接用
		}
		MyFile myFile = data.get(position); // 获取对应下标的文件对象

		//判断是否是图片
		if(myFile.isPic()){
			// 将数据绑定给控件
			// file.getBitmap()是软引用         	file.getBitmap().get()是否有图片【真实图片】 --> 强引用
			if (myFile.getBitmap().get() == null) {
				// 说明是图片，图片还没有加载，设置默认图片为白色背景
				holder.file_img.setImageResource(android.R.color.white);
				// 启动一个异步任务去加载图片
				MyTask task = new MyTask();
				task.execute(myFile.getFilePath(), String.valueOf(position));
			} else {
				// 有图片，直接拿出来用，绑定给图片控件
				// holder.file_img.setImageBitmap(myFile.getBitmap());
				holder.file_img.setImageBitmap(myFile.getBitmap().get());
			}
		}else if(myFile.isDir()){
			// 判断是否是目录			 
			holder.file_img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.dirs));
		}else{
			// 普通文件
			holder.file_img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.file));
		}
		
		holder.file_name.setText(myFile.getFileName());

		return convertView;
	}

	private class MyTask extends AsyncTask<String, Void, Bitmap> {

		// 后台加载
		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0]; // 图片路径
			int positon = Integer.valueOf(params[1]); // 对应文件下标
			// 本地sd卡加载图片
			BitmapFactory.Options opts = new Options();
			opts.inSampleSize = 2; // 图片宽、高缩小到原来的1/2，  缩小图片到原来的1/4【注意要是2的倍数】
			Bitmap bitmap = BitmapFactory.decodeFile(path, opts);

			// data.get(positon).setBitmap(bitmap); //设置图片
			// return bitmap;

			data.get(positon).setBitmap(new SoftReference<Bitmap>(bitmap));  
			return null;
		}

		// 加载完了就会执行该方法，是在主线程里,刷新UI
		@Override
		protected void onPostExecute(Bitmap result) {
			// 刷新适配器，更新UI
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);               
		}
	}

	private class ViewHolder {
		ImageView file_img;
		TextView file_name;
	}

}
