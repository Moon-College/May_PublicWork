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
	private static int MAX_NUM = 10;
	private static int PIC_WIDTH = 70;
	private static Map<String, Bitmap> firstCachedIconMap = new LinkedHashMap<String, Bitmap>(
			MAX_NUM, (float) 0.6, true)
	{
		/**
		 * LinkedHashMap有一个removeEldestEntry(Map.Entry eldest)方法，
		 * 通过覆盖这个方法，加入一定的条件，满足条件返回true。 当put进新的值方法返回true时，便移除该map中最老的键和值。
		 */
		@Override
		protected boolean removeEldestEntry(Entry<String, Bitmap> entry)
		{
			if (this.size() > MAX_NUM)
			{
				/*
				 * 加入secondCachedIconMap
				 */
				addToSecondCachedIcon(entry.getKey(), entry.getValue());
				Log.d(TAG,
						"call addToSecondCachedIcon getKey=" + entry.getKey());
				/*
				 * 返回true表示会移除这个entry
				 */
				return true;
			}
			/*
			 * 返回false表示不会移除
			 */
			return false;
		}
	};
	private static Map<String, SoftReference<Bitmap>> secondCachedIconMap = new LinkedHashMap<String, SoftReference<Bitmap>>(
			MAX_NUM * 5, (float) 0.6, true);
	private static String TAG = "FileListAdapter";

	public List<SdcardFile> getData()
	{
		return data;
	}

	/**
	 * 更新FileListAdapter的数据
	 * 
	 * @param data
	 *            数据
	 */
	public void setData(List<SdcardFile> data)
	{
		this.data = data;
	}

	private LayoutInflater mInflator;

	public FileListAdapter(Context context, List<SdcardFile> data)
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
		if (convertView == null)
		{
			convertView = mInflator.inflate(R.layout.file_item, null);
			vh = new ViewHolder();
			vh.iv = (ImageView) convertView.findViewById(R.id.img);
			vh.tv = (TextView) convertView.findViewById(R.id.textFile);
			convertView.setTag(vh);
		} else
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
		if (ConstraintTool.isPicFileName(sdf.getName()))
		{
			// sdf.setIcon(DecentFileExplorerActivity.mLoadingIcon);
			// Log.d(TAG, "into sdf.getName()");

			/*
			 * 如果getCachedIcon不为空，则显示
			 */
			if (sdf.getCachedIcon() != null && sdf.getCachedIcon().get() != null)
			{
				icon = sdf.getCachedIcon().get();
				//Log.d(TAG, "cachedIcon hit");
			} 
			else
			{
				/*
				 * 如果sdf.getCachedIcon()没有内容则一部加载，在加载完之前先显示mLoadingIcon
				 */
				icon = DecentFileExplorerActivity.mLoadingIcon;
				AsyncLoadingTask alt = new AsyncLoadingTask();
				alt.execute(sdf.getPath(), String.valueOf(position));
				Log.d(TAG, "cachedIcon NOT hit");
			}
		} else
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
		return convertView;
	}

	/**
	 * 用于缓存file_item控件的类
	 * 
	 * @author 火牛_KOP
	 *
	 */
	private class ViewHolder
	{
		private TextView tv;
		private ImageView iv;
	}

	/**
	 * 异步加载图片的任务 AsyncTask<String, Void,
	 * Bitmap>这个泛型的三个类型，分别对应，参数，progress控件和结果返回值
	 * 
	 * @author 火牛_KOP
	 *
	 */
	private class AsyncLoadingTask extends
			AsyncTask<String, Void, SoftReference<Bitmap>>
	{

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params)
		{
			/*
			 * 根据参数获取路径和在data里面的位置
			 */
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			Bitmap icon = null;
			SoftReference<Bitmap> cachedIcon = null;
			/*
			 * 如果使用二级缓存方式就用下面的函数
			 */
			// icon = getCachedIcon(path);
			/*
			 * 如果不用二级缓存方式，直接用cachedIcon字段
			 */

			// Log.d(TAG, "path="+path);
			/* 如果没有从CachedIcon获取成功 */
			// if(icon==null)
			// {
			/*
			 * 获取图片的bitmap,然后更新data的数据,但是直接decodeFile加载图片会报 Caused by:
			 * java.lang.OutOfMemoryError: bitmap size exceeds VM budget
			 * 内存溢出错误需要在decodeFile的时候增加option,调整加载图片的效果
			 */
			BitmapFactory.Options ops = new BitmapFactory.Options();

			// 第一次设置inJustDecodeBounds为true，获取图片的大小尺寸，一次取出的icon为null
			ops.inJustDecodeBounds = true;
			icon = BitmapFactory.decodeFile(path, ops);

			// 根据宽度计算出缩放比例scale
			int scale = (int) (ops.outWidth / PIC_WIDTH);
			if (scale < 1)
			{
				scale = 1;
			}

			Log.d(TAG, "ops.outWidth = "+ops.outWidth+",scale="+scale);
			// 第二次真正的加载icon
			ops.inJustDecodeBounds = false;
			ops.inSampleSize = scale;
			icon = BitmapFactory.decodeFile(path, ops);
			cachedIcon = new SoftReference<Bitmap>(icon);
			icon = null;
			/*
			 * 二级缓存使用下面的函数
			 */
			// addToCachedIcon(MD5Util.getMD5String(path),icon);
			// }
			data.get(position).setCachedIcon(cachedIcon);
			// TODO Auto-generated method stub
			return cachedIcon;
		}

		@Override
		protected void onPostExecute(SoftReference<Bitmap> cachedIcon)
		{
			notifyDataSetChanged();
			super.onPostExecute(cachedIcon);
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
		if (bitmap != null)
		{
			Log.d(TAG, "firstCachedIconMap hit");
			return bitmap;
		}

		/*
		 * 从二级缓存中获取
		 */
		SoftReference<Bitmap> srb = secondCachedIconMap.get(key);
		if (srb == null)
		{
			return null;
		} else
		{
			Log.d(TAG, "secondCachedIconMap hit");
			return srb.get();
		}
	}
}
