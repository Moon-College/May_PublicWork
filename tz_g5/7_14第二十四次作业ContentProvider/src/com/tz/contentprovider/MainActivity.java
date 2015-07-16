package com.tz.contentprovider;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import com.tz.contentprovider.db.MyContactDB;
import com.tz.contentprovider.db.MyDB;
import com.tz.contentprovider.pojo.ContactsInfo;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);



        MyDB db = new MyContactDB(this);

        ContactsInfo info = new ContactsInfo();
        info.email_v2 = "123@qq.com";
        info.name = "G7";
        info.phone_v2 = "13123456789";


        try {
            db.insertData(info);
            String where = ContactsContract.RawContacts.Data.RAW_CONTACT_ID + "=? and " + ContactsContract.RawContacts.Data.MIMETYPE + "=?";
            String[] args = {"1", ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE};
            db.updateData("G6", where, args);
            db.query("");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
