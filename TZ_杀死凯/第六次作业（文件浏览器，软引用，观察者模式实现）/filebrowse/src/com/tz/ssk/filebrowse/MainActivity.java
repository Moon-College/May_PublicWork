package com.tz.ssk.filebrowse;


import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private ListView listview;
	private List<Entity> data;
	private Context mContext;
	private Adapter mAdapter;
	final int LIMIT_WIDTH = 100;
	private RealizeSskObserveable sskobserveable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        listview=(ListView) findViewById(R.id.listview);
        //被观察者
		sskobserveable=new RealizeSskObserveable();
        initdata(Environment.getExternalStorageDirectory().getAbsolutePath());
        listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				    if(((Entity)mAdapter.getItem(position)).isfolder)
				    {
				    	initdata(((Entity)mAdapter.getItem(position)).path);
				    }else
				    {
				    	Toast.makeText(mContext, "只能打开文件夹", Toast.LENGTH_LONG).show();
				    }
					
			}
		});
    }
    private void initdata(String path) {
    	File fls=new File(path);
    	if(fls==null)
    	{
    		Toast.makeText(mContext, "哥们逗我呢。。。", Toast.LENGTH_LONG).show();
    	}else
    	{
    		String backpath=path.substring(0,path.lastIndexOf("/"));
    		data=new ArrayList<Entity>();
    		//添加返回按钮
    		Entity en_back=new Entity();
    		en_back.name="父级...";
    		en_back.file=new File(backpath);
    		en_back.path=backpath;
    		en_back.bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.dirs);
    		en_back.isfolder=true;
			data.add(en_back);
        	fls.listFiles();
    		for(File fl:fls.listFiles())
    		{
    			Entity en=new Entity();
    			en.name=fl.getName();
    			en.file=fl;
    			en.path=fl.getAbsolutePath();
    			if(fl.isDirectory())
    			{
    				en.isfolder=true;
    				en.bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.dirs);
    			}else if(fl.getName().toLowerCase().endsWith(".png")||fl.getName().toLowerCase().endsWith(".jpg"))
    			{
    				//图片
    				//en.bitmap=null;
    				en.softbitmap=new SoftReference<Bitmap>(null);
    				en.ispic=true;
    			}else
    			{
    				en.bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.file);
    			}
    			data.add(en);
    			Log.i("INFO",fl.getName());
    		}
    		mAdapter=new Adapter();
    		listview.setAdapter(mAdapter);
    	}
    	
	}
    private class Adapter extends BaseAdapter
    {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder mViewHolder;
			if(convertView==null)
			{
				mViewHolder=new ViewHolder();
				convertView=LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
				mViewHolder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
				mViewHolder.tv_time=(TextView) convertView.findViewById(R.id.tv_time);
				mViewHolder.iv_picture=(ImageView) convertView.findViewById(R.id.iv_picture);
				convertView.setTag(mViewHolder);
			}else
			{
				mViewHolder=(ViewHolder) convertView.getTag();
			}
			
			if(data.get(position).ispic)
			{
				//图片
				if(data.get(position).softbitmap.get()==null)
				{
					//观察者
					RealizeSskObserver sskobserver=new RealizeSskObserver();
					//注册观察者
					sskobserveable.registeObserver(sskobserver);
					//被回收了。
					MyTask mytask=new MyTask();
					mytask.execute(data.get(position).file.getPath(),position+"");
					
				}else
				{
					mViewHolder.iv_picture.setImageBitmap(data.get(position).softbitmap.get());
				}
			}else
			{
				//文件夹或其他文件
				mViewHolder.iv_picture.setImageBitmap(data.get(position).bitmap);
			}
			mViewHolder.tv_name.setText(data.get(position).name);
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			Date dat=new Date(data.get(position).file.lastModified()); 
			mViewHolder.tv_time.setText(format.format(dat)+"");
			return convertView;
		}
		private class ViewHolder
		{
			TextView tv_name;
			TextView tv_time;
			ImageView iv_picture;
		}
		private class MyTask extends AsyncTask<String,Void,SoftReference<Bitmap>>{

			@Override
			protected SoftReference<Bitmap> doInBackground(String... params) {
				// TODO Auto-generated method stub
				int position = Integer.valueOf(params[1]);
				SoftReference<Bitmap> mSoftBitmap=new SoftReference<Bitmap>(scaleBitmap(params[0]));
				data.get(position).softbitmap=mSoftBitmap;
				return mSoftBitmap;
			}
			@Override
			protected void onPostExecute(SoftReference<Bitmap> result) {
				// TODO Auto-generated method stub
				mAdapter.notifyDataSetChanged();
				sskobserveable.notifyObserverAll();
				super.onPostExecute(result);
			}
			
		}
		private Bitmap scaleBitmap(String path)
		{
			BitmapFactory.Options mOptions=new Options();
			mOptions.inJustDecodeBounds=true;
			BitmapFactory.decodeFile(path,mOptions);
			int height=mOptions.outHeight;
			int width=mOptions.outWidth;
			mOptions.inJustDecodeBounds=false;
			mOptions.inSampleSize=(width/LIMIT_WIDTH);
			Bitmap bitmap=BitmapFactory.decodeFile(path,mOptions);
			Log.i("INFO", "width="+width+"/width="+bitmap.getWidth());
			return bitmap;
		}
    }
	private class Entity
	{
		public File file;
		public String path;
		public String name;
		public boolean isfolder;
		public Bitmap bitmap;
		public SoftReference<Bitmap> softbitmap;
		public boolean ispic;
	}
	
    

}
