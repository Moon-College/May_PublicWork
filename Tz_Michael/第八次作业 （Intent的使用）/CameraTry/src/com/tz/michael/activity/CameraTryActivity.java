package com.tz.michael.activity;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.tz.michael.bean.Person;
import com.tz.michael.utils.BitmapUtils;
import com.tz.michael.utils.FileUtils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.MediaStore.Video;
import android.provider.MediaStore.Video.VideoColumns;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class CameraTryActivity extends Activity {
	
	/**拍照*/
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 110;
	/**录像*/
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private Uri fileUri;
	private int CAMERA_REQUESTCODE=203;
	private List<Person> ll=new ArrayList<Person>();
	
	ImageView img;
	VideoView vedio;
	
	private String filePath="";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        img=(ImageView) findViewById(R.id.img);
        vedio=(VideoView) findViewById(R.id.vedio);
        initData();
    }
    
    /**
     * 初始化一些数据
     */
    private void initData() {
		for(int i=0;i<5;i++){
			Person p=new Person();
			p.setName("name"+(i+1));
			p.setInterest("interest"+(i+1));
			p.setAge(20+i);
			ll.add(p);
		}
	}

	public void myClick(View v){
    	switch (v.getId()) {
		case R.id.btn_image:
			captureImage();
			break;
		case R.id.btn_vedio:
			captureVideo();
			break;
		case R.id.btn_jump:
			Intent intent=new Intent();
			intent.setAction("com.tz.michael.second");
			intent.putParcelableArrayListExtra("pp", (ArrayList<? extends Parcelable>) ll);
			startActivityForResult(intent, CAMERA_REQUESTCODE);
			break;
		case R.id.vedio:
			if(filePath.equals("")){
				
			}else{
				Intent intents = new Intent(Intent.ACTION_VIEW);
				intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intents.setDataAndType(Uri.fromFile(new File(filePath)), "video/*");
				startActivity(intents);
			}
			break;
		default:
			break;
		}
    }
    
    /**
     * 拍照
     */
    public void captureImage(){
    	Intent intent_capture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    	//这个判断是很重要的，他可以防止在没有找到符合条件的app时，你的app不会crash，
    	if(intent_capture.resolveActivity(getPackageManager()) != null){
    		fileUri = FileUtils.getOutputMediaFileUri(FileUtils.MEDIA_TYPE_IMAGE); // create a file to save the image
        	intent_capture.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
        	startActivityForResult(intent_capture,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    	}
    }
    
    /**
     * 录像
     */
	public void captureVideo(){
    	//create new Intent
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
       if(intent.resolveActivity(getPackageManager()) != null){
    	   fileUri = FileUtils.getOutputMediaFileUri(FileUtils.MEDIA_TYPE_VIDEO);  // create a file to save the video
           intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
           intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high
           // start the Video Capture Intent
           startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
       }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if(data!=null){
                	// Image captured and saved to fileUri specified in the Intent
                    Toast.makeText(this, "Image saved to:\n" +data.getData(), Toast.LENGTH_LONG).show();
                    try {
                    	img.setImageURI(data.getData());
					} catch (Exception e) {
						Bundle extras = data.getExtras();
						Bitmap imageBitmap = (Bitmap) extras.get("data");
						img.setImageBitmap(imageBitmap);
					}
                }else{
                	img.setImageURI(fileUri);
                }
                //将图片添加到相册
                BitmapUtils.galleryAddPic(this,fileUri);
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    	//录像这块不是很完善，学完内容提供者，再来完善
    	if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if(data!=null){
                	// Video captured and saved to fileUri specified in the Intent
                    Toast.makeText(this, "Video saved to:\n" +data.getData(), Toast.LENGTH_LONG).show();
                    vedio.setVideoURI(data.getData());
                }else{
                	vedio.setVideoURI(fileUri);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the video capture
            } else {
                // Video capture failed, advise user
            }
        }
    	if(requestCode==CAMERA_REQUESTCODE){
    		if(resultCode == RESULT_OK){
    			String s=data.getParcelableArrayListExtra("pp1").toString();
    			Toast.makeText(this, s, 0).show();
    		}
    	}
    } 
}