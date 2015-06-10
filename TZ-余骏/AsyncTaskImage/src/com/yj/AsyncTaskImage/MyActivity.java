package com.yj.AsyncTaskImage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyActivity extends Activity {

    private ImageView iv_icon;
    private TextView tv_calibration;
    private ProgressBar progressBar;
    private Button bt_apk;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView(this);
        bt_apk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getAPK();
			}
		});
    }


    public void sendImage(View v){
        ImageAsyncTask mAsyncTask = new ImageAsyncTask(progressBar,iv_icon,tv_calibration);
        mAsyncTask.execute("http://img5.duitang.com/uploads/item/201402/08/20140208122906_ah24T.thumb.700_0.jpeg");
    }
    
    public void getAPK(){
    	
    	
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				//判断sdcard是否存在
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					try {
						new APKAsyncTask(progressBar, tv_calibration, MyActivity.this).execute("http://www.laolai.com/download/LaoLaiWangAndroidClient.apk");
					} catch (Exception e) {//下载失败
						// TODO: handle exception
						Log.e("error", e.getMessage());
					}
				}else{//SDK不存在 安装失败
					Log.e("error", "SDK不存在");
				}
			}
		}).start();
	}
    


	private void initView(Object obj) {

        Class<?> cls = obj.getClass();

        Field[] fields = cls.getDeclaredFields();

        for (Field filed : fields){

            if (View.class.isAssignableFrom(filed.getType())){

                try {
                    Method mMethod = cls.getMethod("findViewById",new Class[]{int.class});

                    Class<?> idCls = R.id.class;

                    Field idField = idCls.getDeclaredField(filed.getName());
                    Object id = idField.get(idField);

                    Object value = mMethod.invoke(obj, new Object[]{id});

                    filed.setAccessible(true);
                    filed.set(obj,value);
                } catch (Exception e) {
                    e.getMessage();
                }


            }


        }
    }
}
