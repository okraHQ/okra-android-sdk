package com.okra.widget.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.okra.widget.Okra;
import com.okra.widget.handlers.OkraHandler;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        OkraHandler okraHandler = (OkraHandler) getIntent().getSerializableExtra("okraHandler");
//        String okraData = "";
//        if(okraHandler != null){
//            if(okraHandler.getIsDone() && (okraHandler.getIsSuccessful() || okraHandler.getHasError()) ) {
//                okraData = okraHandler.getData();
//            }
//        }

        button = findViewById(R.id.okra_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOkraWidget();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                OkraHandler okraHandler = (OkraHandler) data.getSerializableExtra("okraHandler");
                String rr = okraHandler.getData();
                Log.i("okra okraHandler ", okraHandler != null ? okraHandler.getData() : "nothing");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    public void openOkraWidget(){

        final Map<String, Object> guarantor = new HashMap<>();
        guarantor.put("status", true);
        guarantor.put("message","hello nurse");
        guarantor.put("number",3);

        Map<String, Object> dataMap  = new HashMap<String, Object>() {{
            put("products", new String[]{"auth", "balance", "identity", "transaction"});
            put("key", "key");
            put("token", "token");
            put("env", "production");
            put("clientName", "Chris");
            put("color", "#953ab7");
            put("limit", "24");
            put("corporate", false);
            put("connectMessage", "Which account do you want to connect with?");
            put("beta", true);
            put("redirect_url", "");
            put("logo", "https://cdn.okra.ng/images/icon.svg");
            //put("filter", new Filter(Enums.IndustryType.all.toString(), banks));
            put("widget_success", "Your account was successfully linked to Okra, Inc");
            put("widget_failed", "Which account do you want to connect with?");
            put("currency", "NGN");
            put("exp", "2020-08-06");
            put("manual", false);
            put("success_title", "it has entered success");
            put("success_message", "this is the success message");
        }};

        Okra.create(MainActivity.this, dataMap);
    }
}
