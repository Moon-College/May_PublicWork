package com.caist.bean;

import java.util.List;

import com.caist.hc.R;
import com.caist.myfile.FolderFile;


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

public class NewAdapter extends BaseAdapter{

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
		 if(null==folderFile.getBitmap()){
			 hold.imageView.setImageResource(android.R.color.white);
			 MyTask myTask = new MyTask();
			 myTask.execute(folderFile.getFilePath(),String.valueOf(position));
		 }else{
			 hold.imageView.setImageBitmap(folderFile.getBitmap());
		 }
		 hold.textView.setText(folderFile.getFileName());
		return convertView;
	}
	
	private class MyHold{
		ImageView imageView;
		TextView textView;
	}
    private class MyTask extends AsyncTask<String, Void, Bitmap>{
       //在后台提供线程加载
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			String path = params[0];
			int position = Integer.valueOf(params[1]);			
			BitmapFactory.Options options = new Options();
			options.inSampleSize = 2;
			Bitmap bitmap = BitmapFactory.decodeFile(path,options);
			data.get(position).setBitmap(bitmap);
			return bitmap;
		}
		//加载图片后刷新UI
    	@Override
    	protected void onPostExecute(Bitmap result) {
    		// TODO Auto-generated method stub
    		NewAdapter.this.notifyDataSetChanged();
    		super.onPostExecute(result);
    	}
    }
}
