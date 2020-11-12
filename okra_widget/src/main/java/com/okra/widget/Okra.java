package com.okra.widget;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.okra.widget.activity.OkraWebActivity;
import com.okra.widget.models.OkraOptions;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.Serializable;
import java.util.Map;

public class Okra {

    @SuppressLint("StaticFieldLeak")
    public static Context baseContext;

    public static void create(Context context, Map<String, Object> okraOptions){
        baseContext = context;
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        int REQUEST_READ_PHONE_STATE = 1;
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        }else {
            if (context instanceof Activity) {
                Intent intent = new Intent(context, OkraWebActivity.class);
                intent.putExtra("okraOptions", (Serializable) okraOptions);
                ((Activity) context).startActivityForResult(intent, REQUEST_READ_PHONE_STATE);
            }
        }
    }
}