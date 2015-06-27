package com.caist.bean;

import java.lang.ref.SoftReference;
import java.util.List;

import com.caist.hc.R;
import com.caist.myfile.FolderFile;


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

public class NewAdapter extends BaseAdapter{

	final static int CHANGEWIDTH =72;
	List<FolderFile> data;
	Context context;
	LayoutInflater inflater;
	public NewAdapter() {
		// TODO Auto-generated constructor stub
	}
	public NewAdapter(Context context, List<FolderFile> data){
		this.context = context;
	    this.data = data;
	    inflater = LayoutInflater.from(context);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
    
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyHold hold =null;
		if(convertView==null){
		    convertView = inflater.inflate(R.layout.myfolderlist, null);   	
		    ImageView imageView = (ImageView) convertView.findViewById(R.id.iv);
		    TextView textView = (TextView) convertView.findViewById(R.id.tv);
		    hold = new MyHold();	
		    hold.imageView = imageView;
		    hold.textView = textView;
		    convertView.setTag(hold);
		}
		else{
			hold = (MyHold) convertView.getTag();
		}
		 FolderFile folderFile =data.get(position);
		 if(folderFile.isImage()){
			 Log.i("INFO","VIew");
			 if(folderFile.getSoftBitmap().get()==null){
				 Log.i("INFO","VIew3");	 
				 hold.imageView.setImageResource(android.R.color.white);
				 MyTask myTask = new MyTask();
				 myTask.execute(folderFile.getFilePath(),String.valueOf(position));

			 }else{
				 hold.imageView.setImageBitmap(folderFile.getSoftBitmap().get());
			 }
		 }
		 
	//	 if(null==folderFile.getBitmap()){
	//		 hold.imageView.setImageResource(android.R.color.white);
	//		 MyTask myTask = new MyTask();
	//		 myTask.execute(folderFile.getFilePath(),String.valueOf(position));
	//	 }
	     else{
			 hold.imageView.setImageBitmap(folderFile.getBitmap());
		 }
		 hold.textView.setText(folderFile.getFileName());
		return convertView;
	}
	
	private class MyHold{
		ImageView imageView;
		TextView textView;
	}
    private class MyTask extends AsyncTask<String, Void, SoftReference<Bitmap>>{
       //在后台提供线程加载
		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			// TODO Auto-generated method stub
			String path = params[0];
			int position = Integer.valueOf(params[1]);			
			Bitmap bitmap = scaleBitmap(path);
			SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitmap);			
			data.get(position).setSoftBitmap(softBitmap);
			return softBitmap;
		}
		public Bitmap scaleBitmap(String path){
			Bitmap bitmap = null;
			BitmapFactory.Options options = new Options();
			options.inJustDecodeBounds = true;
			options.inSampleSize = 2;
			bitmap = BitmapFactory.decodeFile(path, options);
			int width = options.outWidth;
			int height = options.outHeight;
			int scalewidth = 1;
			int scaleheight = 1;
			if(width >NewAdapter.CHANGEWIDTH ){
				scalewidth = width/NewAdapter.CHANGEWIDTH; 
				scaleheight = scalewidth;
			}
			options.inSampleSize = scalewidth;
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(path, options);
			return bitmap;
		}
    	protected void onPostExecute(SoftReference<Bitmap> result) {
    		// TODO Auto-generated method stub
    		NewAdapter.this.notifyDataSetChanged();
    		super.onPostExecute(result);
    	}
    }
}
