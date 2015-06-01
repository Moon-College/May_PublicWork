package com.cn.test.adapter;

import java.util.List;

import com.cn.test.R;
import com.cn.test.been.SdFile;

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
 * Created on 2015-5-28 上午9:33:06 MyAdapter.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class MyAdapter extends BaseAdapter {
	private Context context;
	private List<SdFile> data;
	LayoutInflater inflater;

	public MyAdapter(Context context, List<SdFile> data) {
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			// 没有缓冲重新创建，创建了以后加入到缓存
			convertView = inflater.inflate(R.layout.list, null);
			holder = new ViewHolder();
			holder.file_img = (ImageView) convertView
					.findViewById(R.id.file_img);
			holder.file_name = (TextView) convertView
					.findViewById(R.id.file_name);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		SdFile msdfile = data.get(position);
		if (msdfile.getBitmap() == null) {
			// 图片还为加载，启动一个异步任务去加载图片
			// 设置默认图片
			holder.file_img.setImageResource(android.R.color.white);
			MyTask task = new MyTask();
			task.execute(msdfile.getFilepath().toString(),
					String.valueOf(position), "txt");
		}

		return convertView;
	}

	public class ViewHolder {
		ImageView file_img;
		TextView file_name;

	}

	private class MyTask extends AsyncTask<String, Void, Bitmap> {
		// 启动线程在后台加载
		@Override
		protected Bitmap doInBackground(String... params) {
			// 设置图片路径
			String path = params[0];
			// 设置文件下标
			int position = Integer.valueOf(params[1]);
			BitmapFactory.Options options = new Options();
			
			// 图片缩小到1/4
			options.inSampleSize = 2;
			// 通过路径加载的图片
			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
			// 设置图片
			data.get(position).setBitmap(bitmap);
			return bitmap; 
		}
		// 加载完毕后提供刷新UI
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			MyAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}