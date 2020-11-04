package com.okra.widget.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.okra.widget.Okra;
import com.okra.widget.R;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.models.Enums;
import com.okra.widget.utils.WebInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;


public class OkraWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        final Map<String, Object> mapOkraOptions = (Map<String, Object>) getIntent().getSerializableExtra("okraOptions");
        boolean isMap = getIntent().getBooleanExtra("isMap", false);
        if(isMap && getIntent().hasExtra("okraOptions")){
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

        final WebView okraLinkWebview = findViewById(R.id.ok_webview);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        WebSettings webSettings = okraLinkWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        okraLinkWebview.addJavascriptInterface(new WebInterface(this), "Android");

        okraLinkWebview.loadUrl("https://v2-mobile.okra.ng/");

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
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    okraLinkWebview.evaluateJavascript("openOkraWidget("+"'"+new JSONObject(mapOkraOptions).toString()+"'"+");", null);
                } else {
                    okraLinkWebview.loadUrl("openOkraWidget("+"'"+new JSONObject(mapOkraOptions).toString()+"'"+");");
                }
            }
        });
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
}
