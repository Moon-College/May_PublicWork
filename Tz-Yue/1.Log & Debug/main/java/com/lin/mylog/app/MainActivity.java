package com.lin.mylog.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.lin.mylog.app.utils.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/17.
 */
public class MainActivity extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) this.findViewById(R.id.tv);
        L.i("log", "info");
        L.d("log", "debug");
        L.e("log", "error");
        L.w("log", "warn");


    }


    public void getLog(View v) {
        getRunTimeLog();
    }

    public void getRunTimeLog() {
        tv.setText("");
        L.w("w", "开始抓取");
        List<String> cmdLine = new ArrayList<String>();
        cmdLine.add("logcat");
        cmdLine.add("-d");
//        cmdLine.add("-s");
//        cmdLine.add("log");
        cmdLine.add("*：W");


        try {
            Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
            InputStream inputStream = exec.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\n");
            }
            tv.setText(sb.toString());
            L.d("log", "抓取成功");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            L.d("log", "抓取失败");
        }

        tv.setText("获取日志失败");

    }
}