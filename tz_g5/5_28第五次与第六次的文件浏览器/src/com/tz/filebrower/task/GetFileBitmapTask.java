package com.tz.filebrower.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/30.
 */
public abstract class GetFileBitmapTask extends AsyncTask<String, Void, Bitmap> {
    private CallBack callBack;
    protected WeakReference<Context> context;

    public GetFileBitmapTask(CallBack callBack, WeakReference<Context> context) {
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return getBitmap(params[0]);
    }

    protected abstract Bitmap getBitmap(String path);

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        callBack.callback(bitmap);
        super.onPostExecute(bitmap);
    }
}
