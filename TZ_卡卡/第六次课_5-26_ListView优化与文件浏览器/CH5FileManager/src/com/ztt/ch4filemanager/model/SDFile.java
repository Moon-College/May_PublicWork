package com.ztt.ch4filemanager.model;

import java.io.File;

import android.graphics.Bitmap;

/***
 * ÎÄ¼þÀà
 * @author zhangtengteng
 *
 */
public class SDFile
{
    private String name;
    private String path;
    private File file;
    private Bitmap bitmap;
    private boolean isPic;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getPath()
    {
        return path;
    }
    public void setPath(String path)
    {
        this.path = path;
    }
    public File getFile()
    {
        return file;
    }
    public void setFile(File file)
    {
        this.file = file;
    }
    public Bitmap getBitmap()
    {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }
    public boolean isPic()
    {
        return isPic;
    }
    public void setPic(boolean isPic)
    {
        this.isPic = isPic;
    }
}
