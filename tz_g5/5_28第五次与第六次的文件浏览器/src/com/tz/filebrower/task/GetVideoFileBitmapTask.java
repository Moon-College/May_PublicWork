package com.tz.filebrower.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;

import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/30.
 */
public class GetVideoFileBitmapTask extends GetFileBitmapTask {

    public GetVideoFileBitmapTask(CallBack callBack, WeakReference<Context> context) {
        super(callBack, context);
    }

    @Override
    public Bitmap getBitmap(String path) {
        Bitmap bitmap = null;
        bitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.MICRO_KIND);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, 100, 100,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }
}
