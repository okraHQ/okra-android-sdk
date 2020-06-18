package com.okra.widget.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.okra.widget.models.SmsData;

import java.util.ArrayList;
import java.util.List;

public class MessageUtils {

    public List<SmsData> getUsersSms(Context context){
        List<SmsData> smsDataList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(context, "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {

            Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        SmsData smsData = new SmsData();
                        smsData.set_id(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
                        smsData.setThread_id(cursor.getString(cursor.getColumnIndexOrThrow("thread_id")));
                        smsData.setAddress(cursor.getString(cursor.getColumnIndexOrThrow("address")));
                        smsData.setPerson(cursor.getString(cursor.getColumnIndexOrThrow("person")));
                        smsData.setDate(cursor.getString(cursor.getColumnIndexOrThrow("date")));
                        smsData.setDate_sent(cursor.getString(cursor.getColumnIndexOrThrow("date_sent")));
                        smsData.setProtocol(cursor.getInt(cursor.getColumnIndexOrThrow("protocol")));
                        smsData.setBody(cursor.getString(cursor.getColumnIndexOrThrow("body")));
                        smsData.setCreator(cursor.getString(cursor.getColumnIndexOrThrow("creator")));

                        smsDataList.add(smsData);
                    } while (cursor.moveToNext());
                }
            }
        }else{
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_SMS}, 123);
        }
        return smsDataList;
    }
}
