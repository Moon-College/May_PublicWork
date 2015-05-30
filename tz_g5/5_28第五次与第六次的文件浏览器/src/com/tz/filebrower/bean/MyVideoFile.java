package com.tz.filebrower.bean;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import com.tz.filebrower.task.CallBack;
import com.tz.filebrower.task.GetVideoFileBitmapTask;

import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/28.
 */
public class MyVideoFile extends MyFile {
    public MyVideoFile(String mName, String mFilePath) {
        super(mName, mFilePath);
    }

    @Override
    public void open(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(mFilePath), "video/mp4");
        context.startActivity(intent);
    }


    @Override
    public void executeBitmapTask(CallBack callBack, WeakReference<Context> context) {
        GetVideoFileBitmapTask task = new GetVideoFileBitmapTask(callBack, context);
        task.execute(mFilePath);
    }
}
