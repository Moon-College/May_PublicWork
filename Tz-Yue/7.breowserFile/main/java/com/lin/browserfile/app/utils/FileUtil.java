package com.lin.browserfile.app.utils;

import android.os.Environment;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Administrator on 2015/5/31.
 */
public class FileUtil {
    /**
     * 获得SD卡路径
     *
     * @return
     */
    public static String getSdPath() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }




}
