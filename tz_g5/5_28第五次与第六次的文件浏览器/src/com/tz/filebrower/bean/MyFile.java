package com.tz.filebrower.bean;

import android.content.Context;
import android.graphics.Bitmap;
import com.tz.filebrower.task.CallBack;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/28.
 */
public abstract class MyFile {
    protected String mName;
    protected String mFilePath;
    protected SoftReference<Bitmap> mBitmap;
    protected boolean isExecuting = false;

    public MyFile(String mName, String mFilePath) {
        this.mName = mName;
        this.mFilePath = mFilePath;
    }

    public String getName() {
        return mName;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public Bitmap getmBitmap() {
        if (mBitmap==null) return null;
        return mBitmap.get();
    }

    public boolean isExecuting() {
        return isExecuting;
    }

    public void setIsExecuting(boolean isExecuting) {
        this.isExecuting = isExecuting;
    }

    public void setmBitmap(SoftReference<Bitmap> mBitmap) {
        this.mBitmap = mBitmap;
    }

    public abstract void open(Context context);

    public abstract void executeBitmapTask(CallBack callBack, WeakReference<Context> context);
}
