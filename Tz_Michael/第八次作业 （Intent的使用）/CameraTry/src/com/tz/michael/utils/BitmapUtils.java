package com.tz.michael.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore.Video;
import android.widget.ImageView;

public class BitmapUtils {

	/**
	 * 设置合适的图片
	 * @param mImageView 要设置的 view
	 * @param mCurrentPhotoPath 图片的路径
	 */
	private void setPic(ImageView mImageView,String mCurrentPhotoPath) {
	    // Get the dimensions of the View
	    int targetW = mImageView.getWidth();
	    int targetH = mImageView.getHeight();

	    // Get the dimensions of the bitmap
	    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
	    bmOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
	    int photoW = bmOptions.outWidth;
	    int photoH = bmOptions.outHeight;

	    // Determine how much to scale down the image
	    int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

	    // Decode the image file into a Bitmap sized to fill the View
	    bmOptions.inJustDecodeBounds = false;
	    bmOptions.inSampleSize = scaleFactor;
	    bmOptions.inPurgeable = true;

	    Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
	    mImageView.setImageBitmap(bitmap);
	}
	
	/**
	 * Add the Photo to a Gallery
	 * @param uri
	 */
	public static void galleryAddPic(Context context,Uri uri) {
	    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
	    mediaScanIntent.setData(uri);
	    context.sendBroadcast(mediaScanIntent);
	}
	
	/**
	 * 获得视频的缩略图
	 * 
	 * @param mUri
	 */
	public static Bitmap getBitmap(String imgPath) {

		if (getAndroidSDKVersion() >= 8) {
			Bitmap bp = ThumbnailUtils.createVideoThumbnail(imgPath,
					Video.Thumbnails.MINI_KIND);
			return bp;
		} else {
			return null;
		}
	}
	
	/**
	 * 当前系统的版本号
	 */
	public static int getAndroidSDKVersion() {
		int version;
		try {
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
			return -1;
		}
		return version;
	}
	
}
