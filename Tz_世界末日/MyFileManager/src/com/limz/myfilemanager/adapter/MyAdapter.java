package com.limz.myfilemanager.adapter;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

import com.limz.myfilemanager.activity.R;
import com.limz.myfilemanager.bean.MyFile;

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

public class MyAdapter extends BaseAdapter {

	public ArrayList<MyFile> list;
	private Context mContext;
	private LayoutInflater inflater;
	private final int LIMIT_LENTH = 300;
	
	public MyAdapter(Context mContext, ArrayList<MyFile> list) {
		this.mContext = mContext;
		this.list = list;
	}
	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
//		inflater = LayoutInflater.from(mContext);
//		View view = inflater.inflate(R.layout.item, null);
//		ImageView icon = (ImageView) view.findViewById(R.id.fileicon);
//		TextView name = (TextView) view.findViewById(R.id.filename);
//		name.setText(list.get(position).getName());
		ViewHolder vh = null;
		if(convertView == null) {
			inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.item, null);
			vh = new ViewHolder();
			vh.imageView = (ImageView) convertView.findViewById(R.id.fileicon);
			vh.textView = (TextView) convertView.findViewById(R.id.filename);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		MyFile file = list.get(position);
		if(file.isPic()) {
			if(file.getSoftBitmap().get() == null) {
				//初始加载或者SoftReference被回收
				vh.imageView.setImageResource(android.R.color.white);
				MyTask task = new MyTask();
				task.execute(file.getPath(), String.valueOf(position));
			} else {
				vh.imageView.setImageBitmap(file.getSoftBitmap().get());
			}
		} else {
			vh.imageView.setImageBitmap(file.getIcon());
		}
		
//		Log.d("mingzhu", "myFile : " + file.getName());
//		Log.d("mingzhu", "icon : " + file.getIcon());
//		if(file.getIcon() == null) {
//			vh.imageView.setImageResource(android.R.color.white);
//			MyTask task = new MyTask();
//			task.execute(file.getPath(), String.valueOf(position));
//		} else {
//			vh.imageView.setImageBitmap(file.getIcon());
//		}
		vh.textView.setText(file.getName());
		return convertView;
	}

	private class ViewHolder {
		ImageView imageView;
		TextView textView;
	}
	
	private class MyTask extends AsyncTask<String, Void, SoftReference<Bitmap>> {

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			String path = params[0];
			int pos = Integer.valueOf(params[1]);
			BitmapFactory.Options options = new Options();
			options.inSampleSize = 2;
			Log.d("mingzhu", "path : " + path);
//			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
			Bitmap bitmap = getZoomBitmap(path);
//			list.get(pos).setIcon(bitmap);
			SoftReference<Bitmap> softMap = new SoftReference<Bitmap>(bitmap);
			list.get(pos).setSoftBitmap(softMap);
			
			return softMap;
		}
		
		/**
		 * 获取压缩过后的Bitmap
		 * @author limingzhu
		 * @param path 图片路径
		 * @return 压缩后的Bitmap
		 */
		private Bitmap getZoomBitmap(String path) {
			//生成不占用内存的Bitmap对象
			BitmapFactory.Options options = new Options();
			options.inJustDecodeBounds = true;
			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
			int width = options.outWidth;
			int height = options.outHeight;
			if(width > LIMIT_LENTH || height > LIMIT_LENTH) {
				if(width > height) {
					options.inSampleSize = width/LIMIT_LENTH;
				} else {
					options.inSampleSize = height/LIMIT_LENTH;
				}
			}
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(path, options);
			Log.d("mingzhu", "after zoom : " + bitmap.getWidth());
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			MyAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}
