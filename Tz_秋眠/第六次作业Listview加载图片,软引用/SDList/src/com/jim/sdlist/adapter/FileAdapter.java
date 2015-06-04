package com.jim.sdlist.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import com.jim.sdlist.R;
import com.jim.sdlist.beans.SDEntitiy;
import com.jim.sdlist.beans.utils.FileUtils;

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
	private List<SDEntitiy> list;
	private Context context;
	private LayoutInflater inflater;
	private int MAX_LIMIT = 72;

	public FileAdapter(List<SDEntitiy> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listview_item, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.img);
			holder.tv_count = (TextView) convertView
					.findViewById(R.id.tv_number);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SDEntitiy entitiy = list.get(position);
		String name = entitiy.getFile_name();
		String path = entitiy.getFile_path();
//		String count = entitiy.getFile_count();
		if (entitiy.isPic()) {
			if (entitiy.getSoftBitmap().get() == null) {
				holder.imageView.setImageResource(android.R.color.white);
				MyTask task = new MyTask();
				task.execute(path, String.valueOf(position));
			}else{
				holder.imageView.setImageBitmap(entitiy.getSoftBitmap().get());
			}
		} else {
			holder.imageView.setImageBitmap(entitiy.getBitmap());
		}
//		if ("".endsWith(count) || count == null) {
//			holder.tv_count.setVisibility(View.GONE);
//		} else {
//			holder.tv_count.setText(count);
//		}
		holder.tv_name.setText(name);
		return convertView;
	}

	static class ViewHolder {
		ImageView imageView;
		TextView tv_name, tv_count;
	}

	private class MyTask extends AsyncTask<String, Void, SoftReference<Bitmap>> {

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			// TODO Auto-generated method stub
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			SoftReference<Bitmap> softBitmap = null;
			softBitmap = new SoftReference<Bitmap>(scaleBitmap(path));
			list.get(position).setSoftBitmap(softBitmap);
			return softBitmap;
		}

		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			// TODO Auto-generated method stub
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}

		public Bitmap scaleBitmap(String path) {
			Bitmap bitmap = null;
			BitmapFactory.Options options = new Options();
			options.inJustDecodeBounds = true;
			bitmap = BitmapFactory.decodeFile(path, options);
			int w = options.outWidth;
			int h = options.outHeight;
			if (w > h) {
				if (w / MAX_LIMIT < 1) {
					options.inSampleSize = 1;
				} else {
					options.inSampleSize = (int) w / MAX_LIMIT;
				}
			} else {
				if (h / MAX_LIMIT < 1) {
					options.inSampleSize = 1;
				} else {
					options.inSampleSize = (int) h / MAX_LIMIT;
				}
			}
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(path, options);
			Log.d("TAG",
					"width:" + bitmap.getWidth() + "height:"
							+ bitmap.getHeight());
			return bitmap;
		}
	}
}
