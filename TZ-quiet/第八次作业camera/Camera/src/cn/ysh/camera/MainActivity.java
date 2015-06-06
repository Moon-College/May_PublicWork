package cn.ysh.camera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	final int CAMERA_CODE=10;
	ImageView image;
	private Uri photoUri;
	private Uri uri;
	private Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        image=(ImageView) findViewById(R.id.image);
        button=(Button) findViewById(R.id.btn);
        button.setOnClickListener(this);
    }

	public void onClick(View v) {
		String fileDir=Environment.getExternalStorageDirectory().getAbsolutePath();
		String fileName = File.separator+"ysh.jpg";
		File file = new File(fileDir, fileName);
		photoUri=Uri.fromFile(file);
		Intent intent=new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
		startActivityForResult(intent, CAMERA_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//Activity.Activity.RESULT_OK值为-1
		if (uri == null) {
			if (photoUri != null) {
				uri = photoUri;
			}
		}
		InputStream input;
		try {
			input = getContentResolver().openInputStream(uri);
			BitmapFactory.Options option=new BitmapFactory.Options();
//			option.inJustDecodeBounds=true;
//			BitmapFactory.decodeStream(input, null, option);
//			//获取屏幕的宽高
//			DisplayMetrics dm = new DisplayMetrics();
//			getWindowManager().getDefaultDisplay().getMetrics(dm);
//			if(option.outHeight > dm.heightPixels){
//				option.inSampleSize=option.outWidth/dm.widthPixels;
//			}else{
//				option.inSampleSize=1;
//			}
			option.inJustDecodeBounds=false;
//			input = getContentResolver().openInputStream(uri);
			Bitmap bitmap = BitmapFactory.decodeStream(input, null, option);
			image.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}