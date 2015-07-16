package com.tz.contentprovider.db;

import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import com.tz.contentprovider.pojo.ContactsInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinhan on 15/7/15.
 */
public class MyContactDB extends MyDB{
    public MyContactDB(Context mContext) {
        super(mContext);
    }

    @Override
    public Object query(String index) throws  IllegalAccessException {
        List<ContactsInfo> data = new ArrayList<ContactsInfo>();
        ContentResolver cr = mContext.getContentResolver();//获取内容接收者
        Uri uri_raw_contacts = Uri.parse("content://" + ContactsContract.AUTHORITY + "/raw_contacts");
        Cursor row = cr.query(uri_raw_contacts, new String[]{"_id", "display_name"}, null, null, null);
        while(row.moveToNext()){
            ContactsInfo info = new ContactsInfo();
            int _id = row.getInt(row.getColumnIndex("_id"));
            Uri uri_data = ContactsContract.AUTHORITY_URI;
            uri_data = Uri.withAppendedPath(uri_data, "data");
            Cursor line = cr.query(uri_data, null, "raw_contact_id = ?", new String[]{String.valueOf(_id)}, null);
            while(line.moveToNext()){
                String data1 = line.getString(line.getColumnIndex("data1"));
                String mimeType = line.getString(line.getColumnIndex("mimeType"));
                String filedName = mimeType.substring(mimeType.lastIndexOf("/") + 1);
                Field field = null;
                try {
                    field = info.getClass().getField(filedName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    continue;
                }
                field.setAccessible(true);
                field.set(info, data1);
            }
            data.add(info);
        }

        for(ContactsInfo contactInfo:data){
            Log.i("INFO", "name:"+contactInfo.name);
            Log.i("INFO", "number:" + contactInfo.phone_v2);
        }
        return data;
    }

    @Override
    public ContentProviderResult[] insertData(Object object) throws IllegalAccessException, RemoteException, OperationApplicationException {
        ContactsInfo info = (ContactsInfo) object;
        ContentResolver cr = mContext.getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        Uri uri_raw_contacts = Uri.parse("content://"+ ContactsContract.AUTHORITY+"/raw_contacts");
        ContentValues values_raw = new ContentValues();
        ContentProviderOperation insertRaw = ContentProviderOperation.newInsert(uri_raw_contacts).withValues(values_raw).build();
        operations.add(insertRaw);
        Class mClass = info.getClass();
        Field fields[] = mClass.getDeclaredFields();
        Uri uri_data = ContactsContract.AUTHORITY_URI;
        uri_data = Uri.withAppendedPath(uri_data, "data");
        ContentValues values = new ContentValues();
        for (Field field :fields) {
            values.put("data1", (String) field.get(info));
            values.put("mimetype","vnd.android.cursor.item/"+field.getName());
            ContentProviderOperation insertData = ContentProviderOperation.newInsert(uri_data)
                    .withValueBackReference("raw_contact_id", 0).withValues(values).build();
            operations.add(insertData);
            values.clear();
        }
        return cr.applyBatch(ContactsContract.AUTHORITY, operations);
    }

    @Override
    public ContentProviderResult[] updateData(String data,String where, String args[]) throws RemoteException, OperationApplicationException {
        ContentResolver cr = mContext.getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        ContentValues values = new ContentValues();
        values.put("data1", data);
        ContentProviderOperation update = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection(where, args).withValues(values).build();
        operations.add(update);
        return cr.applyBatch(ContactsContract.AUTHORITY, operations);
    }

}
