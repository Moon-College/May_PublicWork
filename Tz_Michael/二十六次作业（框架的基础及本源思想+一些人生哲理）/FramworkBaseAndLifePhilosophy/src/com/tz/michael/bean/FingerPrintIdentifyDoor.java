package com.tz.michael.bean;

import android.util.Log;

import com.tz.michael.interfaces.FingerPrintInterface;
/**
 * fingerPrintIdentifyDoor
 * @author michael
 *
 */
public class FingerPrintIdentifyDoor extends Door implements FingerPrintInterface{

	@Override
	public void lock() {
		Log.i("FingerPrintIdentifyDoor--", "fingerPrintIdentifyDoor lock");
	}

	@Override
	public void unLock() {
		Log.i("FingerPrintIdentifyDoor--", "fingerPrintIdentifyDoor unLock");
	}

	public boolean isFingerPrintRight(byte[] b) {
		//some operations to decide whether fingerPrint is legal
		return false;
	}

}
