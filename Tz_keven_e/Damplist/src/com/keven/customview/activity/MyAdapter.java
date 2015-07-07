package com.keven.customview.activity;

import java.lang.ref.SoftReference;
import java.util.List;

import com.keven.damp.R;

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
	List<FileDec> fileDecs;
	Context context;
	private static final int LIMT_WIDTH = 200;
	private static final int LIMT_HEIGHT = 300;

	public MyAdapter(List<FileDec> fileDecs, Context context) {
		super();
		this.fileDecs = fileDecs;
		this.context = context;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return fileDecs.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return fileDecs.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listview_file_view, null);

			holder.iv = (ImageView) convertView.findViewById(R.id.file_img);
			holder.tv = (TextView) convertView.findViewById(R.id.file_name);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();

		}
		FileDec fileDec = fileDecs.get(position);
		if(fileDec.isPic()){
			
			if (fileDec.getSoftBitmap().get() == null) {
				holder.iv.setImageResource(android.R.color.white);
				new MyAsynTask().execute(fileDec.getFilePath(), String.valueOf(position));
			
			}else{//it indicates  the memory can receive
				 holder.iv.setImageBitmap(fileDec.getSoftBitmap().get());
			}
		}else{
			
			holder.iv.setImageBitmap(fileDec.getBitmap());
		}
		holder.tv.setText(fileDec.getFileName());
		return convertView;
	}

	static class Holder {
		ImageView iv;
		TextView tv;
	}

	class MyAsynTask extends AsyncTask<String, Void, Bitmap> {

		protected Bitmap doInBackground(String... params) {
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			FileDec fileDec = fileDecs.get(position);
			Bitmap bitmap = scaleBitmap(path);
			SoftReference<Bitmap> softReference=new SoftReference<Bitmap>(bitmap);
			fileDec.setSoftBitmap(softReference);
			return bitmap;
		}

		private Bitmap scaleBitmap(String path) {
			Bitmap bitmap=null;
			Options opts = new Options();
			opts.inJustDecodeBounds = true;
			 bitmap = BitmapFactory.decodeFile(path, opts);
			Log.e("width", opts.outWidth+"");
			if (opts.outWidth > LIMT_WIDTH && opts.outHeight > LIMT_HEIGHT) {
				
				if (opts.outWidth >opts.outHeight) {
					opts.inSampleSize = (opts.outHeight / LIMT_WIDTH + opts.outWidth
							/ LIMT_HEIGHT) / 2;
				} else {
					opts.inSampleSize = (opts.outHeight / LIMT_HEIGHT + opts.outWidth
							/ LIMT_WIDTH) / 2;
				}
			}
			opts.inJustDecodeBounds = false;
			 bitmap = BitmapFactory.decodeFile(path, opts);
			return bitmap;
			// Pop
		}

		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			MyAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);

		}

	}

}
