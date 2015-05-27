package com.ztt.ch4filemanager.adapter;

import java.util.List;

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

import com.ztt.ch4filemanager.R;
import com.ztt.ch4filemanager.model.SDFile;
import com.ztt.ch4filemanager.utils.Utils;

public class FileAdapter extends BaseAdapter
{
    Context context;
    
    List<SDFile> datas;
    
    LayoutInflater inflater;
    
    public FileAdapter(Context context, List<SDFile> datas)
    {
        super();
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }
    
    public void setData(List<SDFile> datas)
    {
        this.datas = datas;
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount()
    {
        return datas.size();
    }
    
    @Override
    public Object getItem(int position)
    {
        return datas.get(position);
    }
    
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        SDFile file = datas.get(position);
        if (convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listview_item, null);
            holder.file_img =
                (ImageView) convertView.findViewById(R.id.iv_file_img);
            holder.file_name =
                (TextView) convertView.findViewById(R.id.tv_file_name);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        
        
        if (file.getBitmap() != null)
        {
            
            holder.file_img.setImageBitmap(file.getBitmap());
        }
        else
        {
            holder.file_img.setImageResource(android.R.color.black);
            //开启异步任务去下载图片
            LoadImageTask task = new LoadImageTask();
            task.execute(file.getPath(), String.valueOf(position));
        }
        holder.file_name.setText(file.getName());
        
        return convertView;
    }
    
    private class ViewHolder
    {
        ImageView file_img;
        
        TextView file_name;
    }
    
    /***
     * 异步下载图片
     * @author zhangtengteng
     *
     */
    class LoadImageTask extends AsyncTask<String, Void, Bitmap>
    {
        //启动线程后台加载
        @Override
        protected Bitmap doInBackground(String... params)
        {
            //图片路径
            String path = params[0];
            //文件的下表
            int position = Integer.valueOf(params[1]);
            BitmapFactory.Options options = new Options();
            options.inSampleSize = Utils.calculateInSampleSize(options, 60, 60);
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            datas.get(position).setBitmap(bitmap);
            return bitmap;
        }
        
        //加载完毕 刷新ui
        @Override
        protected void onPostExecute(Bitmap result)
        {
            notifyDataSetChanged();
            super.onPostExecute(result);
        }
    }
    
}
