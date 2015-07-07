package com.chris.flapybird.spirit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class SpiritBird extends MySpirit
{
	protected static final String tag = "SpiritBird";

	public SpiritBird(Context context, Bitmap[] bitmaps, int x, int y)
	{
		super(context, bitmaps, x, y);
	}

	public SpiritBird(Context context, Bitmap[] bitmaps)
	{
		super(context, bitmaps);
	}

	public SpiritBird(Context context)
	{
		super(context);
	}

	@Override
	public int startDrawFrame(Canvas canvas, int startIndex, int flushTime)
	{
		mCanvas = canvas;
		if (null != mBitmaps[startIndex])
		{
			canvas.drawBitmap(mBitmaps[startIndex], left, top, null);
		} else
		{
			Log.e(tag, "draw bitmaps[" + startIndex + "] failed of null bitmap");
		}
		return startIndex + 1;
	}

}
