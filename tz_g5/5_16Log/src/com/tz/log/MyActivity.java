package com.tz.log;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.tz.log.util.LogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_log:
                try {
                    collectLog();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void collectLog() throws IOException{
        LogUtil.printLog("INFO", "start connectLog");
        StringBuffer sb = new StringBuffer();
        ArrayList<String> cmdLine = new ArrayList<String>();
        cmdLine.add("logcat");
        cmdLine.add("-d");
//        cmdLine.add("-s");
//        cmdLine.add("INFO");
        cmdLine.add("*:W");
        Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));

        InputStream inputStream = exec.getInputStream();
        InputStreamReader buInputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(buInputStreamReader);
        String str = null;
        while((str = bufferedReader.readLine())!=null){
            sb.append(str);
            sb.append("\n");
        }

        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
    }
}
