package com.tz.contentprovider.db;

import android.content.ContentProviderResult;
import android.content.Context;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;

/**
 * Created by qinhan on 15/7/15.
 */
public abstract class MyDB {
    protected Context mContext;

    public MyDB(Context mContext) {
        this.mContext = mContext;
    }

    public abstract Object query(String index) throws NoSuchFieldException, IllegalAccessException;

    public abstract ContentProviderResult[] insertData(Object object) throws IllegalAccessException, RemoteException, OperationApplicationException;

    public abstract ContentProviderResult[] updateData(String data,String where,String args[]) throws RemoteException, OperationApplicationException;
}
