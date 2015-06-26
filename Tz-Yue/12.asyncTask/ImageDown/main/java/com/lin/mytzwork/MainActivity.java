package com.lin.mytzwork;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
    private ImageView iv;
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
        downTask.execute("http://p.aimm-img.com/uploads/allimg/120412/1-120412100125.jpg");

    }


    class downTask extends AsyncTask<String, Integer, Bitmap> {

        private int contentLength;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                bitmap = downImage(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            Integer value = values[0]; 
            pregressbar.setProgress(pregressbar.getProgress() + value);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            iv.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }


        private Bitmap downImage(String path) throws IOException {
            Bitmap bitmap = null;
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(6 * 1000);
            connection.setReadTimeout(6 * 1000);
            if (connection.getResponseCode() == 200) {
                contentLength = connection.getContentLength();
                pregressbar.setMax(contentLength);

                File file = new File(activity.getCacheDir(), "abc.jpg");
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

                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            }
            return bitmap;

        }
    }


}
