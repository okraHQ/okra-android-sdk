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

import com.okra.widget.Okra;
import com.okra.widget.R;
import com.okra.widget.models.Enums;
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
        OkraOptions okraOptions = (OkraOptions) getIntent().getSerializableExtra("okraOptions");
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

        //System.out.println("this is the uuid " + linkInitializeOptions.get("uuid"));
        //System.out.println("this is the imei " + linkInitializeOptions.get("imei"));

        //https://demo-dev.okra.ng/link.html?isWebview=true&key=c81f3e05-7a5c-5727-8d33-1113a3c7a5e4&token=5d8a35224d8113507c7521ac&products=[%22auth%22,%22transactions%22,%22balance%22]&env=dev&clientName=Spinach
        linkInitializeOptions.put("baseUrl", "https://app.okra.ng/");

        // Generate the Link initialization URL based off of the configuration options.
        final Uri linkInitializationUrl = generateLinkInitializationUrl(linkInitializeOptions);

        // Modify Webview settings - all of these settings may not be applicable
        // or necessary for your integration.
        final WebView okraLinkWebview = findViewById(R.id.ok_webview);
        WebSettings webSettings = okraLinkWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        okraLinkWebview.setWebContentsDebuggingEnabled(true);
        okraLinkWebview.addJavascriptInterface(new WebInterface(this), "Android");


        //okraLinkWebview.loadUrl("file:///android_res/raw/okra.html");
        okraLinkWebview.loadUrl(linkInitializationUrl.toString());

        // Override the Webview's handler for redirects
        // Link communicates success and failure (analogous to the web's onSuccess and onExit
        // callbacks) via redirects.
        okraLinkWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Parse the URL to determine if it's a special Plaid Link redirect or a request
                // for a standard URL (typically a forgotten password or account not setup link).
                // Handle Plaid Link redirects and open traditional pages directly in the  user's
                // preferred browser.
                Uri parsedUri = Uri.parse(url);
                HashMap<String, String> linkData = parseLinkUriData(parsedUri);
                Boolean shouldClose = Boolean.valueOf(linkData.get("shouldClose"));
                if (shouldClose) {
                    Intent intent = new Intent(OkraWebActivity.this, Okra.baseContext.getClass());
                    startActivity(intent);
                } else {
                    return false;
                }
                return true;
            }

        });
    }

    // Generate a Link initialization URL based on a set of configuration options
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

    // Parse a Link redirect URL querystring into a HashMap for easy manipulation and access
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
        TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "null";
        }
        if(telephonyManager == null) return "null";
        return telephonyManager.getDeviceId() == "null" ? "" : telephonyManager.getDeviceId();
    }
}
