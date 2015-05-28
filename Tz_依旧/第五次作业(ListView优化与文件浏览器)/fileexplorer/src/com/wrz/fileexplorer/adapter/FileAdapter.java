package com.wrz.fileexplorer.adapter;

import java.util.List;

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

import com.wrz.fileexplorer.R;
import com.wrz.fileexplorer.bean.SdFile;

public class FileAdapter extends BaseAdapter{
	
	private List<SdFile> data;
	private Context context;
	private LayoutInflater inflater;
	
	public FileAdapter(Context context, List<SdFile> data){
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);
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
			// 没有缓存 重新创建，创建以后加入到缓存
			convertView = inflater.inflate(R.layout.file_listview, null);
			ImageView file_img = (ImageView) convertView.findViewById(R.id.file_img);
			TextView file_name = (TextView) convertView.findViewById(R.id.file_name);
			// 加入缓存，给后面的条目用
			holder = new ViewHolder();
			holder.file_img = file_img;
			holder.file_name = file_name;
			convertView.setTag(holder);
		}else{
			// 存在缓存，直接从缓存获取
			holder = (ViewHolder) convertView.getTag();
		}
		
		SdFile sdFile = data.get(position);
		if(sdFile.getBitmap() == null){
			// 图片还未加载，启动一个异步任务去加载图片
			// 设置默认图片
			holder.file_img.setImageResource(android.R.color.white);
			MyTask myTask = new MyTask();
			myTask.execute(sdFile.getFilePath(), String.valueOf(position));
		}else{
			holder.file_img.setImageBitmap(sdFile.getBitmap());
		}
		holder.file_name.setText(sdFile.getName());
		return convertView;
	}
	
	/***
	 * 麻布袋
	 * @author Wangrz
	 *
	 */
	private class ViewHolder{
		ImageView file_img;
		TextView file_name;
	}
	
	private class MyTask extends AsyncTask<String, Void, Bitmap>{

		/***
		 * 启动线程后再后台加载
		 * 
		 */
		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0];// 图片路径
			int position = Integer.valueOf(params[1]);// 文件下标
			BitmapFactory.Options options = new Options();
			options.inSampleSize = 2;// 图片缩小到1/4
			Bitmap bitmap = BitmapFactory.decodeFile(path, options);// 通过路径加载图片
			data.get(position).setBitmap(bitmap);// 设置图片
			return bitmap;
		}

		/***
		 * 加载完毕后提供刷新UI
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}


