package com.tz.download;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    private String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    private int maxLength = 0;

    private int currentLength = 0;

    private ImageView iv;

    private TextView tv,tv_file;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        iv = (ImageView) findViewById(R.id.iv);

        tv = (TextView) findViewById(R.id.tv);

        tv_file = (TextView) findViewById(R.id.tv_file);

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_image:
                String url = "http://s1.dwstatic.com/group1/M00/B1/C5/bb713d0002a4f5b6cff470908dded3df.jpg";
                ImageDownloadTask task = new ImageDownloadTask();
                task.execute(url);
                break;
            case R.id.btn_apk:
                String fileUrl = "http://sqdd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk";
                APKDownloadTask apkDownloadTask = new APKDownloadTask();
                apkDownloadTask.execute(fileUrl);
                break;
        }
    }

    private class ImageDownloadTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            maxLength = 0;
            currentLength = 0;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            int len = (Integer) values[0];
            currentLength += len;
            tv.setText("当前进度:" +currentLength*100/maxLength+"%");
        }

        @Override
        protected Object doInBackground(Object[] params) {
            String url = (String) params[0];
            Bitmap bitmap = null;
            try {
                bitmap = download(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        private Bitmap download(String imageUrl) throws IOException {
            Bitmap bitmap = null;
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                maxLength = connection.getContentLength();
                byte[] buffer = new byte[256];
                int len = 0;
                String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"));
                FileOutputStream fileOutputStream = new FileOutputStream(ROOT_PATH + fileName);
                while ((len = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    publishProgress(len);
                }
                fileOutputStream.close();
                bitmap = BitmapFactory.decodeFile(ROOT_PATH + fileName);
            }
            return bitmap;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Object o) {
            iv.setImageBitmap((Bitmap) o);
            super.onPostExecute(o);
        }
    }

    private class APKDownloadTask extends AsyncTask {

        private long fileLength;

        private long currentLength;

        @Override
        protected Object doInBackground(Object[] params) {
            String url = (String) params[0];
            String path = null;
            try {
                path = downloadApk(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return path;
        }

        private String downloadApk(String fileUrl) throws IOException {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                fileLength = connection.getContentLength();
                byte[] buffer = new byte[1024];
                int len = 0;
                String fileName = fileUrl.substring(fileUrl.lastIndexOf("/"));
                String path = ROOT_PATH + fileName;
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                while ((len = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    publishProgress(len);
                }
                fileOutputStream.close();
                return path;
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            String path = (String) o;
            if (!TextUtils.isEmpty(path)) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
                startActivity(intent);
            }
        }

        @Override
        protected void onPreExecute() {
            fileLength = 0;
            currentLength = 0;
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            int len = (Integer) values[0];
            currentLength += len;
            tv_file.setText("当前进度:" + currentLength * 100 / fileLength + "%");
        }
    }

}
