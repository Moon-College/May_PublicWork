package com.qfx.filebrowser.adapter;

import java.util.List;

import com.qfx.filebrowser.R;
import com.qfx.filebrowser.bean.SdFile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileListAdapter extends BaseAdapter {

	private Context context;
	private List<SdFile> data;
	private LayoutInflater inflater;
	
	public FileListAdapter(Context context, List<SdFile> data) {
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public SdFile getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private class ViewHolder {
		ImageView iv;
		TextView tv;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv_list_item);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_list_item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SdFile sdFile = getItem(position);
		holder.tv.setText(sdFile.getFileName());
		if (sdFile.getFileBmp() != null) {
			holder.iv.setImageBitmap(sdFile.getFileBmp());
		} else {
			//空，重置为默认图片
			holder.iv.setImageResource(R.drawable.ic_default_image);
			ImageTask task = new ImageTask(position);
			task.execute(sdFile.getFilePath());
		}
		return convertView;
	}
	
	private class ImageTask extends AsyncTask<String, Void, Bitmap> {
		
		private int position;
		
		public ImageTask(int position) {
			this.position = position;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0];
			
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;//长宽各缩小1/2，  相当于总个图片缩小到1/4
			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
			data.get(position).setFileBmp(bitmap);
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if (result != null) {
				notifyDataSetChanged();
			}
		}
		
	}

}
