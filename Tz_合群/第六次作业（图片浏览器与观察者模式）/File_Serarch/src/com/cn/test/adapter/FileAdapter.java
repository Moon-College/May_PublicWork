package com.cn.test.adapter;

/**
 * Created on2015-5-29 上午11:27:40 FileAdapter.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TssqODO
 */
import java.lang.ref.SoftReference;
import java.util.List;

import com.cn.test.R;
import com.cn.test.bean.SdFile;

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

//import com.example.twentyeighthomework.R;

public class FileAdapter extends BaseAdapter {

	private final int LIMIT_WIDTH = 80;
	private Context context;
	private List<SdFile> data;
	private LayoutInflater inflater;

	public FileAdapter(Context context, List<SdFile> data) {
		super();
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}

	private class ViewHolder {
		ImageView fileImg;
		TextView fileName;
	}

		//图片需要软引用，加载图片易引起OOM异常
	private class MyTask extends AsyncTask<String, Void, SoftReference<Bitmap>> {

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			Bitmap scaleBitmap = scaleBitmap(path);
			SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(
					scaleBitmap);
			data.get(position).setSoftBitmap(softReference);
			return softReference;
		}

		private Bitmap scaleBitmap(String path) {
			Bitmap bitmap = null;
			BitmapFactory.Options options = new Options();
			options.inJustDecodeBounds = true;
			bitmap = BitmapFactory.decodeFile(path, options);
			int width = options.outWidth;
			int height = options.outHeight;
			int limit_height = height * LIMIT_WIDTH / width;
			//计算缩放的倍数
			if (width > LIMIT_WIDTH && height > limit_height) {
				if (width > height) {
					options.inSampleSize = (width / limit_height + height
							/ LIMIT_WIDTH) / 2;
				} else {
					options.inSampleSize = (width / LIMIT_WIDTH + height
							/ limit_height) / 2;
				}
			}
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(path, options);
			Log.v("homework", "width" + bitmap.getWidth());
			return bitmap;
		}

		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}

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
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			//没有缓冲重新创建，创建了以后加入到缓存
			convertView = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.fileImg = (ImageView) convertView
					.findViewById(R.id.file_img);
			holder.fileName = (TextView) convertView
					.findViewById(R.id.file_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SdFile sdFile = data.get(position);
		//将数据绑定给控件
		if (sdFile.isPic()) {
			//说明软应用的强应用对象被回收了，内存过高了，启动异步任务去加载软引用图片
			//首先显示一张白色的默认图片
			if (sdFile.getSoftBitmap().get() == null) {
				holder.fileImg.setImageResource(android.R.color.white);
				MyTask task = new MyTask();
				task.execute(sdFile.getFilePath(), String.valueOf(position));
			} else {
				holder.fileImg.setImageBitmap(sdFile.getSoftBitmap().get());
			}
		} else {
			holder.fileImg.setImageBitmap(sdFile.getBitmap());
		}
		holder.fileName.setText(sdFile.getName());
		return convertView;
	}

}
