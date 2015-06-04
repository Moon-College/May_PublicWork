package com.yl.adapter;

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

import com.yl.bean.SdFile;
import com.yl.listview.R;

public class ListViewAdapter extends BaseAdapter{
	private List<SdFile> data;
	private LayoutInflater inflater;
	private static final int WIDTH=72;
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
		Log.i("INFO", "run1111");
		InFile infile=new InFile();
		if(convertView==null){
			convertView=inflater.inflate(R.layout.listview_layout, null);
			infile.img=(ImageView) convertView.findViewById(R.id.img);
			infile.tv=(TextView) convertView.findViewById(R.id.tv);
			convertView.setTag(infile);
		}else{
			infile=(InFile) convertView.getTag();
		}
		if(data.get(position).isIsimg()){
			if(data.get(position).getSoftBitmap().get()==null){
				infile.img.setImageResource(android.R.color.white);
				MyTask mytask=new MyTask();
				mytask.execute(String.valueOf(position),data.get(position).getPath());
			}else{
				infile.img.setImageBitmap(data.get(position).getSoftBitmap().get());
			}
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
	private class MyTask extends AsyncTask<String, Void, SoftReference<Bitmap>>{
		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			int position=Integer.parseInt(params[0]);
			String path=params[1];	
			//这样写，在内存中也是存在的
			//Bitmap bitmap=getBitmap(path);
			SoftReference<Bitmap> softbitmap=new SoftReference<Bitmap>(getBitmap(path));
			data.get(position).setSoftBitmap(softbitmap);
			return softbitmap;
		}
		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			ListViewAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		private Bitmap getBitmap(String path){
			BitmapFactory.Options option=new Options();
			//先获得真实宽度
			option.inJustDecodeBounds=true;
			Bitmap bitmap=BitmapFactory.decodeFile(path,option);
			int imgWidth=option.outWidth;
			//获得bitmap
			option.inJustDecodeBounds=false;
			if(imgWidth>WIDTH){
				option.inSampleSize=imgWidth/WIDTH;
			}
			bitmap=BitmapFactory.decodeFile(path,option);
			return bitmap;
		}
	}
}
