package com.tz.filebrower;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.tz.filebrower.adapter.FileAdapter;
import com.tz.filebrower.bean.MyDirFile;
import com.tz.filebrower.bean.MyFile;
import com.tz.filebrower.bean.factory.FileFactory;
import com.tz.filebrower.constants.MyConstants;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    /**
     * Called when the activity is first created.
     */

    private ListView lv_main;

    private FileAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        lv_main = (ListView) findViewById(R.id.lv_main);

        adapter = new FileAdapter(new ArrayList(), this);
        lv_main.setAdapter(adapter);
        lv_main.setOnItemClickListener(this);

        openFile(MyConstants.ROOT);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MyFile file = (MyFile) adapter.getItem(position);
        file.open(this);
    }

    public void openFile(String path) {
        File file = new File(path);
        if (file == null) {
            Toast.makeText(this, "SD无效", Toast.LENGTH_SHORT).show();
        } else {
            if (file.isDirectory()) {
                adapter.clear();
                ArrayList<MyFile> files = new ArrayList<MyFile>();
                String back_path = path.substring(0, path.lastIndexOf("/"));
                MyDirFile back = new MyDirFile("返回", back_path);
                files.add(back);
                File[] file_list = file.listFiles();
                for (File f : file_list) {
                    MyFile tempFile = FileFactory.generateFile(f);
                    files.add(tempFile);
                }
                adapter.changData(files);
            }
        }
    }
}
