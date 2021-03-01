package com.okra.widget;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Keep;

import com.okra.widget.activity.OkraWebActivity;

import java.io.Serializable;
import java.util.Map;

@Keep
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