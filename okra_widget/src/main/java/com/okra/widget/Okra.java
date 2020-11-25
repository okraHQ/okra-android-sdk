package com.okra.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.okra.widget.activity.OkraWebActivity;

import java.io.Serializable;
import java.util.Map;

public class Okra {

    @SuppressLint("StaticFieldLeak")
    public static Context baseContext;

    public static void create(Context context, Map<String, Object> okraOptions){
        baseContext = context;
        if (context instanceof Activity) {
            Intent intent = new Intent(context, OkraWebActivity.class);
            intent.putExtra("okraOptions", (Serializable) okraOptions);
            ((Activity) context).startActivity(intent);
        }
    }
}