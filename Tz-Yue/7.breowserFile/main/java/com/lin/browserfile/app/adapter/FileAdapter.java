package com.lin.browserfile.app.adapter;

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

import com.lin.browserfile.app.R;
import com.lin.browserfile.app.entity.FileInfo;
import com.lin.browserfile.app.utils.BitmapUtil;
import com.lin.browserfile.app.utils.ViewHolder;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/31.
 */
public class FileAdapter extends BaseAdapter {
    private final Bitmap dirs;
    private Context context;
    private List<FileInfo> list;
    private LayoutInflater inflater;
    private final Bitmap defBitmap;

    public FileAdapter(Context context, List<FileInfo> list) {
        this.context = context;
        this.list = list == null ? new ArrayList<FileInfo>() : list;
        inflater = LayoutInflater.from(context);
        defBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.file);
        dirs = BitmapFactory.decodeResource(context.getResources(), R.mipmap.dirs);
    }

    public void upAdapter(List<FileInfo> list) {
        this.list.clear();
        if (list != null) {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public FileInfo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.file_item, null);
        }

        ImageView iv = ViewHolder.get(convertView, R.id.item_iv);
        TextView tv = ViewHolder.get(convertView, R.id.item_tv);

        FileInfo item = getItem(position);
        Bitmap bitmap = null;
        if (item.getBitmap() == null || (bitmap = item.getBitmap().get()) == null) {
            switch (item.getFileType()) {
                case FileInfo.TYPE_DIRECTORY:
                    item.setBitmap(new SoftReference<Bitmap>(dirs));
                    bitmap = dirs;
                    break;
                case FileInfo.TYPE_OTHER_FILE:
                    item.setBitmap(new SoftReference<Bitmap>(defBitmap));
                    bitmap = defBitmap;
                    break;
                case FileInfo.TYPE_PIC:
                    item.setBitmap(new SoftReference<Bitmap>(defBitmap));
                    imgLoadTask imgLoadTask = new imgLoadTask(item);
                    imgLoadTask.execute();
                    bitmap = defBitmap;
                    break;
                default:
                    item.setFileType(FileInfo.TYPE_PIC);
                    item.setBitmap(new SoftReference<Bitmap>(defBitmap));
                    bitmap = defBitmap;
                    break;
            }
        }



        tv.setText(item.getName());
        iv.setImageBitmap(bitmap);
        return convertView;
    }

    class imgLoadTask extends AsyncTask<Void, Void, SoftReference<Bitmap>> {

        private FileInfo fileInfo;

        public imgLoadTask(FileInfo fileInfo) {
            this.fileInfo = fileInfo;
        }

        @Override
        protected SoftReference<Bitmap> doInBackground(Void... params) {
            String path = fileInfo.getPath();
//            Bitmap bitmap = BitmapFactory.decodeFile(path);
            SoftReference<Bitmap> bitmap = new SoftReference<Bitmap>(BitmapUtil.decodeSampledBitmapFromFilePath(path, 200, 200));

            return bitmap;
        }

        @Override
        protected void onPostExecute(SoftReference<Bitmap> bitmap) {

            fileInfo.setBitmap(bitmap);
            notifyDataSetChanged();
        }
    }

    public List<FileInfo> getData() {
        return list;
    }

}
