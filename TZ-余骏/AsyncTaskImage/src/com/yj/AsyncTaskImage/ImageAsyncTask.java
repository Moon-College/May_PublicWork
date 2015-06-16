package com.yj.AsyncTaskImage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author A18ccms A18ccms_gmail_com
 * @version V1.0
 * @Title: ${FILE_NAME}
 * @Package com.yj.AsyncTaskImage
 * @date 15/6/10 10:25
 * @user YuJun
 */
public class ImageAsyncTask extends AsyncTask<String,Integer,Bitmap>
{
    private ProgressBar progressBar;
    private ImageView view;
    private long total_length = 0;
	private TextView textView;
	private long file_length;

    public ImageAsyncTask(ProgressBar progressBar,ImageView view,TextView textView) {
        this.progressBar = progressBar;
        this.view = view;
		this.textView = textView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(strings[0]);
        Bitmap bitmap = null;
        ByteArrayOutputStream baos =null;
        InputStream inputStream =null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                inputStream = response.getEntity().getContent();
                int len = 0;
                byte[] bytes = new byte[1024];
                baos = new ByteArrayOutputStream();
                file_length = response.getEntity().getContentLength();
                progressBar.setMax((int)file_length);
                while ((len = inputStream.read(bytes)) != -1){
                    total_length += len;
                    baos.write(bytes,0,len);
                    publishProgress(len);
                }

            }
            bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(),0,baos.toByteArray().length);
            inputStream.close();
            baos.close();
        } catch (Exception e) {
            e.getMessage();
        }finally {
            try {
                inputStream.close();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null){
            view.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(progressBar.getProgress()+values[0]);
        textView.setText(String.valueOf((int)(total_length*100/(float)file_length))+"%");
    }
}
