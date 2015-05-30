package com.tz.filebrower.task;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by qinhan on 15/5/30.
 */
public class GetMusicFileBitmapTask extends GetFileBitmapTask {

    public GetMusicFileBitmapTask(CallBack callBack, WeakReference<Context> context) {
        super(callBack, context);
    }

    @Override
    public Bitmap getBitmap(String path) {

        return getImage(path);
    }

    private Bitmap getImage(String path) {
        Cursor currentCursor = getCursor(path);
        int album_id = 0;
        try {
            album_id = currentCursor.getInt(currentCursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
        String albumArt = getAlbumArt(album_id);
        if (TextUtils.isEmpty(albumArt)) return null;
        return BitmapFactory.decodeFile(albumArt);
    }

    private Cursor getCursor(String filePath) {
        String path = null;
        Cursor c = context.get().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        if (c.moveToFirst()) {
            do {
                path = c.getString(c
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                if (path.equals(filePath)) {
                    break;
                }
            } while (c.moveToNext());
        }
        return c;
    }

    private String getAlbumArt(int album_id) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        Cursor cur = context.get().getContentResolver().query(
                Uri.parse(mUriAlbums + "/" + Integer.toString(album_id)),
                projection, null, null, null);
        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
        cur = null;
        return album_art;
    }
}
