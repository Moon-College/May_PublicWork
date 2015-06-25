package com.yl.adapter;

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

import com.yl.bean.SdFile;
import com.yl.listview.R;

public class ListViewAdapter extends BaseAdapter{
	private List<SdFile> data;
	private LayoutInflater inflater;
	public ListViewAdapter(Context context,List<SdFile> data){
		this.data=data;
		inflater=LayoutInflater.from(context);
	}
	public ListViewAdapter(){
		
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return this.data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		InFile infile=new InFile();
		if(convertView==null){
			convertView=inflater.inflate(R.layout.listview_layout, null);
			infile.img=(ImageView) convertView.findViewById(R.id.img);
			infile.tv=(TextView) convertView.findViewById(R.id.tv);
			convertView.setTag(infile);
		}else{
			infile=(InFile) convertView.getTag();
		}
		
		if(data.get(position).getImg()==null){
			Log.i("INFO", "run");
			infile.img.setImageResource(android.R.color.white);
			MyTask mytask=new MyTask();
			mytask.execute(String.valueOf(position),data.get(position).getPath());
		}else{
			infile.img.setImageBitmap(data.get(position).getImg());
		}
		infile.tv.setText(data.get(position).getName());
		return convertView;
	}
	private class InFile{
		public ImageView img;
		public TextView tv;
	}
	private class MyTask extends AsyncTask<String, Void, Bitmap>{
		@Override
		protected Bitmap doInBackground(String... params) {
			int position=Integer.parseInt(params[0]);
			String path=params[1];
			BitmapFactory.Options option=new Options();
			option.inSampleSize=10;
			Bitmap bitmap=BitmapFactory.decodeFile(path,option);
			data.get(position).setImg(bitmap);
			return bitmap;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			ListViewAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}
