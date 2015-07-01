package com.lin.mytzwork;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lin.mytzwork.util.ReflectUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button bt;
    private TextView tv;
    private ProgressBar pregressbar;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        ReflectUtil.initView(this);
        initView();
    }

    private void initView() {
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        downTask downTask = new downTask();
        downTask.execute("http://www.apk3.com/uploads/soft/201504/ftt2pzev0cg.apk");

    }


    class downTask extends AsyncTask<String, Integer, File> {

        private int contentLength;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv.setText("开始");
        }

        @Override
        protected File doInBackground(String... params) {
            File file = null;
            try {
                file = downImage(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            Integer value = values[0];
            pregressbar.setProgress(pregressbar.getProgress() + value);


            String text = "当前进度:" + (pregressbar.getProgress() * 100 / contentLength) + "%";
            tv.setText(text);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(File file) {


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


            super.onPostExecute(file);
        }


        private File downImage(String path) throws IOException {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), System.currentTimeMillis() + ".apk");
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(6 * 1000);
            connection.setReadTimeout(6 * 1000);
            if (connection.getResponseCode() == 200) {
                contentLength = connection.getContentLength();
                pregressbar.setMax(contentLength);
                pregressbar.setProgress(0);

                FileOutputStream fos = new FileOutputStream(file);

                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = bis.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                    this.publishProgress(len);
                }

                fos.close();
                bis.close();
                inputStream.close();
            }
            return file;

        }
    }


}
