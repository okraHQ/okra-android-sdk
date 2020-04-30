package com.okra.widget.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.okra.widget.Okra;
import com.okra.widget.R;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.models.Enums;
import com.okra.widget.models.Guarantor;
import com.okra.widget.utils.OkraOptions;
import com.okra.widget.utils.WebInterface;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class OkraWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        final OkraOptions okraOptions = (OkraOptions) getIntent().getSerializableExtra("okraOptions");
        // Initialize Link
        HashMap<String, Object> linkInitializeOptions = new HashMap<>();
        linkInitializeOptions.put("isWebview", okraOptions.isWebview());
        linkInitializeOptions.put("key", okraOptions.getKey());
        linkInitializeOptions.put("token", okraOptions.getToken());
        linkInitializeOptions.put("products", convertArrayListToString(okraOptions.getProducts()));
        linkInitializeOptions.put("env", okraOptions.getEnv().toString());
        linkInitializeOptions.put("clientName", okraOptions.getClientName());
        linkInitializeOptions.put("uuid", Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
        linkInitializeOptions.put("source", "android");
        linkInitializeOptions.put("imei", getIMEI(this));
        linkInitializeOptions.put("webhook", "http://requestb.in");
        linkInitializeOptions.put("color", okraOptions.getColor());
        linkInitializeOptions.put("limit", okraOptions.getLimit());
        linkInitializeOptions.put("corporate", okraOptions.isCorporate());
        linkInitializeOptions.put("connectMessage", okraOptions.getConnectMessage());
        linkInitializeOptions.put("guarantors", okraOptions.getGuarantors().toString());
        linkInitializeOptions.put("callback_url", okraOptions.getCallback_url());
        linkInitializeOptions.put("redirect_url", okraOptions.getRedirect_url());
        linkInitializeOptions.put("logo", okraOptions.getLogo());
        linkInitializeOptions.put("filter", "");
        linkInitializeOptions.put("widget_success", okraOptions.getWidget_success());
        linkInitializeOptions.put("currency", okraOptions.getCurrency());
        linkInitializeOptions.put("exp", okraOptions.getExp());
        linkInitializeOptions.put("success_title", okraOptions.getSuccess_title());
        linkInitializeOptions.put("success_message ", okraOptions.getSuccess_message());


        linkInitializeOptions.put("baseUrl", "https://app.okra.ng/");


        final Uri linkInitializationUrl = generateLinkInitializationUrl(linkInitializeOptions);


        final WebView okraLinkWebview = findViewById(R.id.ok_webview);
        WebSettings webSettings = okraLinkWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        okraLinkWebview.addJavascriptInterface(new WebInterface(this, okraOptions), "Android");


        okraLinkWebview.loadUrl("http://e630adb5.ngrok.io/mobile.html");
//        //okraLinkWebview.loadUrl("file:///android_res/raw/okra.html");
//        System.out.println("linkInitializationUrl.toString() " + linkInitializationUrl.toString());
//        System.out.println("new Guarantor " + new Guarantor(true, "", 3).toString());
//        okraLinkWebview.loadUrl(linkInitializationUrl.toString());

        okraLinkWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Uri parsedUri = Uri.parse(url);
                HashMap<String, String> linkData = parseLinkUriData(parsedUri);
                Boolean shouldClose = Boolean.valueOf(linkData.get("shouldClose"));
                if (shouldClose) {
                    Intent intent = new Intent(OkraWebActivity.this, Okra.baseContext.getClass());
                    intent.putExtra("okraHandler", new OkraHandler());
                    startActivity(intent);
                } else {
                    return false;
                }
                return true;
            }

        });


        okraLinkWebview.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String weburl){
                System.out.println(new Gson().toJson(okraOptions));
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    okraLinkWebview.evaluateJavascript("openOkraWidget("+"'"+new Gson().toJson(okraOptions)+"'"+");", null);
                } else {
                    okraLinkWebview.loadUrl("openOkraWidget("+"'"+new Gson().toJson(okraOptions)+"'"+");");
                }

            }
        });
    }

    public Uri generateLinkInitializationUrl(HashMap<String, Object> linkOptions) {
        Uri.Builder builder = Uri.parse(linkOptions.get("baseUrl").toString())
                .buildUpon()
                .appendQueryParameter("isWebview", "true")
                .appendQueryParameter("isMobile", "true");
        for (String key : linkOptions.keySet()) {
            if (!key.equals("baseUrl")) {
                String p = linkOptions.get(key).toString();
                builder.appendQueryParameter(key, linkOptions.get(key).toString());
            }
        }
        return builder.build();
    }

    public HashMap<String, String> parseLinkUriData(Uri linkUri) {
        HashMap<String, String> linkData = new HashMap<String, String>();
        for (String key : linkUri.getQueryParameterNames()) {
            linkData.put(key, linkUri.getQueryParameter(key));
        }
        return linkData;
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public String convertArrayListToString(ArrayList<Enums.Product> productList) {
        StringBuilder formattedArray = new StringBuilder("[");
        for (int index = 0; index < productList.size(); index++) {
            if (index == (productList.size() - 1)) {
                formattedArray.append(String.format("\"%s\"", productList.get(index)));
            } else {
                formattedArray.append(String.format("\"%s\",", productList.get(index)));
            }
        }
        formattedArray.append("]");
        return formattedArray.toString();
    }

    public String getIMEI(Activity activity) {
       try{
           TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
           if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
               return "null";
           }else {
               if (telephonyManager == null) return "null";
               return telephonyManager.getDeviceId() == "null" ? "" : telephonyManager.getDeviceId();
           }
       }catch (Exception exception){
           return "";
       }
    }
}
