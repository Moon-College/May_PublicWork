package com.tz.filebrower.bean;

import android.content.Context;
import android.graphics.Bitmap;
import com.tz.filebrower.MainActivity;
import com.tz.filebrower.R;
import com.tz.filebrower.task.CallBack;
import com.tz.filebrower.task.GetDirFileBitmapTask;

import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/28.
 */
public class MyDirFile extends MyFile {
    public MyDirFile(String mName, String mFilePath) {
        super(mName, mFilePath);
    }

    @Override
    public void open(Context context) {
        MainActivity activity = (MainActivity) context;
        ((MainActivity) context).openFile(mFilePath);
    }

    @Override
    public void executeBitmapTask(CallBack callBack, WeakReference<Context> context) {
        GetDirFileBitmapTask task = new GetDirFileBitmapTask(callBack, context);
        task.execute(R.drawable.dirs + "");
    }

}
