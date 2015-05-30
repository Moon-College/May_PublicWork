package com.tz.filebrower.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/30.
 */
public class GetImageFileBitmapTask extends GetFileBitmapTask {


    private static final int LIMIT_WIDTH = 200;
    private static final int LIMIT_HEIGHT = 300;

    public GetImageFileBitmapTask(CallBack callBack, WeakReference<Context> context) {
        super(callBack, context);
    }

    @Override
    public Bitmap getBitmap(String path) {
        return scaleBitmap(path);
    }

    public Bitmap scaleBitmap(String path){
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//
        bitmap = BitmapFactory.decodeFile(path,options);
        int width = options.outWidth;
        int height = options.outHeight;
        if(width>LIMIT_WIDTH&&height>LIMIT_HEIGHT){
            if(width>height){
                options.inSampleSize = (width/LIMIT_HEIGHT + height/LIMIT_WIDTH)/2;
            }else{
                options.inSampleSize = (width/LIMIT_WIDTH + height/LIMIT_HEIGHT)/2;
            }
        }
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }
}
