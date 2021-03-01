package com.okra.widget;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcelable;

import com.okra.widget.activity.OkraWebActivity;
import com.okra.widget.utils.OkraOptions;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.Serializable;
import java.util.Map;

public class Okra {



    public static void create(Context context, Map<String, Object> okraOptions){
        int requestCode = 1;
        if (context instanceof Activity) {
            Intent intent = new Intent(context, OkraWebActivity.class);
            intent.putExtra("okraOptions", (Serializable) okraOptions);
            ((Activity) context).startActivityForResult(intent, requestCode);
        }
    }

}