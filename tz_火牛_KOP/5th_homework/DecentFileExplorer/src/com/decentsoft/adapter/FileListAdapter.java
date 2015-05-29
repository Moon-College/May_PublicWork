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
		 * LinkedHashMap��һ��removeEldestEntry(Map.Entry eldest)������
		 * ͨ�������������������һ����������������������true�� ��put���µ�ֵ��������trueʱ�����Ƴ���map�����ϵļ���ֵ��
		 */
		@Override
		protected boolean removeEldestEntry(Entry<String, Bitmap> entry)
		{
			if (this.size() > MAX_NUM)
			{
				/*
				 * ����secondCachedIconMap
				 */
				addToSecondCachedIcon(entry.getKey(), entry.getValue());
				Log.d(TAG,
						"call addToSecondCachedIcon getKey=" + entry.getKey());
				/*
				 * ����true��ʾ���Ƴ����entry
				 */
				return true;
			}
			/*
			 * ����false��ʾ�����Ƴ�
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
	 * ����FileListAdapter������
	 * 
	 * @param data
	 *            ����
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
		 * ���convertViewû�У��򴴽����convertView���������ռ����Tag���滺��
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
			 * ֱ�ӻ�ȡTag����Ļ���
			 */
			vh = (ViewHolder) convertView.getTag();
		}
		SdcardFile sdf = data.get(position);
		/**
		 * �����ͼƬ�ļ���ʹ�������첽����ͼƬ��Ȼ����ʾ
		 */
		if (ConstraintTool.isPicFileName(sdf.getName()))
		{
			// sdf.setIcon(DecentFileExplorerActivity.mLoadingIcon);
			// Log.d(TAG, "into sdf.getName()");

			/*
			 * ���getCachedIcon��Ϊ�գ�����ʾ
			 */
			if (sdf.getCachedIcon() != null && sdf.getCachedIcon().get() != null)
			{
				icon = sdf.getCachedIcon().get();
				//Log.d(TAG, "cachedIcon hit");
			} 
			else
			{
				/*
				 * ���sdf.getCachedIcon()û��������һ�����أ��ڼ�����֮ǰ����ʾmLoadingIcon
				 */
				icon = DecentFileExplorerActivity.mLoadingIcon;
				AsyncLoadingTask alt = new AsyncLoadingTask();
				alt.execute(sdf.getPath(), String.valueOf(position));
				Log.d(TAG, "cachedIcon NOT hit");
			}
		} else
		{
			/**
			 * �����ļ������ļ�������ֱ����ʾ�Ϳ���
			 */
			icon = sdf.getIcon();

		}
		vh.tv.setText(sdf.getName());
		vh.iv.setImageBitmap(icon);
		/**
		 * ����convertView
		 */
		return convertView;
	}

	/**
	 * ���ڻ���file_item�ؼ�����
	 * 
	 * @author ��ţ_KOP
	 *
	 */
	private class ViewHolder
	{
		private TextView tv;
		private ImageView iv;
	}

	/**
	 * �첽����ͼƬ������ AsyncTask<String, Void,
	 * Bitmap>������͵��������ͣ��ֱ��Ӧ��������progress�ؼ��ͽ������ֵ
	 * 
	 * @author ��ţ_KOP
	 *
	 */
	private class AsyncLoadingTask extends
			AsyncTask<String, Void, SoftReference<Bitmap>>
	{

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params)
		{
			/*
			 * ���ݲ�����ȡ·������data�����λ��
			 */
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			Bitmap icon = null;
			SoftReference<Bitmap> cachedIcon = null;
			/*
			 * ���ʹ�ö������淽ʽ��������ĺ���
			 */
			// icon = getCachedIcon(path);
			/*
			 * ������ö������淽ʽ��ֱ����cachedIcon�ֶ�
			 */

			// Log.d(TAG, "path="+path);
			/* ���û�д�CachedIcon��ȡ�ɹ� */
			// if(icon==null)
			// {
			/*
			 * ��ȡͼƬ��bitmap,Ȼ�����data������,����ֱ��decodeFile����ͼƬ�ᱨ Caused by:
			 * java.lang.OutOfMemoryError: bitmap size exceeds VM budget
			 * �ڴ����������Ҫ��decodeFile��ʱ������option,��������ͼƬ��Ч��
			 */
			BitmapFactory.Options ops = new BitmapFactory.Options();

			// ��һ������inJustDecodeBoundsΪtrue����ȡͼƬ�Ĵ�С�ߴ磬һ��ȡ����iconΪnull
			ops.inJustDecodeBounds = true;
			icon = BitmapFactory.decodeFile(path, ops);

			// ���ݿ�ȼ�������ű���scale
			int scale = (int) (ops.outWidth / PIC_WIDTH);
			if (scale < 1)
			{
				scale = 1;
			}

			Log.d(TAG, "ops.outWidth = "+ops.outWidth+",scale="+scale);
			// �ڶ��������ļ���icon
			ops.inJustDecodeBounds = false;
			ops.inSampleSize = scale;
			icon = BitmapFactory.decodeFile(path, ops);
			cachedIcon = new SoftReference<Bitmap>(icon);
			icon = null;
			/*
			 * ��������ʹ������ĺ���
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
		 * ����һ������
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
		 * ��һ�������л�ȡ,�����ȡ����ֱ�ӷ���
		 */
		bitmap = firstCachedIconMap.get(key);
		if (bitmap != null)
		{
			Log.d(TAG, "firstCachedIconMap hit");
			return bitmap;
		}

		/*
		 * �Ӷ��������л�ȡ
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
