package com.okra.widget.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.okra.widget.Okra;
import com.okra.widget.activity.OkHiAddressActivity;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.models.OkHiModels.Location;
import com.okra.widget.models.OkHiModels.LocationException;
import com.okra.widget.models.OkHiModels.User;
import com.okra.widget.services.AddressVerificationListener;
import com.okra.widget.services.PermissionType;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends OkHiAddressActivity implements AddressVerificationListener {

    Button button;
    Button okHiButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAddressVerificationService().init(this,"#bbdfc8",R.mipmap.ic_launcher,"https://homepages.cae.wisc.edu/~ece533/images/airplane.png","#bbdfc8",this);
        button = findViewById(R.id.okra_btn);
        okHiButton = findViewById(R.id.okra_okhi_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOkraWidget();
            }
        });
        okHiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAddressVerificationService().addUser("Bolu","Okunaiya","+2348188288402");
                getAddressVerificationService().launchAddressCollection();

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
            put("callback_url", "");
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


    @Override
    public void onSuccessCollection(User user, Location location) {
        System.out.println("SUCCESSFUL COLLECTION "+ user +  " " + location);
    }

    @Override
    public void onSuccessStartVerification(String result) {
        System.out.println("SUCCESSFUL VERIFICATION "+ result);
    }

    @Override
    public void onErrorCollectionAction(LocationException locationException) {
        System.out.println("ERROR COLLECTING "+ locationException);
    }

    @Override
    public void onErrorVerificationAction(LocationException locationException) {
        System.out.println("ERROR VERIFYING "+ locationException);
    }

    @Override
    public void permissionRequestHandler(boolean result, PermissionType type) {
        System.out.println("PERMISSION "+ type + " Result : " + result);
        switch (type){

            case OK_COLLECT:
                getAddressVerificationService().launchAddressCollection();
                break;
            case OK_VERIFY:
                break;
        }
    }

    @Override
    public void permissionRequestHandlerError(LocationException locationException, PermissionType type) {
        System.out.println("PERMISSION "+ type + " Error : " + locationException);
    }
}

