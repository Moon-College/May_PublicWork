package com.tz.filebrower.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.tz.filebrower.R;
import com.tz.filebrower.bean.MyFile;
import com.tz.filebrower.task.CallBack;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinhan on 15/5/28.
 */
public class FileAdapter extends BaseAdapter {
    private List mList;
    private Context mContext;

    public FileAdapter(List mList, Context mContext) {
        if (mList != null) {
            this.mList = mList;
        } else {
            this.mList = new ArrayList();
        }
        this.mContext = mContext;
    }

    public void changData(List list) {
        if (list != null && !list.isEmpty()) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        mList.clear();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_item, null);
        ImageView iv_file = ViewHolder.get(convertView, R.id.iv_file);
        TextView tv_file = ViewHolder.get(convertView, R.id.tv_file);

        final MyFile file = (MyFile) getItem(position);

        tv_file.setText(file.getName());

        Bitmap bitmap = file.getmBitmap();
        if (bitmap != null) {
            iv_file.setImageBitmap(bitmap);
        } else {
            if (!file.isExecuting()) {
                CallBack callBack = new CallBack() {
                    @Override
                    public void callback(Bitmap map) {
                        SoftReference<Bitmap> reference = new SoftReference<Bitmap>(map);
                        file.setmBitmap(reference);
                        file.setIsExecuting(false);
                        FileAdapter.this.notifyDataSetChanged();
                    }
                };
                WeakReference<Context> context = new WeakReference<Context>(mContext);
                file.executeBitmapTask(callBack, context);
                file.setIsExecuting(true);
            }
        }
            return convertView;
    }

}
