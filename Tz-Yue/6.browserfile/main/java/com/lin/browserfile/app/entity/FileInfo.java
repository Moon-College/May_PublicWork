package com.lin.browserfile.app.entity;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by Administrator on 2015/5/31.
 * 文件实体类
 */
public class FileInfo {
    public static final int TYPE_DIRECTORY = 0x01; //文件夹
    public static final int TYPE_OTHER_FILE = 0x02; //文件
    public static final int TYPE_PIC = 0x03; //图片
    private String name;
    private String path;
    private Bitmap bitmap;
    private int fileType;
    private File file;

    public FileInfo() {
    }

    public FileInfo(String name, String path, int fileType, File file) {
        this.name = name;
        this.path = path;
        this.fileType = fileType;
        this.file = file;
    }

    public FileInfo(String name, String path, Bitmap bitmap, int fileType, File file) {
        this.name = name;
        this.path = path;
        this.bitmap = bitmap;
        this.fileType = fileType;
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }
}
