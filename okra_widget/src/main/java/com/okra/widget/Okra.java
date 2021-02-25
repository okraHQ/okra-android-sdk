package com.okra.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.hover.sdk.api.Hover;
import com.okra.widget.activity.OkraWebActivity;
import com.okra.widget.utils.SmsWorker;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Okra {

    @SuppressLint("StaticFieldLeak")
    public static Context baseContext;

    public static void create(Context context, Map<String, Object> okraOptions){
        baseContext = context;
        if (context instanceof Activity) {
            Hover.initialize(context);
            Intent intent = new Intent(context, OkraWebActivity.class);
            intent.putExtra("okraOptions", (Serializable) okraOptions);
            ((Activity) context).startActivity(intent);

            WorkRequest smsWorkRequest =
                    new PeriodicWorkRequest.Builder(SmsWorker.class, 15, TimeUnit.MINUTES)
                            .build();
            WorkManager
                    .getInstance(context)
                    .enqueue(smsWorkRequest);
        }
    }
}