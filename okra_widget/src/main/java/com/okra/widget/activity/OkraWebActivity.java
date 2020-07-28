package com.okra.widget.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hover.sdk.api.Hover;
import com.hover.sdk.sims.SimInfo;
import com.hover.sdk.transactions.Transaction;
import com.okra.widget.Okra;
import com.okra.widget.R;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverResponse;
import com.okra.widget.models.OkraOptions;
import com.okra.widget.models.request.BankRequest;
import com.okra.widget.utils.BankUtils;
import com.okra.widget.utils.WebInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class OkraWebActivity extends AppCompatActivity {

    BankRequest bankRequest = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hover.initialize(this);
        setContentView(R.layout.activity_web);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        final OkraOptions okraOptions = (OkraOptions) getIntent().getSerializableExtra("okraOptions");
        okraOptions.setImei(getIMEI(this));

        final WebView okraLinkWebview = findViewById(R.id.ok_webview);
        WebSettings webSettings = okraLinkWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        okraLinkWebview.addJavascriptInterface(new WebInterface(this, okraOptions), "Android");


        okraLinkWebview.loadUrl("https://5e9dad3079f67.htmlsave.net/");

        okraLinkWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Uri parsedUri = Uri.parse(url);
                HashMap<String, String> linkData = parseLinkUriData(parsedUri);
                Boolean shouldClose = Boolean.valueOf(linkData.get("shouldClose"));
                if (shouldClose) {
                    Intent intent = new Intent(OkraWebActivity.this, Okra.baseContext.getClass());
                    intent.putExtra("okraHandler", new OkraHandler());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    return false;
                }
                return true;
            }

            public void onPageFinished(WebView view, String weburl){
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    okraLinkWebview.evaluateJavascript("openOkraWidget("+"'"+new Gson().toJson(okraOptions)+"'"+");", null);
                } else {
                    okraLinkWebview.loadUrl("openOkraWidget("+"'"+new Gson().toJson(okraOptions)+"'"+");");
                }

            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Map<String, String> map = BankUtils.getInputExtras(data);
        BankServices bankServices = null;
        if(map != null && !map.isEmpty() && map.containsKey("bank")){
            bankServices = BankUtils.getBankImplementation(map.get("bank"));
        }
        
        if(BankUtils.isFirstAction(map)){
            BankUtils.selectedSim = BankUtils.getSelectedSim(this, data);
        }

        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            HoverResponse hoverResponse = BankUtils.getHoverResponse(data);
            Transaction transaction = getHoverTransaction(hoverResponse.getUuid());

            try {
                if(bankRequest == null) bankRequest = new BankRequest();
                if(map != null && !map.isEmpty() && map.get("id").equals("bvn")){
                    bankRequest = bankServices.handleGetBvn(transaction, bankRequest);
                }else if(map != null && !map.isEmpty() && map.get("id").equals("accounts")){
                    bankRequest = bankServices.handleGetAccounts(transaction, bankRequest);
                }else if(map != null && !map.isEmpty() && map.get("id").equals("balance")){
                    bankRequest = bankServices.handleGetAccountBalance(transaction, bankRequest);
                }else if(map != null && !map.isEmpty() && map.get("id").equals("transactions")){
                    bankRequest = bankServices.handleGetTransactions(transaction, bankRequest);
                }
                if(map != null && !map.isEmpty() && Boolean.parseBoolean(map.get("isLastAction"))) bankRequest = null;

            } catch (Exception e) {
                Toast.makeText(this, "Bank not implemented", Toast.LENGTH_LONG).show();
            }

        } else if (requestCode == 0 && resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "Error: " + data.getStringExtra("error"), Toast.LENGTH_LONG).show();
        }
        if(bankServices.hasNext()){
            try {
                int s = bankServices.getIndex();
                BankUtils.fireIntent(this, bankServices.getNextAction(), map.get("bank"));
                bankServices.setIndex(bankServices.getIndex() + 1);
            } catch (Exception ignored) {}
        }else{
            Toast.makeText(this, "Doesnt have next", Toast.LENGTH_LONG).show();
        }
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

    private Transaction getHoverTransaction(String uuid){
        return Hover.getTransaction(uuid, this);
    }
}
