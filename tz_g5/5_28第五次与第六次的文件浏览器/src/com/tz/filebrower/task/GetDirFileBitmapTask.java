package com.tz.filebrower.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/30.
 */
public class GetDirFileBitmapTask extends GetFileBitmapTask {

    public GetDirFileBitmapTask(CallBack callBack, WeakReference<Context> context) {
        super(callBack, context);
    }

    @Override
    protected Bitmap getBitmap(String path) {
        int resId=0;
        try {
            resId=Integer.parseInt(path);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        if (context.get()==null)return null;
        return BitmapFactory.decodeResource(context.get().getResources(), resId);
    }
}
