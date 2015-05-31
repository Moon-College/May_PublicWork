package com.lin.browserfile.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.lin.browserfile.app.adapter.FileAdapter;
import com.lin.browserfile.app.entity.FileInfo;
import com.lin.browserfile.app.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private Activity activity;
    private ListView lv;

    private FileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = this;
        setContentView(R.layout.activity_main);
        lv = (ListView) this.findViewById(R.id.lv);

        String path = FileUtil.getSdPath();
        List<FileInfo> list = getFiles(path);
        adapter = new FileAdapter(activity, list);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileInfo item = adapter.getItem(position);
                if (item.getFileType() != FileInfo.TYPE_DIRECTORY) {
                    return;
                }
                if (item.getPath().lastIndexOf(File.separator) != 0) {
                    List<FileInfo> files = getFiles(item.getPath());
                    adapter.upAdapter(files);
                } else {
                    Toast.makeText(activity, "不能向上了！", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    /**
     * 获得一个文件夹的内容
     *
     * @param path
     * @return
     */
    private List<FileInfo> getFiles(String path) {
        List<FileInfo> infos = new ArrayList<FileInfo>();
        FileInfo info = null;


        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                info = new FileInfo();
                File f = files[i];

                info.setFileType(getFileType(f));
                info.setName(f.getName());
                info.setPath(f.getAbsolutePath());
                info.setFile(f);
                if (info.getFileType() == FileInfo.TYPE_DIRECTORY) {
                    infos.add(0, info);
                } else {
                    infos.add(info);
                }
            }
            infos.add(0, getBackFile(path));

            return infos;
        }
        return null;
    }

    /**
     * 获得返回文件夹
     *
     * @param path
     * @return
     */
    private FileInfo getBackFile(String path) {

        String mPath = path.substring(0, path.lastIndexOf(File.separator));
        String name = new String("...");
        int fileType = FileInfo.TYPE_DIRECTORY;
        File file = new File(mPath);

        FileInfo info = new FileInfo(name, mPath, fileType, file);
        return info;
    }

    /**
     * 获得文件类型
     *
     * @param f
     * @return
     */
    private int getFileType(File f) {
        String name = f.getName();
        int fileType = -1;
        if (f.isDirectory()) {
            fileType = FileInfo.TYPE_DIRECTORY;
        } else {
            name = name.toUpperCase();
            if (name.endsWith("PNG") || name.endsWith("JPG")) {
                fileType = FileInfo.TYPE_PIC;
            } else {
                fileType = FileInfo.TYPE_OTHER_FILE;
            }
        }
        return fileType;
    }

    long time = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            List<FileInfo> data = adapter.getData();
            if (!data.isEmpty()) {
                FileInfo item = data.get(0);

                if (item.getPath().lastIndexOf(File.separator) != 0) {
                    List<FileInfo> files = getFiles(item.getPath());
                    adapter.upAdapter(files);
                    return false;
                } else {
                    long l = System.currentTimeMillis();
                    if (l - time > 2 * 1000) {
                        Toast.makeText(activity, "不能向上了！", Toast.LENGTH_LONG).show();
                        time = System.currentTimeMillis();
                        return false;
                    } else {
                        return super.onKeyDown(keyCode, event);
                    }
                }

            }
        }


        return super.onKeyDown(keyCode, event);
    }
}
