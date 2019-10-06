package com.okra.widget;


import android.content.Context;
import android.content.Intent;

import com.okra.widget.activity.OkraWebActivity;
import com.okra.widget.utils.OkraOptions;

public class Starter {

    Context context;
    OkraOptions okraOptions;

    public Starter(Context context, OkraOptions okraOptions){
        this.context = context;
        this.okraOptions = okraOptions;
    }


    public void OpenOkraWidget(){
        Intent intent = new Intent(this.context, OkraWebActivity.class);
        intent.putExtra("okraOptions", this.okraOptions);
        this.context.startActivity(intent);
    }
}
