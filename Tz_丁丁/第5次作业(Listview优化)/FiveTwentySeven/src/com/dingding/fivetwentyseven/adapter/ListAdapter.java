package com.dingding.fivetwentyseven.adapter;

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

import com.dingding.fivetwentyseven.bean.SdFile;
import com.example.fivetwentyseven.R;

public class ListAdapter extends BaseAdapter{
	private Context context;
	private List<SdFile> data;
	private LayoutInflater inflater;
	public ListAdapter(Context context, List<SdFile> data) {
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
			convertView = inflater.inflate(R.layout.list_item, null);
			ImageView file_img = (ImageView) convertView.findViewById(R.id.file_img);
			TextView file_name = (TextView) convertView.findViewById(R.id.file_name);
			holder = new ViewHolder();
			holder.file_img = file_img;
			holder.file_name = file_name;
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		SdFile sdFile = data.get(position);
		if(sdFile.getBitmap() == null){
			holder.file_img.setImageResource(android.R.color.white);
			MyTask task = new MyTask();
			task.execute(sdFile.getFilePath(),String.valueOf(position));
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
	
	private class MyTask extends AsyncTask<String, Void, Bitmap>{
		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			BitmapFactory.Options options = new Options();
			options.inSampleSize = 2;
			Bitmap bitmap = BitmapFactory.decodeFile(path,options);
			data.get(position).setBitmap(bitmap);
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			ListAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}

}
