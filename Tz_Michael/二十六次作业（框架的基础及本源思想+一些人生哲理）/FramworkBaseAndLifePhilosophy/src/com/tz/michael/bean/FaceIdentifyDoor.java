package com.tz.michael.bean;

import android.util.Log;

import com.tz.michael.interfaces.FaceIdentifyInterface;
/**
 * faceIdentifyDoor
 * @author michael
 *
 */
public class FaceIdentifyDoor extends Door implements FaceIdentifyInterface{

	@Override
	public void lock() {
		Log.i("FaceIdentifyDoor--", "faceIdentifyDoor unLock");
	}

	@Override
	public void unLock() {
		Log.i("FaceIdentifyDoor--", "faceIdentifyDoor unLock");
	}

	public boolean isFaceLegal(byte[] b) {
		//some operations to decide whether face is legal
		return false;
	}

}
