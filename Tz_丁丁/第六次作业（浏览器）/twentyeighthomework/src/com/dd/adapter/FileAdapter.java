package com.dd.adapter;

import java.lang.ref.SoftReference;
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

import com.dd.bean.SdFile;
import com.example.twentyeighthomework.R;

public class FileAdapter extends BaseAdapter {

	private final int LIMIT_WIDTH = 72;
	private Context context;
	private List<SdFile> data;
	private LayoutInflater inflater;
	public FileAdapter(Context context, List<SdFile> data) {
		super();
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
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
		ViewHolder holder = null;
		if (convertView ==null) {
			convertView = inflater.inflate(R.layout.list_item, null);
			holder=new ViewHolder();
			holder.fileImg = (ImageView) convertView.findViewById(R.id.file_img);
			holder.fileName = (TextView) convertView.findViewById(R.id.file_name);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		SdFile sdFile = data.get(position);
		if (sdFile.isPic()) {
			if (sdFile.getSoftBitmap().get()==null) {
				holder.fileImg.setImageResource(android.R.color.white);
				MyTask task = new MyTask();
				task.execute(sdFile.getFilePath(),String.valueOf(position));
			}else {
				holder.fileImg.setImageBitmap(sdFile.getSoftBitmap().get());
			}
		}else {
			holder.fileImg.setImageBitmap(sdFile.getBitmap());
		}
		holder.fileName.setText(sdFile.getName());
		return convertView;
	}
	private class ViewHolder{
		ImageView fileImg;
		TextView fileName;
	}
	private class MyTask extends AsyncTask<String, Void, SoftReference<Bitmap>>{

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			Bitmap scaleBitmap = scaleBitmap(path);
			SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(scaleBitmap);
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
			int limit_height = height*LIMIT_WIDTH/width;
			if (width>LIMIT_WIDTH&&height>limit_height) {
				if (width>height) {
					options.inSampleSize = (width/limit_height+height/LIMIT_WIDTH)/2;
				}else {
					options.inSampleSize = (width/LIMIT_WIDTH+height/limit_height)/2;
				}
			}
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(path, options);
			Log.v("homework", "width"+bitmap.getWidth());
			return bitmap;
		}

		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
	}

}
