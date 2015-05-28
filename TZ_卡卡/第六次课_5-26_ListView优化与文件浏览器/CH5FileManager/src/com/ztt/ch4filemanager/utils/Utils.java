package com.ztt.ch4filemanager.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.ztt.ch4filemanager.R;
import com.ztt.ch4filemanager.model.SDFile;

public class Utils
{
    private static List<SDFile> fileList = new ArrayList<SDFile>();
    
    /***
     * 根据文件路径获取文件
     * @param path
     * @return
     */
    public static List<SDFile> getFilRoot(Context context, String path)
    {
        fileList.clear();
        File file = new File(path);
        if (file != null)
        {
            //创建返回文件
            SDFile back = new SDFile();
            String backPath = path.substring(0, path.lastIndexOf("/"));
            File backFile = new File(backPath);
            
            back.setName("返回");
            back.setPath(backPath);
            back.setPic(false);
            back.setFile(backFile);
            back.setBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.dirs));
            fileList.add(back);
            // 获取该目录下的文件列表
            File[] listFiles = file.listFiles();
            for (File f : listFiles)
            {
                SDFile sf = new SDFile();
                sf.setName(f.getName());
                sf.setPath(f.getPath());
                sf.setFile(f);
                //如果该文件是一个文件夹
                if (f.isDirectory())
                {
                    sf.setBitmap(BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.dirs));
                }
                //该文件是图片
                else if (f.getName().toLowerCase().endsWith(".png")
                    || f.getName().toLowerCase().endsWith(".jpg"))
                {
                    sf.setPic(true);
                    sf.setBitmap(null);
                    //                    sf.setBitmap(BitmapFactory.decodeFile(f.getPath()));
                }
                // 该文件是普通文件
                else
                {
                    sf.setBitmap(BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.file));
                }
                fileList.add(sf);
                
            }
            
        }
        
        return fileList;
    }
    
    /***
     * 图片压缩
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
        int reqWidth, int reqHeight)
    {
        // Raw height and width of image  
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        
        if (height > reqHeight || width > reqWidth)
        {
            if (width > height)
            {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            }
            else
            {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }
}
