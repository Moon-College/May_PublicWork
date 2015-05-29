package com.file.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import com.example.filemanager.R;
import com.file.bean.FileBean;

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

public class FileAdapter extends BaseAdapter {
	private List<FileBean> mFileData;
	private LayoutInflater mInflater;

	public FileAdapter(Context context, List<FileBean> mFileData) {
		super();
		this.mFileData = mFileData;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mFileData.size();
	}

	@Override
	public Object getItem(int position) {
		return mFileData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.fileitem, null);
			holder = new Holder();
			holder.fileImage = (ImageView) convertView
					.findViewById(R.id.fileimage);
			holder.fileName = (TextView) convertView
					.findViewById(R.id.filename);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		if (mFileData.get(position).isPic()) {
			if (mFileData.get(position).getSoftBitmap().get() == null) {
				holder.fileImage.setImageResource(Color.BLUE);
				new MyAsynTask().execute(mFileData.get(position).getFilePath(),
						String.valueOf(position));
			} else {
				holder.fileImage.setImageBitmap(mFileData.get(position)
						.getSoftBitmap().get());
			}
		} else {
			holder.fileImage
					.setImageBitmap(mFileData.get(position).getBitmap());
		}

		holder.fileName.setText(mFileData.get(position).getName());
		return convertView;
	}

	private class Holder {
		public ImageView fileImage;
		public TextView fileName;
	}

	class MyAsynTask extends AsyncTask<String, Void, SoftReference<Bitmap>> {
		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(scaleBitmap(params[0],10));
			mFileData.get(Integer.parseInt(params[1])).setSoftBitmap(softBitmap);
			return softBitmap;
		}

		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
	
	
	public Bitmap scaleBitmap(String path,int mwidth){
		Bitmap bitmap = null;
		BitmapFactory.Options options = new Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeFile(path,options);
		int width = options.outWidth;
		int height = options.outHeight;
		
		float bili=width/height;
		int mheight=(int) (mwidth/bili);
		options.inSampleSize = (width/mwidth + height/mheight)/2;
		
		options.inJustDecodeBounds = false;  
		bitmap = BitmapFactory.decodeFile(path,options);
		return bitmap;
	}
}
