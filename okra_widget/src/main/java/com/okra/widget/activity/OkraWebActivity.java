 package com.okra.widget.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.hover.sdk.api.Hover;
import com.hover.sdk.transactions.Transaction;
import com.okra.widget.Okra;
import com.okra.widget.R;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.interfaces.BankServices;
import com.okra.widget.models.IntentData;
import com.okra.widget.models.request.BankRequest;
import com.okra.widget.utils.BankUtils;
import com.okra.widget.utils.WebInterface;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.json.JSONObject;


public class OkraWebActivity extends AppCompatActivity {

    BankRequest bankRequest = null;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        context = this;

        final Map<String, Object> mapOkraOptions = (Map<String, Object>) getIntent().getSerializableExtra("okraOptions");
        if(getIntent().hasExtra("okraOptions")){
            Map<String, Object> deviceInfo = new HashMap<>();
            deviceInfo.put("deviceName", Build.BRAND);
            deviceInfo.put("deviceModel", android.os.Build.MODEL);
            deviceInfo.put("longitude", 0.0);
            deviceInfo.put("latitude", 0.0);
            deviceInfo.put("platform", "android");
            mapOkraOptions.put("uuid", Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
            mapOkraOptions.put("deviceInfo", deviceInfo);
            mapOkraOptions.put("isWebview", true);
            mapOkraOptions.put("source", "android");
        }


        WebView.setWebContentsDebuggingEnabled(true);
        final WebView okraLinkWebview = findViewById(R.id.ok_webview);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        WebSettings webSettings = okraLinkWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        okraLinkWebview.addJavascriptInterface(new WebInterface(this), "Android");

        okraLinkWebview.loadUrl("https://d8213e427064.ngrok.io/mobile.html");  //https://dev-v2-app.okra.ng

        okraLinkWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Uri parsedUri = Uri.parse(url);
                HashMap<String, String> linkData = parseLinkUriData(parsedUri);
                boolean shouldClose = Boolean.parseBoolean(linkData.get("shouldClose"));
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
                progressBar.setVisibility(View.GONE);
                String rr = new JSONObject(mapOkraOptions).toString();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    okraLinkWebview.evaluateJavascript("openOkraWidget("+"'"+new JSONObject(mapOkraOptions).toString()+"'"+");", null);
                } else {
                    okraLinkWebview.loadUrl("openOkraWidget("+"'"+new JSONObject(mapOkraOptions).toString()+"'"+");");
                }
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final Map<String, String> map = BankUtils.getInputExtras(data);
            final BankServices bankServices = BankUtils.getBankImplementation(map.get("bank"));
//            if (map != null && !map.isEmpty() && map.containsKey("bank")) {
//                bankServices = BankUtils.getBankImplementation(map.get("bank"));
//            }

            if (BankUtils.isFirstAction(map)) {
                BankUtils.selectedSim = BankUtils.getSelectedSim(this, data);
            }

            Log.i("partyneverstops", "action count is " + bankServices.getActionCount());
            Log.i("partyneverstops", "and present index is " + bankServices.getIndex());
            Log.i("partyneverstops", bankServices.hasNext() ? "this bank has next" : "this bank doesnt have next");
            if (bankServices.hasNext()) {
                try {
                     final Context context = this;
                    new CountDownTimer(5000, 5000) {
                        public void onFinish() {
                            try {
                                BankUtils.fireIntent(
                                        context,
                                        bankServices.getNextAction(),
                                        new IntentData(
                                                map.get("bank"),
                                                map.get("recordId"),
                                                map.containsKey("authPin") ? map.get("authPin") : "",
                                                map.containsKey("nuban") ? map.get("nuban") : "",
                                                map.get("miscellaneous"),
                                                map.get("bgColor"),
                                                map.get("accentColor"),
                                                map.get("buttonColor")
                                        ));
                                bankServices.setIndex(bankServices.getIndex() + 1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
                } catch (Exception ignored) {
                    String c = ignored.getMessage();
                    String v = ignored.getCause().getMessage();
                }
            }else {
                bankServices.setIndex(1);
            }
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
