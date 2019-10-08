package com.okra.widget;


import android.content.Context;
import android.content.Intent;

import com.okra.widget.activity.OkraWebActivity;
import com.okra.widget.utils.OkraOptions;

public class Okra {

    public static Context baseContext;

    public static void create(Context context, OkraOptions okraOptions){
        baseContext = context;
        Intent intent = new Intent(context, OkraWebActivity.class);
        intent.putExtra("okraOptions", okraOptions);
        context.startActivity(intent);
    }

}
