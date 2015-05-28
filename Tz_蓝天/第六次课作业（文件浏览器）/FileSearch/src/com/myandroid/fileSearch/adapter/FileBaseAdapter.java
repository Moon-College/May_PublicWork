package com.myandroid.fileSearch.adapter;

import java.util.List;

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

import com.myandroid.fileSearch.R;
import com.myandroid.fileSearch.bean.SdFile;

public class FileBaseAdapter extends BaseAdapter {

	private Context context = null;
	private List<SdFile> mList = null;
	private LayoutInflater inflater = null;
	private SdFile sdFile=null;

	public FileBaseAdapter(Context context, List<SdFile> list) {
		this.context = context;
		this.mList = list;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return mList.size();
	}

	public Object getItem(int position) {
		return mList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if (convertView == null) {
			convertView=inflater.inflate(R.layout.file_list_item, null);
			viewHolder=new ViewHolder();
			viewHolder.image=(ImageView) convertView.findViewById(R.id.imageFile);
			viewHolder.tvName=(TextView) convertView.findViewById(R.id.fileName);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		sdFile=mList.get(position);
		viewHolder.tvName.setText(sdFile.getName());
		if (sdFile.getBitmap()==null) {
			viewHolder.image.setImageResource(android.R.color.white);
			Task task=new Task();
			task.execute(sdFile.getFile_path(),String.valueOf(position));
		}else{
			viewHolder.image.setImageBitmap(sdFile.getBitmap());
		}
		return convertView;
	}

	private class ViewHolder{
		private ImageView image;
		private TextView tvName;
	}
	
	private class Task extends AsyncTask<String, Void, Bitmap>{

		@Override
		protected Bitmap doInBackground(String... params) {
			String path=params[0];
			int position=Integer.valueOf(params[1]);
			BitmapFactory.Options options=new BitmapFactory.Options();
			Bitmap bitmap=BitmapFactory.decodeFile(path,options); //通过路径加载图片
			mList.get(position).setBitmap(bitmap);//设置图片
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			FileBaseAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}

		
		
		
	}
	
}
