package com.decentsoft.adapter;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.decentsoft.bean.SdcardFile;
import com.decentsoft.constraint.ConstraintTool;
import com.decentsoft.constraint.MD5Util;
import com.decentsoft.decentfileExplorer.DecentFileExplorerActivity;
import com.decentsoft.decentfileExplorer.R;

public class FileListAdapter extends BaseAdapter
{
    private Context context;
    private List<SdcardFile> data;
    private static int MAX_NUM = 20;
    private static Map<String,Bitmap> firstCachedIconMap = new LinkedHashMap<String, Bitmap>(MAX_NUM, (float)0.6, true)
    {
    	@Override
        protected boolean removeEldestEntry(Entry<String,Bitmap> entry)
        {
    		if(this.size()>MAX_NUM)
    		{
    			/*
    			 * 加入secondCachedIconMap
    			 */
    			addToSecondCachedIcon(entry.getKey(), entry.getValue());
    		}
        	return false;
        }
    };
    private static Map<String,SoftReference<Bitmap>> secondCachedIconMap = new LinkedHashMap<String, SoftReference<Bitmap>>(MAX_NUM*5, (float)0.6, true);
	private static String TAG="FileListAdapter";
    public List<SdcardFile> getData()
	{
		return data;
	}

    /**
     * 更新FileListAdapter的数据
     * @param data 数据
     */
	public void setData(List<SdcardFile> data)
	{
		this.data = data;
	}

	private LayoutInflater mInflator;
	public FileListAdapter(Context context,List<SdcardFile> data)
	{
		this.context = context;
		this.data = data;
		mInflator = LayoutInflater.from(this.context);
	}
	
	public int getCount()
	{
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder vh = null;
		Bitmap icon = null;
		/*
		 * 如果convertView没有，则创建这个convertView，把两个空间放入Tag里面缓存
		 */
		if(convertView==null)
		{
			 convertView = mInflator.inflate(R.layout.file_item, null);
			 vh = new ViewHolder();
			 vh.iv = (ImageView) convertView.findViewById(R.id.img); 
			 vh.tv = (TextView) convertView.findViewById(R.id.textFile);
			 convertView.setTag(vh);
		}
		else
		{
			/**
			 * 直接获取Tag里面的缓存
			 */
			vh = (ViewHolder) convertView.getTag();
		}
		SdcardFile sdf = data.get(position);
		/**
		 * 如果是图片文件，使用任务异步加载图片，然后显示
		 */
		if(ConstraintTool.isPicFileName(sdf.getName()))
		{
			//sdf.setIcon(DecentFileExplorerActivity.mLoadingIcon);
			//Log.d(TAG, "into sdf.getName()");
			
			if(DecentFileExplorerActivity.mLoadingIcon == sdf.getIcon())
			{
		        AsyncLoadingTask alt =new AsyncLoadingTask();
		        alt.execute(sdf.getPath(),String.valueOf(position));
			}
			icon =  sdf.getIcon();
		}
        else
        {
			/**
			 * 其他文件或者文件夹这是直接显示就可以
			 */
        	icon = sdf.getIcon();
			
		}
		vh.tv.setText(sdf.getName());
		vh.iv.setImageBitmap(icon);
		/**
		 * 返回convertView
		 */
		return  convertView;
	}
	/**
	 * 用于缓存file_item控件的类
	 * @author 火牛_KOP
	 *
	 */
	private class ViewHolder
	{
		private TextView tv;
		private ImageView iv;
	}
	
	/**
	 * 异步加载图片的任务
	 * AsyncTask<String, Void, Bitmap>这个泛型的三个类型，分别对应，参数，progress控件和结果返回值
	 * @author 火牛_KOP
	 *
	 */
	private class AsyncLoadingTask extends AsyncTask<String, Void, Bitmap>
	{

		@Override
		protected Bitmap doInBackground(String... params)
		{
			/*
			 * 根据参数获取路径和在data里面的位置 
			 */
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			Bitmap icon = null;
			icon = getCachedIcon(path);

			//Log.d(TAG, "path="+path);
			/* 如果没有从CachedIcon获取成功 */
			if(icon==null)
			{
				/*
				 * 获取图片的bitmap,然后更新data的数据,但是直接decodeFile加载图片会报
				 * Caused by: java.lang.OutOfMemoryError: bitmap size exceeds VM budget
				 * 内存溢出错误需要在decodeFile的时候增加option,调整加载图片的效果
				 */				
				BitmapFactory.Options ops = new BitmapFactory.Options();
				ops.inSampleSize = 2;
				icon = BitmapFactory.decodeFile(path,ops);
				addToCachedIcon(MD5Util.getMD5String(path),icon);
			}
			data.get(position).setIcon(icon);			
			// TODO Auto-generated method stub
			return icon;
		}
		
		@Override
		protected void onPostExecute(Bitmap icon)
		{
			notifyDataSetChanged();
			super.onPostExecute(icon);
		}
	}

	private static void addToCachedIcon(String key, Bitmap icon)
	{
		/*
		 * 介入一级缓存
		 */
		firstCachedIconMap.put(key, icon);
	}
	
	private static void addToSecondCachedIcon(String key, Bitmap icon)
	{
		// TODO Auto-generated method stub
		SoftReference<Bitmap> srb = new SoftReference<Bitmap>(icon);
		secondCachedIconMap.put(key, srb);
	}

	private static Bitmap getCachedIcon(String path)
	{
		String key = MD5Util.getMD5String(path);
		Bitmap bitmap = null;
		/*
		 * 从一级缓存中获取,如果获取到了直接返回
		 */
		bitmap = firstCachedIconMap.get(key);
		if(bitmap!=null)
		{
			Log.d(TAG, "firstCachedIconMap hit");
			return bitmap;
		}
		
		/*
		 * 从二级缓存中获取
		 */
		SoftReference<Bitmap>  srb = secondCachedIconMap.get(key);
		if(srb==null)
		{
			return null;
		}
		else
		{
			Log.d(TAG, "secondCachedIconMap hit");
		    return 	srb.get();
		}
	}
}
