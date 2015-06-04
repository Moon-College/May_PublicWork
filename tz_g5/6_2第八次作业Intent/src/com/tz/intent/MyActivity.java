package com.tz.intent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.tz.intent.bean.Person;

import java.io.File;
import java.io.IOException;

public class MyActivity extends Activity {
    private static final int TAKE_PHOTO = 111;
    private static final int INTENT = 1111;
    private ImageView iv;

    private String mImagePath = Environment.getExternalStorageDirectory()
            + "/TEMP.jpg";

    private final String mIntent = "action.tz.intent";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                iv.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        iv = (ImageView) findViewById(R.id.iv);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv:
                Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photo.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(mImagePath)));
                startActivityForResult(photo, TAKE_PHOTO);
                break;
            case R.id.btn:
                Intent intent = new Intent();
                intent.setAction(mIntent);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(intent,INTENT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PHOTO) {
                try {
                    getBitmap(mImagePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == INTENT) {
                Person p = data.getParcelableExtra("data");
                if (p != null) {
                    Toast.makeText(this,p.getName(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void getBitmap(final String mImagePath) {
        new Thread() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                message.obj = rotateImage(mImagePath);
                mHandler.sendMessage(message);
            }
        }.start();
    }

    private Bitmap rotateImage(String path) {
        int degree = getPicRotationInfo(path);
        Bitmap bitmap = scaleBitmap(path);

        if (degree != -1 && degree != 0) {
            /*
			 * 图片放入暂存Bitmap，接着再利用Bitmap.
			 * createBitmap来创建新的Bitmap对象
			 * 在创建新的Bitmap对象的同时，搭配Matrix对象里的setRotate()方法动态旋转新创建的Bitmap。
			 */
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            // matrix.preRotate(degree);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return resizedBitmap;
        }
        return bitmap;
    }

    /*
	 * 根据图片路径获取
	 */
    private int getPicRotationInfo(String path) {
        int degree = -1;
        try {
			/*
			 * 读取图片Exif信息里面的旋转信息出来
			 */
            ExifInterface exifInterface = new ExifInterface(path);
            degree = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (degree) {
                case ExifInterface.ORIENTATION_NORMAL:
                    degree = 0;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return degree;
    }

    private Bitmap scaleBitmap(String mImagePath) {
        int maxWid = iv.getWidth();
        int maxHei = iv.getHeight();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(mImagePath, options);
        int width = options.outWidth;
        int height = options.outHeight;
        if (width > maxWid && height > maxHei) {
            if (width < height) {
                options.inSampleSize = (width / maxHei + height / maxWid) / 2;
            } else {
                options.inSampleSize = (width / maxWid + height / maxHei) / 2;
            }
        }
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(mImagePath, options);
        return bitmap;
    }
}
