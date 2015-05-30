package com.tz.filebrower.bean;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import com.tz.filebrower.task.CallBack;
import com.tz.filebrower.task.GetImageFileBitmapTask;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/28.
 */
public class MyImageFile extends MyFile {
    public MyImageFile(String mName, String mFilePath) {
        super(mName, mFilePath);
    }

    @Override
    public void open(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(mFilePath)), "image/*");
        context.startActivity(intent);
    }


    @Override
    public void executeBitmapTask(CallBack callBack, WeakReference<Context> context) {
        GetImageFileBitmapTask task = new GetImageFileBitmapTask(callBack, context);
        task.execute(mFilePath);
    }


}
