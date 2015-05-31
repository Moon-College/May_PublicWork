package com.zjm.filesearch.util;

import android.graphics.BitmapFactory;

public class ImgUtil {
	
	
	
	/**
	 * 源码提供的动态计算inSampleSize
	 *@author Json 
	 *@param options
	 *@param minSideLength  最小边长
	 *@param maxNumOfPixels 最大像素
	 *@return
	 * */
	public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
	    int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
	    int roundedSize;
	    if (initialSize <= 8) {
	        roundedSize = 1;
	        while (roundedSize < initialSize) {
	            roundedSize <<= 1;
	        }
	    } else {
	        roundedSize = (initialSize + 7) / 8 * 8;
	    }
	    return roundedSize;
	}
	
	/**
	 * @author Json
	 *@param options
	 *@param minSideLength  最小边长
	 *@param maxNumOfPixels 最大像素
	 *@return
	 * */
	private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
	    double w = options.outWidth;
	    double h = options.outHeight;
	    //下界 w*h/最大像素   的平方根  向上取整
	    int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
	    //上界 
	    int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
	    
	    //
	    if (upperBound < lowerBound) {
	        // return the larger one when there is no overlapping zone.
	        return lowerBound;
	    }
	    if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
	        return 1;
	    } else if (minSideLength == -1) {
	        return lowerBound;
	    } else {
	        return upperBound;
	    }
	}

}
