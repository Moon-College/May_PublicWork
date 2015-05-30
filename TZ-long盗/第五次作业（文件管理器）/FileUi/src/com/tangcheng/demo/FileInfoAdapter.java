package com.tangcheng.demo;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileInfoAdapter extends BaseAdapter {

	private ArrayList<FileInfo> data;
	private Context context;
	private LayoutInflater layoutInflater;
	public FileInfoAdapter(Context context, ArrayList<FileInfo> data) {

		this.context = context;
		this.data = data;
		this.layoutInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return data.size();
	}

	public FileInfo getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		ImageView icon;
		TextView filename;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			if (R.id.file_listView == parent.getId()) {
				convertView = layoutInflater.inflate(R.layout.file_list_item,
						null);
			} else if (R.id.file_gridView == parent.getId()) {
				convertView = layoutInflater.inflate(R.layout.file_grid_item,
						null);
			}
			holder = new ViewHolder();
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.filename = (TextView) convertView
					.findViewById(R.id.filename);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 应用数据
		FileInfo info = data.get(position);
		holder.filename.setText(info.getName());
		if (info.getBitmap() == null) {
			holder.icon.setImageResource(android.R.color.white);
			ImageTask task = new ImageTask();
			task.execute(info.getFilePath(),String.valueOf(position),"hello");
			
		}else{
			holder.icon.setImageBitmap(info.getBitmap());
		}

		
		return convertView;
	}
	
	private class ImageTask extends AsyncTask<String, Void, Bitmap>{
		@Override
		protected void onPostExecute(Bitmap result) {
			FileInfoAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			BitmapFactory.Options options = new Options();
			options.inSampleSize = 4;
			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
			data.get(position).setBitmap(bitmap);		
			return bitmap;
		}
		
	}

}
