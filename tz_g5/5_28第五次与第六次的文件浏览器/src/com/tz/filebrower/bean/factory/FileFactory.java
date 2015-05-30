package com.tz.filebrower.bean.factory;

import android.content.Context;
import android.util.Log;
import com.tz.filebrower.bean.*;
import com.tz.filebrower.task.CallBack;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/28.
 */
public class FileFactory {

    private static final String IMAGE_FILE_SUFFIX [] = {".png",".jpg"};
    private static final String MUSIC_FILE_SUFFIX []={".mp3",".mqcc",".lrc"} ;
    private static final String VIDEO_FILE_SUFFIX []= {".mp4",".avi",".wmv"};

    public static MyFile generateFile(File file) {
        MyFile myFile = null;
        if(file.isDirectory()) {
            myFile = new MyDirFile(file.getName(), file.getAbsolutePath());
            Log.v("Dir", "Dir");
        }else if(isMatchSuffix(file.getName(), IMAGE_FILE_SUFFIX)) {
            myFile = new MyImageFile(file.getName(), file.getAbsolutePath());
            Log.v("img", "img");
        }else if (isMatchSuffix(file.getName(),MUSIC_FILE_SUFFIX)) {
            myFile = new MyMusicFile(file.getName(), file.getAbsolutePath());
            Log.v("MUSIC", "MUSIC");
        }else if (isMatchSuffix(file.getName(),VIDEO_FILE_SUFFIX)) {
            myFile = new MyVideoFile(file.getName(), file.getAbsolutePath());
            Log.v("Video", "Video");
        } else {
            myFile=new MyFile(file.getName(),file.getAbsolutePath()) {
                @Override
                public void open(Context context) {

                }

                @Override
                public void executeBitmapTask(CallBack callBack, WeakReference<Context> context) {

                }
            };
        }
        return myFile;
    }


    private static boolean isMatchSuffix(String fileName,String suffixs[]) {
        for (String suffix :suffixs) {
            if (fileName.toLowerCase().endsWith(suffix)) return true;
        }
        return false;
    }
}
