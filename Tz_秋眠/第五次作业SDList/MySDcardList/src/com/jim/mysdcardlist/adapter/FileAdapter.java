package com.jim.mysdcardlist.adapter;

import java.util.List;

import com.jim.mysdcardlist.R;
import com.jim.mysdcardlist.beans.SDFiler;

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

public class FileAdapter extends BaseAdapter {
	private List<SDFiler> list;
	private Context context;
	private LayoutInflater inflater;

	public FileAdapter(List<SDFiler> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, null);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.file_name = (TextView) convertView.findViewById(R.id.tv);
			holder.file_count = (TextView) convertView
					.findViewById(R.id.tv_number);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SDFiler filer = list.get(position);
		if (filer.getBitmap() == null) {
			holder.img.setImageResource(android.R.color.white);
			new BitmapTask().execute(filer.getFile_url(),
					String.valueOf(position));
		} else {
			holder.img.setImageBitmap(filer.getBitmap());
			
		}
		holder.file_name.setText(filer.getFile_name());
		String file_count = filer.getFile_count();
		if ("-1".equals(file_count)) {
			holder.file_count.setVisibility(View.GONE);
		} else {
			holder.file_count.setText(file_count);
		}
		return convertView;
	}

	static class ViewHolder {
		ImageView img;
		TextView file_name, file_count;
	}

	class BitmapTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			Options options = new Options();
			options.inSampleSize = 2;
			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
			list.get(position).setBitmap(bitmap);
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}
