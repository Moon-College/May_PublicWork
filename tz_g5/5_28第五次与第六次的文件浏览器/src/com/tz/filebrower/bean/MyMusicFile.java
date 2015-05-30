package com.tz.filebrower.bean;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import com.tz.filebrower.task.CallBack;
import com.tz.filebrower.task.GetMusicFileBitmapTask;

import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/28.
 */
public class MyMusicFile extends MyFile {
    public MyMusicFile(String mName, String mFilePath) {
        super(mName, mFilePath);
    }

    @Override
    public void open(Context context) {
        Intent it = new Intent(Intent.ACTION_VIEW);
        it.setDataAndType(Uri.parse(mFilePath), "audio/MP3");
        context.startActivity(it);
    }


    @Override
    public void executeBitmapTask(CallBack callBack, WeakReference<Context> context) {
        GetMusicFileBitmapTask task = new GetMusicFileBitmapTask(callBack, context);
        task.execute(mFilePath);
    }

}
