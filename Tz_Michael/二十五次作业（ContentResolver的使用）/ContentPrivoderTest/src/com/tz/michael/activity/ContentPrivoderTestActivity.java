package com.tz.michael.activity;

import java.util.List;

import org.apache.http.client.utils.URIUtils;

import com.tz.michael.bean.ContactInfo;
import com.tz.michael.utils.ContentResolverUtils;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

public class ContentPrivoderTestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        List<Object> ll=ContentResolverUtils.queryData(this, ContactInfo.class);
//        for(Object object:ll){
//        	ContactInfo contactInfo=(ContactInfo) object;
//        	Log.i("contact--", contactInfo.toString());
//        }
        List<ContactInfo> ll=ContentResolverUtils.queryData(this);
        for(ContactInfo contactInfo:ll){
        	Log.i("ccinfo--", contactInfo.toString());
        }
        
        ContactInfo contactInfo=new ContactInfo("dnf", "78978989", "@qq.com");
        ContentResolverUtils.remoteInsertData(this, contactInfo);
    }
}