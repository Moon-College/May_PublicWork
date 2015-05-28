package com.xigua.filebrowser;

import java.io.File;
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

public class FileAdapter extends BaseAdapter{
	private Context context;
	private List<cFile> data;
	private LayoutInflater inflater;
	public FileAdapter(Context context,List<cFile> data){
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
		cFile cFile = data.get(position);
		if(cFile.getBitmap() == null&&!cFile.getName().equals("·µ»Ø")){
			holder.file_img.setImageResource(android.R.color.darker_gray);
			MyTask task = new MyTask();
			task.execute(cFile.getFilePath(),String.valueOf(position));
		}else{
			holder.file_img.setImageBitmap(cFile.getBitmap());
		}
		holder.file_name.setText(cFile.getName());
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
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}
