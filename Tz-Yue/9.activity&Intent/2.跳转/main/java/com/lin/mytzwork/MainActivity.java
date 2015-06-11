package com.lin.mytzwork;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.lin.mytzwork.utils.ReflectUtil;


public class MainActivity extends Activity implements View.OnClickListener {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;

    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReflectUtil.initView(this);
        tv.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction("com.lin.OtherActivity");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addCategory("123456");
        startActivity(intent);

    }


}
